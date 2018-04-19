package org.smartsupply.model.enums;

import java.util.Arrays;
import java.util.List;

public enum ProductType {

    UNKNOWN("Unknown", 0, ""),
    JEWELLERY("Jewellery", 1, "J"),
    ELECTRONICS("Electronics", 2, "E");

    private String name, abbreviation;
    private Integer ordinal;

    ProductType(String name,  int ordinal, String abbreviation) {
        this.name= name;
        this.ordinal= ordinal;
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public static ProductType get(Integer number) {
        for (ProductType productType : ProductType.values()) {
            if (productType.getOrdinal().equals(number)) {
                return productType;
            }
        }
        return UNKNOWN;
    }

    public static ProductType get(String string) {
        for (ProductType productType : ProductType.values()) {
            if (productType.getName().equals(string) || productType.getAbbreviation().equals(string)) {
                return productType;
            }
        }
        return UNKNOWN;
    }

    public static List<ProductType> values2() {
        return Arrays.asList(JEWELLERY, ELECTRONICS);
    }
}
