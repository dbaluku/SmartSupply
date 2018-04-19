package org.smartsupply.api.service.impl.admin;

import com.jarcommons.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.smartsupply.api.dao.BaseDAO;
import org.smartsupply.api.service.UserService;
import org.smartsupply.api.service.baseclasses.BaseQuickService;
import org.smartsupply.api.service.baseclasses.BaseService;
import org.smartsupply.api.service.baseclasses.BaseServiceClass;
import org.smartsupply.api.service.impl.ConnectionService;
import org.smartsupply.api.service.impl.base.BaseQuickServiceImpl;
import org.smartsupply.api.service.impl.base.BaseServiceImpl;
import org.smartsupply.api.utils.BuildWhereClauseUtil;
import org.smartsupply.model.admin.Branch;
import org.smartsupply.model.admin.JobTitle;
import org.smartsupply.model.admin.User;
import org.smartsupply.model.customer.Customer;
import org.smartsupply.model.enums.OrgUnitType;
import org.smartsupply.model.exception.ValidationException;
import org.smartsupply.model.product.Stock;
import org.smartsupply.model.search.OrgUnitSearchParams;
import org.smartsupply.model.search.StockSearchParams;
import org.smartsupply.model.search.UserSearchParams;
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
import java.util.stream.Collectors;

