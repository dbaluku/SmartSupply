package org.smartsupply.model.search;

import org.smartsupply.model.product.Product;
import org.smartsupply.model.product.Stock;

public class StockProductSearchParams extends BaseSearchParams {
    private Stock stock;
    private Product product;
    private Double quantity;

    public StockProductSearchParams() {}
    public StockProductSearchParams(Stock stock) { this.stock = stock; }

    public StockProductSearchParams(Product product) { this.product = product; }

    public StockProductSearchParams(Stock stock, Product product) {
        this.stock = stock;
        this.product = product;
    }

    public StockProductSearchParams(Stock stock, Product product, Double quantity) {
        this.stock = stock;
        this.product = product;
        this.quantity = quantity;
    }

    public StockProductSearchParams(Stock stock, Double qnty_grtr_oreql) {
        this.stock = stock;
        this.quantity = qnty_grtr_oreql;
    }

    public Stock getStock() {
        return stock;
    }

    public Product getProduct() {
        return product;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getQuantity() { return quantity; }

    public void setQuantity(Double quantity) { this.quantity = quantity; }

}
