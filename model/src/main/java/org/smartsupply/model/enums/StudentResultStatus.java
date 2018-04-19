package org.smartsupply.model.enums;

public enum StudentResultStatus {
    UNKNOWN("UNKNOWN", 0),
    PENDING("PENDING", 1),
    CONFIRMED("CONFIRMED", 2);

    StudentResultStatus(String name, Integer ordinalValue) {
        this.name = name;
        this.ordinalValue = ordinalValue;
    }

    private String name;
    private Integer ordinalValue;

    public String getName() {
        return this.name;
    }

    public Integer getOrdinalValue() {
        return ordinalValue;
    }

    public static StudentResultStatus getStatus(Integer statusNumber) {
        for (StudentResultStatus status : StudentResultStatus.values()) {
            if (status.getOrdinalValue().equals(statusNumber)) {
                return status;
            }
        }

        return UNKNOWN;
    }

}
