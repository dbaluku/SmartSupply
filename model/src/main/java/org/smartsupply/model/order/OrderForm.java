package org.smartsupply.model.order;

import java.util.List;

public class OrderForm {
    private String product_id;
    private double quantity;

    public OrderForm(String product_id, double quantity) {
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
