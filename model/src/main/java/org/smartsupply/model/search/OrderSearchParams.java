package org.smartsupply.model.search;

import org.smartsupply.model.admin.Branch;
import org.smartsupply.model.admin.User;
import org.smartsupply.model.customer.Customer;

import java.util.Date;

public class OrderSearchParams extends BaseSearchParams {
    private Customer customer;
    private Branch branch;
    private User user;
    private Date date;

    public OrderSearchParams() {
    }

    public OrderSearchParams(Branch branch) { this.branch = branch; }

    public OrderSearchParams(User user) { this.user = user; }

    public OrderSearchParams(Customer customer) {
        this.customer = customer;
    }

    public OrderSearchParams(Branch branch, User user) {
        this.branch = branch;
        this.user = user;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Branch getBranch() { return branch; }

    public void setBranch(Branch branch) { this.branch = branch; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }
}
