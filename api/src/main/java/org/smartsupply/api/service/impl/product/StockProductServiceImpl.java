package org.smartsupply.api.service.impl.product;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smartsupply.api.dao.BaseDAO;
import org.smartsupply.api.service.baseclasses.BaseQuickService;
import org.smartsupply.api.service.baseclasses.BaseServiceClass;
import org.smartsupply.api.service.impl.ConnectionService;
import org.smartsupply.api.service.impl.base.BaseQuickServiceImpl;
import org.smartsupply.api.utils.BuildWhereClauseUtil;
import org.smartsupply.model.product.Product;
import org.smartsupply.model.product.Stock;
import org.smartsupply.model.product.StockProduct;
import org.smartsupply.model.search.ProductSearchParams;
import org.smartsupply.model.search.StockProductSearchParams;
import org.smartsupply.model.search.StockSearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.smartsupply.api.utils.MyValidate.disallowNull;

@Service("stockProductService")
public class StockProductServiceImpl extends BaseQuickServiceImpl<StockProduct, StockProductSearchParams>
implements BaseQuickService<StockProduct, StockProductSearchParams>{

    @Autowired
    public BaseQuickService<Product, ProductSearchParams> productService;

    @Autowired
    public BaseQuickService<Stock, StockSearchParams> stockService;

    @Autowired
    public void setBaseDao(BaseDAO<StockProduct> daoToSet) {
        super.baseDAO = daoToSet;
        super.baseDAO.setClazz(StockProduct.class);
    }

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public void validate(StockProduct stockProduct) throws Exception {
        disallowNull(stockProduct.getProduct(), "Product");
        disallowNull(stockProduct.getStock(), "Stock");
        disallowNull(stockProduct.getQuantity(), "Quantity");
//        disallowNull(product.getQuantityType(), "Quantity Type");
//        disallowNull(product.getUnitprice(), "Unit Price");
    }

    public void deleteById(String id) throws Exception {
        StockProduct stockProduct = getById(id);
        baseDAO.remove(stockProduct);
    }

    @Override
    public String buildQuery(StockProductSearchParams params, Integer pageNo) {
        String sql = "select s.id, s.quantity, s.stock_id, s.product_id from stock_products s " +
                "inner join stock st on s.stock_id = st.id " +
                "inner join products p on s.product_id = p.id";

        StringBuilder whereClause = new StringBuilder();
        BuildWhereClauseUtil.addEqual(whereClause, "s.stock_id", params.getStock());
        BuildWhereClauseUtil.addEqual(whereClause, "s.product_id", params.getProduct());
        if (StringUtils.isNotBlank(whereClause.toString())) {
            sql += " where " + whereClause.toString();
        }
        BuildWhereClauseUtil.addStringFieldEquals(whereClause, "id", params.getIds(), ",");

        sql += BuildWhereClauseUtil.getPagingString(pageNo);
        return sql;
    }

    @Override
    public List<StockProduct> get(StockProductSearchParams params, Integer pageNo) throws Exception {
        Connection conn = ConnectionService.getConnection();
        Statement s = conn.createStatement();
        String sql = buildQuery(params, pageNo);

        s.execute(sql);
        ResultSet rs = s.getResultSet();
        List<StockProduct> stocks = new ArrayList<>();

        String stockIds = null;
        String productIds = null;
        while ((rs != null) && (rs.next())) {
            String id = rs.getString("id");
            Double quantity = rs.getDouble("quantity");
            String productid = rs.getString("product_id");
            Product product = new Product(productid);
            productIds += StringUtils.isBlank(productIds) ? productid : "," + productid;

            String stockid = rs.getString("stock_id");
            Stock stock = new Stock(stockid);
            stockIds += StringUtils.isBlank(stockIds) ? stockid : "," + stockid;

            StockProduct stockProduct = new StockProduct(id, quantity, stock, product);
            stocks.add(stockProduct);
        }
        ConnectionService.close(conn, s, rs);

        List<Product> products = productService.get(new ProductSearchParams(productIds));
        for (StockProduct t : stocks) {
            if (products.contains(t.getProduct())) {
                t.setProduct(BaseServiceClass.get(t.getProduct(), products));
            }
        }

        List<Stock> stocks1 = stockService.get(new StockSearchParams(stockIds));
        for (StockProduct t : stocks) {
            if (stocks1.contains(t.getStock())) {
                t.setStock(BaseServiceClass.get(t.getStock(), stocks1));
            }
        }
        return stocks;
    }

}
