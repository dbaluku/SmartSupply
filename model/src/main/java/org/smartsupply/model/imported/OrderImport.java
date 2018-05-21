package org.smartsupply.model.imported;

import org.smartsupply.model.admin.Branch;
import org.smartsupply.model.admin.User;
import org.smartsupply.model.customer.Customer;
import org.smartsupply.model.product.Product;

import java.util.Date;
import java.util.List;

public class OrderImport {
    private String customer;
    private String  products;
    private Date date_of_ordering;
    private double total_amount;
    private String branch;
    private String sales_man;
}
