package org.smartsupply.model.enums;

public enum Duration {
    YEARS("Year(s)", 0),
    MONTHS("Month(s)", 1),
    WEEKS("Week(s)", 2),
    DAYS("Day(s)", 3);

    Duration(String name, Integer ordinal) {
        this.name = name;
        this.ordinal = ordinal;
    }

    private String name;
    private Integer ordinal;

    public String getName() {
        return this.name;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public static Duration get(Integer number) {
        for (Duration t : Duration.values()) {
            if (t.getOrdinal().equals(number)) {
                return t;
            }
        }
        return null;
    }
}