package org.smartsupply.model.order;

import org.smartsupply.model.BaseData;
import org.smartsupply.model.product.Product;
import org.smartsupply.model.product.Stock;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_products",
        uniqueConstraints= {@UniqueConstraint(
                columnNames = {"product_id", "order_id"})})
public class OrderItem extends BaseData implements Serializable{
    private Product product;
    private Order order;
    private double quantity;

    public OrderItem() {
    }

    public OrderItem(Product product, Order order, double quantity) {
        this.product = product;
        this.order = order;
        this.quantity = quantity;
    }

    public OrderItem(Product product, double quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "product_id")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "order_id")
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Column(name = "quantity")
    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
