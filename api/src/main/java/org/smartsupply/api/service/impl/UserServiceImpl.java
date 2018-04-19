package org.smartsupply.api.service.impl;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.jarcommons.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.smartsupply.api.Constants;
import org.smartsupply.api.dao.BaseDAO;
import org.smartsupply.api.service.UserService;
import org.smartsupply.api.service.impl.base.BaseQuickServiceImpl;
import org.smartsupply.api.utils.BuildSearchUtil;
import org.smartsupply.api.utils.BuildWhereClauseUtil;
import org.smartsupply.model.admin.Branch;
import org.smartsupply.model.admin.User;
import org.smartsupply.model.admin.UserType;

import org.smartsupply.model.enums.Gender;
import org.smartsupply.model.enums.RecordStatus;
import org.smartsupply.model.search.UserSearchParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import static com.jarcommons.JarValidate.disallowNull;

@Service("userService")
@Transactional
public class UserServiceImpl extends BaseQuickServiceImpl<User, UserSearchParams> implements UserService {


//    @Autowired
//    private BaseSearchService<Branch, OrgUnitSearchParams> orgUnitService;

    private BaseDAO<Branch> orgUnitDAO;

    @Autowired
    public void setOrgUnitDAO(BaseDAO<Branch> daoToSet) {
        orgUnitDAO = daoToSet;
        orgUnitDAO.setClazz(Branch.class);
    }

    @Autowired
    public void setBaseDao(BaseDAO<User> daoToSet) {
        super.baseDAO = daoToSet;
        super.baseDAO.setClazz(User.class);
    }

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public void validate(User user) throws Exception {
        disallowNull(user.getUserType(), "User type");
        disallowBlank(user.getUsername(), "User name");
        disallowBlank(user.getPassword(), "Password");
//        UserSearchParams params = new UserSearchParams(user.getUsername(), false);
//        User existing = getUnique(params);
//        if (existing != null && (user.idIsBlankOrEmpty() || user.idIsNOTBlankOrEmpty() && !existing.getId().equals(user.getId()))) {
//            throw new ValidationException("User name " + user.getUsername() + " already exists");
//        }
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public void deleteByIds(String[] ids) throws Exception {
        for (String id : ids) {
            deleteById(id);
        }
    }

    public void deleteById(String id) throws Exception {
        User user = getById(id);
        if (StringUtils.equalsIgnoreCase(user.getUsername(), "administrator")) {
            throw new Exception("cannot delete default administrator");
        }
        baseDAO.remove(user);
    }

    @Override
    public Search prepareSearch(UserSearchParams params, Integer pageNo) {
        Search search = new Search();

        if (params.hasUserTypes()) {
            search.addFilterIn("userType", params.getUserTypes());
        }
        search.addSort("firstName", false, true);

        search.setMaxResults(Constants.MAX_NUM_PAGE_RECORD);

        Filter firstNameFilter;
        Filter lastNameFilter;
        Filter userNameFilter;

        if (StringUtils.isNotBlank(params.getNameOrUserName())) {
            if (params.getNameOrUserName().contains(" ")) {
                String[] names = params.getNameOrUserName().split(" ");

                Filter[] firstNameFilters = new Filter[names.length];
                Filter[] lastNameFilters = new Filter[names.length];
                Filter[] userNameFilters = new Filter[names.length];
                for (int i = 0; i < names.length; i++) {
                    firstNameFilters[i] = new Filter("firstName", "%" + names[i] + "%", Filter.OP_ILIKE);
                    lastNameFilters[i] = new Filter("lastName", "%" + names[i] + "%", Filter.OP_ILIKE);
                    userNameFilters[i] = new Filter("username", "%" + names[i] + "%", Filter.OP_ILIKE);
                }

                firstNameFilter = Filter.and(firstNameFilters);
                lastNameFilter = Filter.and(lastNameFilters);
                userNameFilter = Filter.and(userNameFilters);

            } else {
                firstNameFilter = new Filter("firstName", "%" + params.getNameOrUserName() + "%", Filter.OP_ILIKE);
                lastNameFilter = new Filter("lastName", "%" + params.getNameOrUserName() + "%", Filter.OP_ILIKE);
                userNameFilter = new Filter("username", "%" + params.getNameOrUserName() + "%", Filter.OP_ILIKE);
            }

            search.addFilterOr(firstNameFilter, lastNameFilter, userNameFilter);

        }

        if (params.getGender() != null) {
            search.addFilterEqual("gender", params.getGender());
        }

        if (params.getRole() != null) {
            search.addFilterEqual("roles.name", params.getRole().getName());
        }

        if (params.getBranch() != null) {
            search.addFilterEqual("orgUnit", params.getBranch());
        }

        if (params.getUserType() != null) {
            search.addFilterEqual("userType", params.getUserType());
        }

        search.addFilterNotEqual("userType", UserType.CUSTOMER);

        search.addSort("firstName", false, true);
        search.addSort("lastName", false, true);
        search.addSort("username", false, true);

        search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);

        BuildSearchUtil.setPageNo(search, pageNo);

        return search;
    }






