package org.smartsupply.model.enums;

public enum FeesPaymentStrategy {
    NOT_APPLICABLE("Not Aplicable", 0, false),
    PER_SEMESTER("Per Semester", 1, true),
    FOR_WHOLE_COURSE("For Whole Course", 2, true);

    FeesPaymentStrategy(String name, Integer ordinalValue, Boolean feesApplicable) {
        this.name = name;
        this.ordinalValue = ordinalValue;
        this.feesApplicable = feesApplicable;
    }

    private String name;
    private Integer ordinalValue;
    private Boolean feesApplicable;

    public String getName() {
        return this.name;
    }

    public Integer getOrdinalValue() {
        return ordinalValue;
    }

    public static FeesPaymentStrategy getFeesPaymentStrategy(Integer ordinalValue) {
        for (FeesPaymentStrategy strategy : FeesPaymentStrategy.values()) {
            if (strategy.getOrdinalValue().equals(ordinalValue)) {
                return strategy;
            }
        }
        return NOT_APPLICABLE;
    }

    public Boolean feesApplicable() {
        return feesApplicable;
    }
}
