package org.smartsupply.model.search;

import org.smartsupply.model.product.Product;
import org.smartsupply.model.product.Stock;

public class StockProductSearchParams extends BaseSearchParams {
    private Stock stock;
    private Product product;

    public StockProductSearchParams() {}
    public StockProductSearchParams(Stock stock) { this.stock = stock; }

    public StockProductSearchParams(Product product) { this.product = product; }

    public StockProductSearchParams(Stock stock, Product product) {
        this.stock = stock;
        this.product = product;
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
}