    @Override
    public List<User> get(UserSearchParams params, Integer pageNo) throws Exception {
        Connection conn = ConnectionService.getConnection();

        Statement s = conn.createStatement();
        String sql = buildQuery(params, pageNo);

        s.execute(sql);
        ResultSet rs = s.getResultSet();

        List<User> users = new ArrayList<>();

        String orgUnitIds = "";
        while ((rs != null) && (rs.next())) {
            String id = rs.getString("id");
            String email_address= rs.getString("email_address");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            Gender gender = Gender.get(rs.getInt("gender"));
            UserType userType = UserType.get(rs.getInt("user_type"));
            String org_unit_id = rs.getString("org_unit_id");
            if (StringUtil.isNotBlankOrEmpty(org_unit_id)) {
                orgUnitIds += StringUtil.isBlankOrEmpty(orgUnitIds) ? org_unit_id : "," + org_unit_id;
            }
            String username = rs.getString("username");

            User user = new User(id, firstName, lastName, gender,email_address);
            user.setUserType(userType);
            user.setBranch(new Branch(org_unit_id));

            user.setUsername(username);
            users.add(user);
        }

        ConnectionService.close(conn, s, rs);

//        List<Branch> orgUnits = orgUnitService.searchWithParams(new OrgUnitSearchParams(orgUnitIds, false));
//        for (User t : users) {
//            if (orgUnits.contains(t.getBranch())) {
//                t.setBranch(BaseServiceClass.get(t.getBranch(), orgUnits));
//            }
//        }
        return users;
    }

    @Override
    public String buildQuery(UserSearchParams params, Integer pageNo) {
        String sql = "select id, first_name, last_name, gender, user_type, org_unit_id, username, email_address from smartsupply_users";

        StringBuilder whereClause = new StringBuilder();
        BuildWhereClauseUtil.addStringFieldEquals(whereClause, "username", params.getNameOrUserName(), ",");
        if (params.hasUserTypes()) {
            List<Integer> integers = params.getUserTypes().stream().map(Enum::ordinal).collect(Collectors.toList());
            BuildWhereClauseUtil.addEqual(whereClause, "user_type", integers);
        }
        if (params.getUserType() != null) {
            BuildWhereClauseUtil.addEqual(whereClause, "user_type", params.getUserType().getOrdinal());
        }
        BuildWhereClauseUtil.addStringFieldEquals(whereClause, "id", params.getIds(), ",");

        if (StringUtils.isNotBlank(whereClause.toString())) {
            sql += " where " + whereClause.toString();
        }

        sql += BuildWhereClauseUtil.getPagingString(pageNo);
        return sql;
    }

    @Override
    public List<String> getDeatils(User user) throws Exception {
        return null;
    }
}