package org.smartsupply.model.search;

import org.smartsupply.model.product.Product;

public class StockSearchParams extends BaseSearchParams {
    private String name;
    private Product product;

    public StockSearchParams() {}

    public StockSearchParams(String ids, boolean isIdString) {
        this.name = isIdString ? null : ids;
        this.setIds(isIdString ? ids : null);
    }

    public StockSearchParams(String name) { this.name = name; }

    public StockSearchParams(Product product) { this.product = product; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
