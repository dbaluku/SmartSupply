package org.smartsupply.model.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public enum ClassOfAward {

    FIRST_CLASS("First Class", 4.40, 5.0, Arrays.asList(CourseType.POST_GRADUATE_DIPLOMA, CourseType.BACHELORS)),
    SECOND_UPPER("Second class-Upper Division", 3.60, 4.39, Arrays.asList(CourseType.POST_GRADUATE_DIPLOMA, CourseType.BACHELORS)),
    SECOND_LOWER("Second class-Lower Division", 2.80, 3.59, Arrays.asList(CourseType.POST_GRADUATE_DIPLOMA, CourseType.BACHELORS)),
    PASS("Pass", 2.00, 2.79, Arrays.asList(CourseType.POST_GRADUATE_DIPLOMA, CourseType.BACHELORS)),
    NOT_CLASSIFIED("Not Classified", -1.0, -1.0, Arrays.asList()),
    ERROR("Error finding class of award", -1.0, -1.0, Arrays.asList()),
    CLASS_I("Class I (Distinction)", 4.40, 5.0, Arrays.asList(CourseType.DIPLOMA)),
    CLASS_II("Class II (Credit)", 2.80, 4.39, Arrays.asList(CourseType.DIPLOMA)),
    CLASS_III("Class III (Pass)", 2.00, 2.79, Arrays.asList(CourseType.DIPLOMA));

    ClassOfAward(String name, double from, double to, List<CourseType> courseTypes) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.courseTypes = courseTypes;
    }

    private static Logger logStatic = LoggerFactory.getLogger(ClassOfAward.class);
    private String name;
    private double from, to;

    private List<CourseType> courseTypes;

    public String getName() {
        return this.name;
    }

    private static List<ClassOfAward> classesOfAward(CourseType courseType) {
        List<ClassOfAward> classOfAwards = new ArrayList<>();
        for (ClassOfAward c : ClassOfAward.values()) {
            if (c.courseTypes.contains(courseType)) {
                classOfAwards.add(c);
            }
        }
        return classOfAwards;
    }

    public static ClassOfAward get(CourseType courseType, double cgpa) {
        if (courseType == null) {
            logStatic.error("Course type is required to compute Class of award");
            return ERROR;
        }
        return courseType.isClassified() ? get(courseType, cgpa, classesOfAward(courseType)) : NOT_CLASSIFIED;
    }

    private static ClassOfAward get(CourseType courseType, double cgpa, List<ClassOfAward> classesOfAward) {
        try {
            return classesOfAward.stream().filter(t -> t.from <= cgpa && cgpa <= t.to).findFirst().get();
        } catch (NoSuchElementException e) {
            logStatic.error("Error finding class of award for CGPA: " + cgpa + "  CourseType: " + courseType);
            return ERROR;
        }
    }

}
