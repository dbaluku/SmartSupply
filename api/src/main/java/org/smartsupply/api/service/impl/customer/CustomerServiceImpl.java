package org.smartsupply.api.service.impl.customer;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smartsupply.api.dao.BaseDAO;
import org.smartsupply.api.service.baseclasses.BaseQuickService;
import org.smartsupply.api.service.impl.ConnectionService;
import org.smartsupply.api.service.impl.base.BaseQuickServiceImpl;
import org.smartsupply.api.utils.BuildWhereClauseUtil;
import org.smartsupply.model.customer.Customer;
import org.smartsupply.model.search.CustomerSearchParams;
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
@Service("customerService")
public class CustomerServiceImpl extends BaseQuickServiceImpl<Customer, CustomerSearchParams>
        implements BaseQuickService<Customer, CustomerSearchParams> {

//    @Autowired
//    public BaseQuickService<Customer, CustomerSearchParams> categoryService;

    @Autowired
    public void setBaseDao(BaseDAO<Customer> daoToSet) {
        super.baseDAO = daoToSet;
        super.baseDAO.setClazz(Customer.class);
    }

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public void validate(Customer customer) throws Exception {
        disallowNull(customer.getName(), "Name");
        //disallowNull(customer.getPhoneNo(), "Phone");
        //disallowNull(customer.getCustomerNo(), "Customer NUmber");
    }

    public void deleteById(String id) throws Exception {
        Customer customer = getById(id);
        baseDAO.remove(customer);
    }

    @Override
    public String buildQuery(CustomerSearchParams params, Integer pageNo) {
        String sql = "select id, customer_name, email, phoneno, adress, customer_no from customer";

        StringBuilder whereClause = new StringBuilder();
        BuildWhereClauseUtil.addEqual(whereClause, "phoneno", params.getPhoneNo());
        if (StringUtils.isNotBlank(whereClause.toString())) {
            sql += " where " + whereClause.toString();
        }
        BuildWhereClauseUtil.addStringFieldEquals(whereClause, "id", params.getIds(), ",");

        sql += BuildWhereClauseUtil.getPagingString(pageNo);
        return sql;
    }

    @Override
    public List<Customer> get(CustomerSearchParams params, Integer pageNo) throws Exception {
        Connection conn = ConnectionService.getConnection();

        Statement s = conn.createStatement();
        String sql = buildQuery(params, pageNo);
        s.execute(sql);
        ResultSet rs = s.getResultSet();

        List<Customer> customers = new ArrayList<>();

        String categoryids = "";
        while ((rs != null) && (rs.next())) {
            String id = rs.getString("id");
            String name= rs.getString("customer_name");
            String email= rs.getString("email");
            String phoneNo= rs.getString("phoneno");
            String Adress= rs.getString("adress");
            String customer_no= rs.getString("customer_no");

            Customer customer =new Customer(id,name,email,phoneNo,Adress,customer_no);

            customers.add(customer);
        }

        ConnectionService.close(conn, s, rs);
        return customers;
    }


}
