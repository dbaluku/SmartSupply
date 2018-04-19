package org.smartsupply.model.enums;

import java.util.Arrays;
import java.util.List;

public enum Gender {
    UNKNOWN("Unknown", 0, ""),
    MALE("Male", 1, "M"),
    FEMALE("Female", 2, "F");

    Gender(String name, Integer ordinal, String abbreviation) {
        this.name = name;
        this.ordinal = ordinal;
        this.abbreviation = abbreviation;
    }

    private String name, abbreviation;
    private Integer ordinal;

    public String getName() {
        return this.name;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public static Gender get(Integer number) {
        for (Gender gender : Gender.values()) {
            if (gender.getOrdinal().equals(number)) {
                return gender;
            }
        }
        return UNKNOWN;
    }

    public static Gender get(String string) {
        for (Gender gender : Gender.values()) {
            if (gender.getName().equals(string) || gender.getAbbreviation().equals(string)) {
                return gender;
            }
        }
        return UNKNOWN;
    }

    public static List<Gender> values2() {
        return Arrays.asList(MALE, FEMALE);
    }
}
