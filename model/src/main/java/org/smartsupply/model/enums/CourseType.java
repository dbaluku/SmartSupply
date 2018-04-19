package org.smartsupply.model.enums;

import org.smartsupply.model.util.MyStringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum CourseType {
    DIPLOMA("Diploma", 0, true, "diploma", null, false, "Undergraduate Programmes"),
    BACHELORS("Bachelors", 1, true, "bachelors", null, false, "Undergraduate Programmes"),
    POST_GRADUATE_DIPLOMA("PGD", 2, true, "pgd", null, false, "Graduate Programmes"),
    MASTERS("Masters", 3, false, "masters", Arrays.asList(POST_GRADUATE_DIPLOMA), true, "Graduate Programmes"),
    PHD("PhD", 4, false, "phd", null, false, "Graduate Programmes"),
    SHORT_COURSE("Short Course", 5, false, "short_course", null, false, ""),
    CERTIFICATE("Certificate", 6, false, "certificate", null, false, "Certificate Programmes");

    CourseType(String name, Integer ordinal, boolean isClassified, String abbreviation,
               List<CourseType> slaveCourseTypes, boolean hasTopic, String programsGroupName) {
        this.name = name;
        this.ordinal = ordinal;
        this.isClassified = isClassified;
        this.abbreviation = abbreviation;
        this.slaveCourseTypes = slaveCourseTypes;
        this.hasTopic = hasTopic;
        this.programsGroupName = programsGroupName;
    }

    private String name, abbreviation;
    private Integer ordinal;
    private boolean isClassified, hasTopic;
    private List<CourseType> slaveCourseTypes;
    private String programsGroupName;

    public String getName() {
        return this.name;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public boolean isClassified() {
        return isClassified;
    }

    public List<CourseType> getSlaveCourseTypes() {
        return slaveCourseTypes;
    }

    public boolean hasSlaveCourseTypes() {
        return slaveCourseTypes != null && slaveCourseTypes.size() > 0;
    }

    public String getProgramsGroupName() {
        return programsGroupName;
    }

    public static CourseType get(Integer number) {
        for (CourseType status : CourseType.values()) {
            if (status.getOrdinal().equals(number)) {
                return status;
            }
        }
        return null;
    }

    public String getTranscriptView() throws Exception {
        if (MyStringUtil.isBlankOrEmpty(abbreviation))
            throw new Exception("Transcript not yet implemented for " + name);
        return abbreviation + "_transcript";
    }

    public boolean hasTopic() {
        return hasTopic;
    }

    public static List<Integer> getOrdinalValues(List<CourseType> values) {
        List<Integer> ordinalValues = new ArrayList<>();
        for (CourseType value : (values == null ? new ArrayList<CourseType>() : values)) {
            ordinalValues.add(value.ordinal());
        }
        return ordinalValues;
    }

}