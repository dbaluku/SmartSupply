package org.smartsupply.model.order;

import org.smartsupply.model.BaseData;
import org.smartsupply.model.admin.Branch;
import org.smartsupply.model.admin.User;
import org.smartsupply.model.customer.Customer;
import org.smartsupply.model.product.Product;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseData implements Serializable {
    private static final long serialVersionUID = -2636551734031385042L;

    private Customer customer;
    private List<Product> products;
    private Date date_of_ordering;
    private double total_amount;
    private Branch branch;
    private User sales_man;

    public Order() {
    }

    public Order(String id) {
        super(id);
    }

    public Order(List<Product> products, Date date_of_ordering, double total_amount, Branch branch, User sales_man) {
        this.products = products;
        this.date_of_ordering = date_of_ordering;
        this.total_amount = total_amount;
        this.branch = branch;
        this.sales_man = sales_man;
    }

    public Order(Customer customer, List<Product> products, Date date_of_ordering, double total_amount, Branch branch, User sales_man) {
        this.customer = customer;
        this.products = products;
        this.date_of_ordering = date_of_ordering;
        this.total_amount = total_amount;
        this.branch = branch;
        this.sales_man = sales_man;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "customer_id", nullable = true)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToMany
    @JoinTable(name = "order_products", joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Column(name = "date_created", nullable = false)
    public Date getDate_of_ordering() {
        return date_of_ordering;
    }

    public void setDate_of_ordering(Date date_of_ordering) {
        this.date_of_ordering = date_of_ordering;
    }

    @Column(name = "total_amount", nullable = false)
    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "branch_id", nullable = false)
    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id", nullable = false)
    public User getSales_man() {
        return sales_man;
    }

    public void setSales_man(User sales_man) {
        this.sales_man = sales_man;
    }
}
