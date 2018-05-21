package org.smartsupply.api.service.impl.order;

import com.jarcommons.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smartsupply.api.dao.BaseDAO;
import org.smartsupply.api.service.baseclasses.BaseQuickService;
import org.smartsupply.api.service.baseclasses.BaseService;
import org.smartsupply.api.service.baseclasses.BaseServiceClass;
import org.smartsupply.api.service.impl.ConnectionService;
import org.smartsupply.api.service.impl.base.BaseQuickServiceImpl;
import org.smartsupply.api.service.impl.product.ProductServiceImpl;
import org.smartsupply.api.utils.BuildWhereClauseUtil;
import org.smartsupply.model.admin.Branch;
import org.smartsupply.model.admin.User;
import org.smartsupply.model.customer.Customer;
import org.smartsupply.model.order.Order;
import org.smartsupply.model.order.OrderItem;
import org.smartsupply.model.product.Product;
import org.smartsupply.model.search.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.smartsupply.api.utils.MyValidate.disallowNull;

@Service("orderService")
public class OrderServiceImpl extends BaseQuickServiceImpl<Order, OrderSearchParams>
        implements BaseQuickService<Order, OrderSearchParams> {

    @Autowired
    private BaseQuickService<User, UserSearchParams> userService;//UserService userService

    @Autowired
    private BaseQuickService<Branch,OrgUnitSearchParams> orgUnitService;

    @Autowired
    public BaseQuickService<Product, ProductSearchParams> productService;

    @Autowired
    public BaseQuickService<OrderItem,OrderItemSearchParams>  orderitemService;


    @Autowired
    public void setBaseDao(BaseDAO<Order> daoToSet) {
        super.baseDAO = daoToSet;
        super.baseDAO.setClazz(Order.class);
    }

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public void validate(Order order) throws Exception {
       // disallowNull(order.getCustomer(), "Customer");
        disallowNull(order.getDate_of_ordering(), "Date");
        disallowNull(order.getSales_man(),"Sales Rep");
        //disallowNull(order.getProducts(), "Products");
        //disallowNull(customer.getCustomerNo(), "Customer NUmber");
    }

    public void deleteById(String id) throws Exception {
       Order order = getById(id);
        baseDAO.remove(order);
    }

    @Override
    public String buildQuery(OrderSearchParams params, Integer pageNo) {
        String sql = "select id, customer_id, date_created, total_amount,user_id, branch_id from orders";

        StringBuilder whereClause = new StringBuilder();
        BuildWhereClauseUtil.addEqual(whereClause, "customer_id", params.getCustomer());
        BuildWhereClauseUtil.addEqual(whereClause,"branch_id",params.getBranch());
        BuildWhereClauseUtil.addEqual(whereClause,"user_id",params.getUser());
        if (StringUtils.isNotBlank(whereClause.toString())) {
            sql += " where " + whereClause.toString();
        }
        BuildWhereClauseUtil.addStringFieldEquals(whereClause, "id", params.getIds(), ",");

        sql += BuildWhereClauseUtil.getPagingString(pageNo);
        return sql;
    }

    @Override
    public List<Order> get(OrderSearchParams params, Integer pageNo) throws Exception {
        Connection conn = ConnectionService.getConnection();

        Statement s = conn.createStatement();
        String sql = buildQuery(params, pageNo);
        s.execute(sql);
        ResultSet rs = s.getResultSet();

        List<Order> orders = new ArrayList<>();

        String  userids = "";
        String customreids="";
        String branchids="";
        while ((rs != null) && (rs.next())) {
            String id = rs.getString("id");
            String customer_id= rs.getString("customer_id");
            Customer customer =new Customer(customer_id);
            if (StringUtil.isNotBlankOrEmpty(customer_id)) {
                customreids += StringUtil.isBlankOrEmpty(customreids) ? customer_id : "," + customer_id;
            }
            String user_id= rs.getString("user_id");
            User user = new User(user_id);
            if (StringUtil.isNotBlankOrEmpty(user_id)) {
                userids += StringUtil.isBlankOrEmpty(userids) ? user_id : "," + user_id;
            }
            String branch_id = rs.getString("branch_id");
            Branch branch =new Branch(branch_id);
            if (StringUtil.isNotBlankOrEmpty(branch_id)) {
                branchids += StringUtil.isBlankOrEmpty(branchids) ? branch_id : "," + branch_id;
            }
            Date date_created= rs.getDate("date_created");
            Double total_amount= rs.getDouble("total_amount");

            Order order = new Order(id,customer,date_created,total_amount,branch, user);

            orders.add(order);
        }
        ConnectionService.close(conn, s, rs);

        List<User> users = userService.searchWithParams(new UserSearchParams(userids, true));
        for (Order t : orders) {
            if (users.contains(t.getSales_man())) {
                t.setSales_man(BaseServiceClass.get(t.getSales_man(), users));
            }
        }

        List<Branch> branches = orgUnitService.get(new OrgUnitSearchParams(branchids,true));
        for (Order t : orders) {
            if (branches.contains(t.getBranch())) {
                t.setBranch(BaseServiceClass.get(t.getBranch(), branches));
            }
        }

        //add customer to be searchedd for


//        fill in the items
        for (Order order: orders) {
          List<OrderItem>items=   orderitemService.get(new OrderItemSearchParams(order));
          order.setProducts(items);
        }

        return orders;
    }


}
