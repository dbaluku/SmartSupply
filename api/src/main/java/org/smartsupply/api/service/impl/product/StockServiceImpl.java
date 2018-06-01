package org.smartsupply.api.service.impl.product;

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
import org.smartsupply.model.product.Product;
import org.smartsupply.model.product.Stock;
import org.smartsupply.model.product.StockProduct;
import org.smartsupply.model.search.OrgUnitSearchParams;
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
import java.util.Set;

import static org.smartsupply.api.utils.MyValidate.disallowNull;

@Service("stockService")
public class StockServiceImpl extends BaseQuickServiceImpl<Stock, StockSearchParams>
        implements BaseQuickService<Stock, StockSearchParams> {

    @Autowired
    public BaseQuickService<Product, ProductSearchParams> productService;

    @Autowired
    public BaseQuickService<StockProduct, StockProductSearchParams> stockProductService;
    @Autowired
    public void setBaseDao(BaseDAO<Stock> daoToSet) {
        super.baseDAO = daoToSet;
        super.baseDAO.setClazz(Stock.class);
    }

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public void validate(Stock stock) throws Exception {
        disallowNull(stock.getName(), "Name");
//        disallowNull(product.getQuantityType(), "Quantity Type");
//        disallowNull(product.getUnitprice(), "Unit Price");
    }



    public void deleteById(String id) throws Exception {
        Stock stock = getById(id);
        baseDAO.remove(stock);
    }

    @Override
    public String buildQuery(StockSearchParams params, Integer pageNo) {
        String sql = "select id, name from stock";

        StringBuilder whereClause = new StringBuilder();
        BuildWhereClauseUtil.addStringLike(whereClause, "name", params.getName());
        if (StringUtils.isNotBlank(whereClause.toString())) {
            sql += " where " + whereClause.toString();
        }
        BuildWhereClauseUtil.addStringFieldEquals(whereClause, "id", params.getIds(), ",");

        sql += BuildWhereClauseUtil.getPagingString(pageNo);
        return sql;
    }

    @Override
    public List<Stock> get(StockSearchParams params, Integer pageNo) throws Exception {
        Connection conn = ConnectionService.getConnection();

        Statement s = conn.createStatement();
        String sql = buildQuery(params, pageNo);

        s.execute(sql);
        ResultSet rs = s.getResultSet();

        List<Stock> stocks = new ArrayList<>();

        String productids = "";
        while ((rs != null) && (rs.next())) {
            String id = rs.getString("id");
            String name= rs.getString("name");
            Stock stock = new Stock(id,name);
            stocks.add(stock);
        }

        ConnectionService.close(conn, s, rs);
//        List<Product> products = productService.get(new ProductSearchParams(productids,true));
//        for (Stock t : stocks) {
//            if( products.contains(t.getProduct())) {
//                t.setProduct(BaseServiceClass.get(t.getProduct(), products));
//            }
//        }


        return fillInStockProducts(stocks,params);
    }

    private List<Stock> fillInStockProducts(List<Stock> stocks, StockSearchParams params) throws Exception {
        StockProductSearchParams stockProductSearchParams = new StockProductSearchParams();
       for (Stock stock:stocks){
           List<StockProduct> stockProducts = stockProductService.get(new StockProductSearchParams(stock));
           stock.setProducts(stockProducts);
       }

        return stocks;
    }

}
