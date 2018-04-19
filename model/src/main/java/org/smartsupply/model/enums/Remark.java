package org.smartsupply.model.enums;

public enum Remark {

    MSD("MSD", true, "Missed Both Cw and Ex", "", "MSD"),

    MSD_CW("MSD Cw", true, "Missed Course Work", "", "MSD-CW"),

    MSD_EXAM("MSD Ex", true, "Missed Exam", "", "MSD-EX"),

    NORMAL_PROGRESS("NP", false, "Normal Progress", "", ""),

    AUDITED("Audited", false, "Audited Course", "AC", "Audited"),

    COURSE_TO_RETAKE("CTR", true, "Course to Retake", "", "CTR"),

    RETAKE_SCORE("RT", false, "Retake Score", "", "RT"),

    CONCEDED_PASS("CP", true, "Conceded Pass", "CP", "CP"),

    CREDITS_TRANSFERRED("CT", false, "Credits Transferred", "", "CT");


    private String name, description, transcriptString, testimonialStr;

    private boolean hasCourseUnitPrefix;

    Remark(String name, boolean hasCourseUnitPrefix, String description, String transcriptString,
           String testimonialStr) {
        this.name = name;
        this.hasCourseUnitPrefix = hasCourseUnitPrefix;
        this.description = description;
        this.transcriptString = transcriptString;
        this.testimonialStr = testimonialStr;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean hasCourseUnitPrefix() {
        return hasCourseUnitPrefix;
    }

    public String transcriptString() {
        return transcriptString;
    }

    public String testimonialStr() {
        return testimonialStr;
    }
}