import static com.jarcommons.JarValidate.isNotNull;
import static com.jarcommons.JarValidate.isNull;
import static org.smartsupply.api.utils.MyValidate.isNotBlank;
import static org.smartsupply.api.utils.MyValidate.validateUniqueProperty;
@Transactional
@Service("orgUnitService")
public class OrgUnitServiceImpl extends BaseQuickServiceImpl<Branch,OrgUnitSearchParams> implements
        BaseQuickService<Branch,OrgUnitSearchParams>{

    @Autowired
    public UserService userService;

    @Autowired
    private BaseService<JobTitle> jobTitleService;

    @Autowired
    public BaseQuickService<Stock,StockSearchParams> stockService;

    @Autowired
    public void setBaseDao(BaseDAO<Branch> daoToSet) {
        super.baseDAO = daoToSet;
        super.baseDAO.setClazz(Branch.class);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public void validate(Branch t) throws Exception {

        isNotBlank(t.getName(), "Branch Name");
        isNotBlank(t.getAbbreviation(), "Abbreviation");

        if (isNotNull(t.getHeadTitle()) && isNull(t.getHead()) || isNull(t.getHeadTitle()) && isNotNull(t.getHead()))
            throw new ValidationException("Head: Title and office holder should be supplied together!!!");

        if (isNotNull(t.getAsstHead()) && isNull(t.getAsstHeadTitle()) || isNull(t.getAsstHead()) && isNotNull(t.getAsstHeadTitle()))
            throw new ValidationException("Asst Head: Title and office holder should be supplied together!!!");

        if (isNotNull(t.getAgHead()) && isNull(t.getAgHeadTitle()) || isNull(t.getAgHead()) && isNotNull(t.getAgHeadTitle()))
            throw new ValidationException("Ag Head: Title and office holder should be supplied together!!!");

        if (t.getHead() != null && t.getAgHead() != null)
            throw new ValidationException("A Unit can not have a Head and a Ag. Head at the same time!!!");

        if (t.getHead() != null && t.getAsstHead() != null && t.getHead().equals(t.getAsstHead()))
            throw new ValidationException("Head and Asst. Head must be different !!!");

        if (t.getHead() != null && t.getAgHead() != null && t.getHead().equals(t.getAgHead()))
            throw new ValidationException("Head and Ag. Head must be different !!!");

        if (t.getAsstHead() != null && t.getAgHead() != null && t.getAsstHead().equals(t.getAgHead()))
            throw new ValidationException("Asst. Head and Ag. Head must be different !!!");

        Branch existingBranch = getByField("name", t.getName());
        validateUniqueProperty(existingBranch, t, "Branch", "Name", t.getName());

        existingBranch = getByField("abbreviation", t.getAbbreviation());
        validateUniqueProperty(existingBranch, t, "Branch", "Abbreviation", t.getAbbreviation());
    }


    public static Branch get(Branch branch, List<Branch> branches) {
        for (Branch t : branches) {
            if (branch.equals(t)) {
                return t;
            }
        }
        return null;
    }

    @Override
    public List<Branch> get(OrgUnitSearchParams params,Integer pageNo) throws Exception {
        Connection conn = ConnectionService.getConnection();

        Statement s = conn.createStatement();
        String sql = buildQuery(params, pageNo);
        s.execute(sql);
        ResultSet rs = s.getResultSet();

        List<Branch> branches = new ArrayList<>();

        String stocks = "";
        String userIds="";
        String jobtitlesids="";
        while ((rs != null) && (rs.next())) {
            String id = rs.getString("id");
            String name= rs.getString("name");
            String abbreviation= rs.getString("abbreviation");
            OrgUnitType orgUnitType=OrgUnitType.get(rs.getInt("org_unit_type"));
            String head= rs.getString("head_id");
            User usr= new User(head);
            if (StringUtil.isNotBlankOrEmpty(head)) {
                userIds += StringUtil.isBlankOrEmpty(stocks) ? head : "," + head;
            }
            String headTitle =rs.getString("head_title_id");
            JobTitle jobTitle =new JobTitle(headTitle);
            String branch_id = rs.getString("parent");
            Branch parent = new Branch(branch_id);

            String location = rs.getString("location");
            Date date = rs.getDate("date_created");
            String stock_id =rs.getString("stock_id");
            Stock stock =new Stock(stock_id);
            if (StringUtil.isNotBlankOrEmpty(stock_id)) {
                stocks += StringUtil.isBlankOrEmpty(stocks) ? stock_id : "," + stock_id;
            }

            Branch branch =new Branch(name, abbreviation, orgUnitType, usr,
                    jobTitle, parent,location,date,stock);
            branches.add(branch);
        }

        ConnectionService.close(conn, s, rs);

        List<User> users = userService.searchWithParams(new UserSearchParams(userIds, true));
        for (Branch t : branches) {
            if (users.contains(t.getHead())) {
                t.setHead(BaseServiceClass.get(t.getHead(), users));
            }
        }
        for(Branch t :branches) {
            JobTitle jobTitle = jobTitleService.getById(t.getAgHeadTitle().getId());
            t.setAgHeadTitle(jobTitle);
        }

        List<Stock> stockList = stockService.searchWithParams(new StockSearchParams(stocks, true));
        for (Branch t : branches) {
            if (stockList.contains(t.getStock())) {
                t.setStock(BaseServiceClass.get(t.getStock(), stockList));
            }
        }

        return branches;
    }



    public String buildQuery(UserSearchParams params, Integer pageNo) {
        String sql = "select (*)from org_unit";

        StringBuilder whereClause = new StringBuilder();
//        BuildWhereClauseUtil.addStringFieldEquals(whereClause, "username", params.getNameOrUserName(), ",");
//        if (params.hasUserTypes()) {
//            List<Integer> integers = params.getUserTypes().stream().map(Enum::ordinal).collect(Collectors.toList());
//            BuildWhereClauseUtil.addEqual(whereClause, "user_type", integers);
//        }
//        if (params.getUserType() != null) {
//            BuildWhereClauseUtil.addEqual(whereClause, "user_type", params.getUserType().getOrdinal());
//        }
//        BuildWhereClauseUtil.addStringFieldEquals(whereClause, "id", params.getIds(), ",");

        if (StringUtils.isNotBlank(whereClause.toString())) {
            sql += " where " + whereClause.toString();
        }

        sql += BuildWhereClauseUtil.getPagingString(pageNo);
        return sql;
    }

}
