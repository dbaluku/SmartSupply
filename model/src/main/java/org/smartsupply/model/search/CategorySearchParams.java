package org.smartsupply.model.search;

public class CategorySearchParams extends BaseSearchParams {
    private String name;

    public CategorySearchParams() {}

    public CategorySearchParams(String ids, boolean isIdString) {
        this.name = isIdString ? null : ids;
        this.setIds(isIdString ? ids : null);
    }
    public CategorySearchParams(String name) { this.name = name; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
