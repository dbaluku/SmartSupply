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
import org.smartsupply.model.enums.ProductType;
import org.smartsupply.model.enums.QuantityType;
import org.smartsupply.model.product.Category;
import org.smartsupply.model.product.Product;
import org.smartsupply.model.search.CategorySearchParams;
import org.smartsupply.model.search.ProductSearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.jarcommons.JarValidate.disallowBlank;
import static org.smartsupply.api.utils.MyValidate.disallowNull;

@Service("productService")
@Transactional
public class ProductServiceImpl extends BaseQuickServiceImpl<Product, ProductSearchParams>
implements BaseQuickService<Product, ProductSearchParams> {

    @Autowired
    public BaseQuickService<Category, CategorySearchParams> categoryService;

    @Autowired
    public void setBaseDao(BaseDAO<Product> daoToSet) {
        super.baseDAO = daoToSet;
        super.baseDAO.setClazz(Product.class);
    }

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public void validate(Product product) throws Exception {
        disallowNull(product.getName(), "Name");
        disallowNull(product.getQuantityType(), "Quantity Type");
        disallowNull(product.getUnitprice(), "Unit Price");
    }

    public void deleteById(String id) throws Exception {
        Product product = getById(id);
        baseDAO.remove(product);
    }

    @Override
    public String buildQuery(ProductSearchParams params, Integer pageNo) {
        String sql = "select id, product_name, product_type, quantity_type, unit_price from products";

        StringBuilder whereClause = new StringBuilder();
        BuildWhereClauseUtil.addEqual(whereClause, "product_name", params.getName());
        if (params.getProductType() != null) {
            BuildWhereClauseUtil.addEqual(whereClause, "product_type", params.getProductType().getOrdinal());
        }
        if (StringUtils.isNotBlank(whereClause.toString())) {
            sql += " where " + whereClause.toString();
        }
        BuildWhereClauseUtil.addStringFieldEquals(whereClause, "id", params.getIds(), ",");

        sql += BuildWhereClauseUtil.getPagingString(pageNo);
        return sql;
    }

    @Override
    public List<Product> get(ProductSearchParams params, Integer pageNo) throws Exception {
        Connection conn = ConnectionService.getConnection();

        Statement s = conn.createStatement();
        String sql = buildQuery(params, pageNo);
        s.execute(sql);
        ResultSet rs = s.getResultSet();

        List<Product> products = new ArrayList<>();

        String categoryids = "";
        while ((rs != null) && (rs.next())) {
            String id = rs.getString("id");
            String product_name= rs.getString("product_name");
           float unit_price = rs.getFloat("unit_price");
           ProductType product_type =ProductType.get(rs.getInt("product_type"));
            QuantityType quantity_type = QuantityType.get(rs.getInt("quantity_type"));
            Product product = new Product(id,product_name,product_type,unit_price,quantity_type);


            products.add(product);
        }

        ConnectionService.close(conn, s, rs);
        return products;
    }


}
