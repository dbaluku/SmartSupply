package org.smartsupply.api.service.impl.product;

import com.jarcommons.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smartsupply.api.dao.BaseDAO;
import org.smartsupply.api.service.baseclasses.BaseQuickService;
import org.smartsupply.api.service.impl.ConnectionService;
import org.smartsupply.api.service.impl.base.BaseQuickServiceImpl;
import org.smartsupply.api.utils.BuildWhereClauseUtil;
import org.smartsupply.model.product.Category;
import org.smartsupply.model.search.CategorySearchParams;
import org.smartsupply.model.search.OrgUnitSearchParams;
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

import static org.smartsupply.api.utils.MyValidate.disallowNull;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl extends BaseQuickServiceImpl<Category, CategorySearchParams>
implements BaseQuickService<Category, CategorySearchParams> {
    @Autowired
    public void setBaseDao(BaseDAO<Category> daoToSet) {
        super.baseDAO = daoToSet;
        super.baseDAO.setClazz(Category.class);
    }

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public void validate(Category category) throws Exception {
        disallowNull(category.getName(), "Name");
//        disallowNull(product.getQuantityType(), "Quantity Type");
//        disallowNull(product.getUnitprice(), "Unit Price");
    }


    public void deleteById(String id) throws Exception {
        Category category = getById(id);
        baseDAO.remove(category);
    }

    @Override
    public String buildQuery(CategorySearchParams params, Integer pageNo) {
        String sql = "select id, category_description, category_name from category";

        StringBuilder whereClause = new StringBuilder();
        BuildWhereClauseUtil.addStringLike(whereClause, "category_name", params.getName());
        if (StringUtils.isNotBlank(whereClause.toString())) {
            sql += " where " + whereClause.toString();
        }
        BuildWhereClauseUtil.addStringFieldEquals(whereClause, "id", params.getIds(), ",");

        sql += BuildWhereClauseUtil.getPagingString(pageNo);
        return sql;
    }

    @Override
    public List<Category> get(CategorySearchParams params, Integer pageNo) throws Exception {
        Connection conn = ConnectionService.getConnection();

        Statement s = conn.createStatement();
        String sql = buildQuery(params, pageNo);

        s.execute(sql);
        ResultSet rs = s.getResultSet();

        List<Category> categories = new ArrayList<>();

        String categoryids = "";
        while ((rs != null) && (rs.next())) {
            String id = rs.getString("id");
            String category_name= rs.getString("category_name");
            String category_description= rs.getString("category_description");
            Category category = new Category(id,category_name,category_description);
            categories.add(category);
        }

        ConnectionService.close(conn, s, rs);
        return categories;
    }

}
