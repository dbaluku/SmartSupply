package org.smartsupply.model.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum UserStatus {

    DISABLED("Disabled", 0), ENABLED("Enabled", 1);

    UserStatus(String name, Integer ordinal) {
        this.name = name;
        this.ordinal = ordinal;
    }

    private String name;
    private Integer ordinal;

    public String getName() {
        return name;
    }

    public static List<UserStatus> values2() {
        List<UserStatus> list = Arrays.asList(UserStatus.values());
        Collections.reverse(list);
        return list;
    }

    public static UserStatus get(Integer statusNumber) {
        for (UserStatus status : values()) {
            if (status.ordinal.equals(statusNumber)) {
                return status;
            }
        }
        return null;
    }
}
