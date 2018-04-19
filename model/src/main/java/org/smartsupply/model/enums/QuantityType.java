package org.smartsupply.model.enums;

import java.util.Arrays;
import java.util.List;

public enum QuantityType {
    UNKNOWN("Unknown", 0),
    DOZENS("Dozens", 1),
    KILOS("Kilos",3),
    LITRES("Litres",4),
    SACKETS("Sackets", 2);

    private String name;
    private Integer ordinal;

    QuantityType(String name, Integer ordinal) {
        this.name = name;
        this.ordinal = ordinal;
    }

    public String getName() {
        return name;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public static QuantityType get(Integer number){
        for (QuantityType quantityType : QuantityType.values()) {
            if (quantityType.getOrdinal().equals(number)) {
                return quantityType;
            }
        }
        return UNKNOWN;
    }

    public static QuantityType get(String name){
        for (QuantityType quantityType : QuantityType.values()) {
            if (quantityType.getName().equals(name)) {
                return quantityType;
            }
        }
        return UNKNOWN;
    }

    public static List<QuantityType> values2() {
        return Arrays.asList(DOZENS, SACKETS, KILOS, LITRES);
    }
}
