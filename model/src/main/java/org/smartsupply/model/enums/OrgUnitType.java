package org.smartsupply.model.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum OrgUnitType {
    MAIN_BRANCH("Main_Branch", 0),
    BRANCH("Branch", 1);


    static {
        MAIN_BRANCH.children = Arrays.asList(BRANCH);
    }

    OrgUnitType(String name, Integer ordinalValue) {
        this.name = name;
        this.ordinalValue = ordinalValue;
    }

    private String name;
    private Integer ordinalValue;
    private List<OrgUnitType> children = new ArrayList<>();

    public String getName() {
        return this.name;
    }

    public Integer getOrdinalValue() {
        return ordinalValue;
    }

    public List<OrgUnitType> getChildren() {
        return children;
    }

    public String getChildrenStr() {
        String s = "";
        children = children == null ? new ArrayList<>() : children;
        for (OrgUnitType t : children) {
            s += (s.equalsIgnoreCase("") ? "" : ", ") + t.getName();
        }
        return s;
    }

    public List<OrgUnitType> getParents() {
        List<OrgUnitType> parents = new ArrayList<>();
        for (OrgUnitType t : values()) {
            if (t.children.contains(this)) {
                parents.add(t);
            }
        }
        return parents;
    }

    public static OrgUnitType get(Integer number) {
        for (OrgUnitType orgUnitType : values()) {
            if (orgUnitType.getOrdinalValue().equals(number)) {
                return orgUnitType;
            }
        }
        return null;
    }
}
