package org.smartsupply.model.enums;

public enum ModeOfCreation {
    UNKNOWN("Unknown", 0),
    EXCEL_IMPORT("Excel Import", 1),
    RESOLVING_MISPLACED_RESULTS("Import & Resolving", 2),
    UNDO_EDIT("Undo Edit", 3),
    FORM_ENTRY("Form Entry", 4);

    ModeOfCreation(String name, Integer ordinal) {
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

    public static ModeOfCreation get(Integer number) {
        if (number != null) {
            for (ModeOfCreation t : ModeOfCreation.values()) {
                if (t.getOrdinal().equals(number)) {
                    return t;
                }
            }
        }
        return null;
    }
}
