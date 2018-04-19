package org.smartsupply.api.service.impl.order;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smartsupply.api.dao.BaseDAO;
import org.smartsupply.api.service.baseclasses.BaseQuickService;
import org.smartsupply.api.service.impl.ConnectionService;
import org.smartsupply.api.service.impl.base.BaseQuickServiceImpl;
import org.smartsupply.api.utils.BuildWhereClauseUtil;
import org.smartsupply.model.customer.Customer;
import org.smartsupply.model.order.Order;
import org.smartsupply.model.search.OrderSearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.smartsupply.api.utils.MyValidate.disallowNull;

public class OrderServiceImpl extends BaseQuickServiceImpl<Order, OrderSearchParams>
        implements BaseQuickService<Order, OrderSearchParams> {

    @Autowired
    public void setBaseDao(BaseDAO<Order> daoToSet) {
        super.baseDAO = daoToSet;
        super.baseDAO.setClazz(Order.class);
    }

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public void validate(Order order) throws Exception {
        disallowNull(order.getCustomer(), "Customer");
        disallowNull(order.getDate_of_ordering(), "Date");
        disallowNull(order.getProducts(), "Products");
        //disallowNull(customer.getCustomerNo(), "Customer NUmber");
    }

    public void deleteById(String id) throws Exception {
       Order order = getById(id);
        baseDAO.remove(order);
    }

    @Override
    public String buildQuery(OrderSearchParams params, Integer pageNo) {
        String sql = "select id, customer_id, order_products, date_created, total_amount from orders";

        StringBuilder whereClause = new StringBuilder();
        BuildWhereClauseUtil.addEqual(whereClause, "customer_id", params.getCustomer());
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

        String categoryids = "";
        while ((rs != null) && (rs.next())) {
            String id = rs.getString("id");
            String customer_id= rs.getString("customer_id");
            Customer customer =new Customer(customer_id);
            String orderlist= rs.getString("order_products");
            Date date_created= rs.getDate("date_created");
            Double total_amount= rs.getDouble("total_amount");


            //Order order =new Order(id,Customer customer, List<Product> products, Date date_of_ordering, double total_amount);

            //orders.add(order);
        }

        ConnectionService.close(conn, s, rs);
        return orders;
    }


}
