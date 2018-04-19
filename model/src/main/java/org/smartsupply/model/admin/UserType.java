package org.smartsupply.model.admin;

import java.util.ArrayList;
import java.util.List;

public enum UserType {

    SALES_ATTENDANT("sales_attendant", true, false, 0),
    MANAGER("manager", true, true, 1),
    ACCOUNTANT("accoutant", true, true, 2),
    CUSTOMER("customer",false,false,3),
    UNKNOWN("unknown", false, false, 4);


    private String name;
    private Boolean isStaff;
    private Boolean canHeadUnit;
    private Integer ordinal;

    UserType(String name, Boolean isStaff, Boolean canHeadUnit, Integer ordinal) {
        this.name = name;
        this.isStaff = isStaff;
        this.canHeadUnit = canHeadUnit;
        this.ordinal = ordinal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<UserType> getUserTypesThatCanHeaDUnit() {
        List<UserType> types = new ArrayList<>();
        for (UserType t : UserType.values()) {
            if (t.canHeadUnit) {
                types.add(t);
            }
        }
        return types;
    }


    public static List<UserType> getStaffUserTypes() {
        List<UserType> types = new ArrayList<>();
        for (UserType t : UserType.values()) {
            if (t.isStaff) {
                types.add(t);
            }
        }
        return types;
    }

    public Boolean isStaff() {
        return isStaff;
    }

    public Boolean canHeadUnit() {
        return canHeadUnit;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public static UserType get(Integer number) {
        for (UserType userType : UserType.values()) {
            if (userType.getOrdinal().equals(number)) {
                return userType;
            }
        }
        return UNKNOWN;
    }

    public static UserType get(String string) {
        for (UserType userType : UserType.values()) {
            if (userType.getName().equals(string)) {
                return userType;
            }
        }
        return UNKNOWN;
    }

    public static String getOrdinals() {
        String ordinals = "";
        for (UserType t : UserType.values()) {
            ordinals += ordinals == "" ? t.getOrdinal() : "," + t.getOrdinal();
        }
        return ordinals;
    }
}
