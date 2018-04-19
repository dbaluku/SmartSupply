package org.smartsupply.model.search;

import org.smartsupply.model.customer.Customer;

public class OrderSearchParams extends BaseSearchParams {
    private Customer customer;

    public OrderSearchParams(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
