package org.smartsupply.model.search;

import org.smartsupply.model.enums.ProductType;
import org.smartsupply.model.product.Category;

public class ProductSearchParams extends BaseSearchParams {
    private String name;
    private Category category;
    private ProductType productType;

    public ProductSearchParams() {}

    public ProductSearchParams(String ids, boolean isIdString) {
        this.name = isIdString ? null : ids;
        this.setIds(isIdString ? ids : null);
    }

    public ProductSearchParams(String name) { this.name = name; }

    public ProductSearchParams(Category category) { this.category = category; }

    public ProductSearchParams(ProductType productType) { this.productType = productType; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
