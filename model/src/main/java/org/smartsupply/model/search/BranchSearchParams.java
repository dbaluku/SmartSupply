package org.smartsupply.model.search;

public class BranchSearchParams extends BaseSearchParams{

    String name;

    public BranchSearchParams(){}

    public BranchSearchParams(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
