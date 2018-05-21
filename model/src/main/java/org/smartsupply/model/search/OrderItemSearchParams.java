package org.smartsupply.model.search;

import org.smartsupply.model.order.Order;
import org.smartsupply.model.product.Product;

public class OrderItemSearchParams extends BaseSearchParams {
    private Order order;
    private Product product;

    public OrderItemSearchParams() {
    }

    public OrderItemSearchParams(Order order) {
        this.order = order;
    }

    public OrderItemSearchParams(Product product) {
        this.product = product;
    }

    public OrderItemSearchParams(Order order, Product product) {
        this.order = order;
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
