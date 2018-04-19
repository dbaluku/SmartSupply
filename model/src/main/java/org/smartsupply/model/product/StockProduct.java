package org.smartsupply.model.product;

import org.smartsupply.model.BaseData;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stock_products",
        uniqueConstraints= {@UniqueConstraint(
                columnNames = {"stock_id", "product_id"})})
public class StockProduct extends BaseData implements Serializable {
    private double quantity;
    private Stock stock;
    private Product product;



    public StockProduct() {}

    public StockProduct(String id, double quantity, Stock stock, Product product) {
        super(id);
        this.quantity = quantity;
        this.stock = stock;
        this.product = product;
    }

    public StockProduct(double quantity, Stock stock, Product product) {
        this.quantity = quantity;
        this.stock = stock;
        this.product = product;
    }

    @Column(name = "quantity")
    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "stock_id")
    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "product_id")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}
