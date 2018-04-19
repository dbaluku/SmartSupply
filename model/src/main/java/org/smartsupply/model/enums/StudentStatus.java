package org.smartsupply.model.enums;

import java.util.*;

public enum StudentStatus {

    UNKNOWN(true, true, true, "Unknown", 0, false, "status_unknown", 1),
    ACTIVE(true, true, true, "Active", 1, false, "status_active", 2),
    DEAD_YEAR(true, false, true, "Dead year", 2, false, "status_dead_year", 4),
    DISCONTINUED(false, false, false, "Discontinued", 3, false, "status_discontinued", 10),
    GRADUATED(true, false, false, "Graduated", 4, true, "status_graduated", 6),
    SUSPENDED(false, false, false, "Suspended", 5, false, "status_suspended", 9),
    UNDER_INVESTIGATION(false, true, false, "Under Investigation", 6, false, "status_under_investigation", 7),
    STAY_PUT(true, true, true, "Stay Put", 7, false, "status_stay_put", 3),
    DICEASED(true, true, true, "Deceased", 8, false, "status_diceased", 11),
    RECOMMENDED_FOR_DISCONTINUATION(false, true, false, "To be Discontinued", 9, false, "status_to_be_discontinued", 8),
    GRADUAND(true, false, false, "Graduand", 10, true, "status_graduand", 5);

    StudentStatus(boolean viewsResults, boolean updatesResults, boolean canGraduate,
                  String name, Integer ordinalValue, boolean isGradStatus, String code, Integer sortIndex) {
        this.name = name;
        this.ordinalValue = ordinalValue;
        this.viewsResults = viewsResults;
        this.updatesResults = updatesResults;
        this.canGraduate = canGraduate;
        this.isGradStatus = isGradStatus;
        this.code = code;
        this.sortIndex = sortIndex;
    }

    private String name, code;
    private Integer ordinalValue, sortIndex;
    private boolean viewsResults, updatesResults, canGraduate, isGradStatus;

    public String getName() {
        return this.name;
    }

    public String getAbbreviation() {
        return name.length() > 10 ? this.name.substring(0, 10) : name;
    }

    public String getCode() {
        return this.code;
    }

    public Integer getOrdinalValue() {
        return ordinalValue;
    }

    public static StudentStatus getStatus(Integer statusNumber) {
        for (StudentStatus status : values()) {
            if (status.getOrdinalValue().equals(statusNumber)) {
                return status;
            }
        }
        return null;
    }

    public static List<Integer> getOrdinalValues(List<StudentStatus> studentStatuses) {
        List<Integer> list = new ArrayList<>();
        if (studentStatuses != null) {
            for (StudentStatus status : studentStatuses) {
                list.add(status.getOrdinalValue());
            }
        }
        return list;
    }

    public static List<StudentStatus> validForSavingResultsList() {
        List<StudentStatus> studentStatuses = new ArrayList<>();
        for (StudentStatus status : values()) {
            if (!status.updatesResults) {
                studentStatuses.add(status);
            }
        }
        return studentStatuses;
    }

    public boolean viewsResults() {
        return viewsResults;
    }

    public boolean updatesResults() {
        return updatesResults;
    }

    public boolean canGraduate() {
        return canGraduate;
    }

    public boolean isGradStatus() {
        return isGradStatus;
    }

    public static List<StudentStatus> sortedStatuses() {
        List<StudentStatus> studentStatuses = new ArrayList<>();
        for (StudentStatus t : StudentStatus.values()) {
            studentStatuses.add(t);
        }
        Collections.sort(studentStatuses, SORT_ORDER);
        return studentStatuses;
    }

    public static final Comparator<StudentStatus> SORT_ORDER = (status1, status2) -> status1.sortIndex.compareTo(status2.sortIndex);

}
