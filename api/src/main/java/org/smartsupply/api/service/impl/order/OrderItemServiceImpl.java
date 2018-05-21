package org.smartsupply.api.service.impl.order;

import com.jarcommons.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smartsupply.api.dao.BaseDAO;
import org.smartsupply.api.service.baseclasses.BaseQuickService;
import org.smartsupply.api.service.baseclasses.BaseServiceClass;
import org.smartsupply.api.service.impl.ConnectionService;
import org.smartsupply.api.service.impl.base.BaseQuickServiceImpl;
import org.smartsupply.api.utils.BuildWhereClauseUtil;
import org.smartsupply.model.order.Order;
import org.smartsupply.model.order.OrderItem;
import org.smartsupply.model.product.Product;
import org.smartsupply.model.search.OrderItemSearchParams;
import org.smartsupply.model.search.ProductSearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service("orderitemService")
public class OrderItemServiceImpl extends BaseQuickServiceImpl<OrderItem, OrderItemSearchParams>
        implements BaseQuickService<OrderItem, OrderItemSearchParams> {

    @Autowired
    public BaseQuickService<Product, ProductSearchParams> productService;


    @Autowired
    public void setBaseDao(BaseDAO<OrderItem> daoToSet) {
        super.baseDAO = daoToSet;
        super.baseDAO.setClazz(OrderItem.class);
    }

    private Logger log = LoggerFactory.getLogger(this.getClass());



    public void deleteById(String id) throws Exception {
        OrderItem orderItem = getById(id);
        baseDAO.remove(orderItem);
    }

    @Override
    public String buildQuery(OrderItemSearchParams params, Integer pageNo) {
        String sql = "select product_id, order_id, quantity from order_products";

        StringBuilder whereClause = new StringBuilder();
        BuildWhereClauseUtil.addEqual(whereClause, "product_id", params.getProduct());
        BuildWhereClauseUtil.addEqual(whereClause, "order_id", params.getOrder());

        if (StringUtils.isNotBlank(whereClause.toString())) {
            sql += " where " + whereClause.toString();
        }
      //  BuildWhereClauseUtil.addStringFieldEquals(whereClause, "id", params.getIds(), ",");

        sql += BuildWhereClauseUtil.getPagingString(pageNo);
        return sql;
    }

    @Override
    public List<OrderItem> get(OrderItemSearchParams params, Integer pageNo) throws Exception {
        Connection conn = ConnectionService.getConnection();

        Statement s = conn.createStatement();
        String sql = buildQuery(params, pageNo);
        s.execute(sql);
        ResultSet rs = s.getResultSet();

        List<OrderItem> items = new ArrayList<>();

        String orderIds="";
        String productIds ="";

        while ((rs != null) && (rs.next())) {
            //String id = rs.getString("id");
            String product_id= rs.getString("product_id");
            Product product = new Product(product_id);
            if (StringUtil.isNotBlankOrEmpty(product_id)) {
                productIds += StringUtil.isBlankOrEmpty(productIds) ? product_id : "," + product_id;
            }
//            String order_id = rs.getString("order_id");
//            Order order = new Order(order_id);
//            if (StringUtil.isNotBlankOrEmpty(order_id)) {
//                orderIds += StringUtil.isBlankOrEmpty(orderIds) ? order_id : "," + order_id;
//            }
            double quantity = rs.getDouble("quantity");

            OrderItem orderItem = new OrderItem(product,quantity);


            items.add(orderItem);
        }

        ConnectionService.close(conn, s, rs);

        List<Product> products = productService.get(new ProductSearchParams(productIds,true));
        for (OrderItem t : items) {
            if (products.contains(t.getProduct())) {
                t.setProduct(BaseServiceClass.get(t.getProduct(), products));
            }
        }



        // more work is needed here
        return items;
    }

}

