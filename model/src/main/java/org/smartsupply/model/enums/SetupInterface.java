package org.smartsupply.model.enums;

import java.util.List;

public interface SetupInterface {
    
    public boolean hasStudentNumbers();
    public boolean hasCampuses();

    public int regNoColumnIndex();
    public int studentNoColumnIndex();
    public int nameColumnIndex();
    public int finalMarkColumnIndex();
    public int cw1ColumnIndex();
    public int cw2ColumnIndex();
    public int cw3ColumnIndex();
    public int cwColumnIndex();
    public int secAColumnIndex();
    public int q1ColumnIndex();
    public int q2ColumnIndex();
    public int q3ColumnIndex();
    public int q4ColumnIndex();
    public int q5ColumnIndex();
    public int q6ColumnIndex();
    public int q7ColumnIndex();
    public int q8ColumnIndex();
    public int q9ColumnIndex();
    public int examColumnIndex();
    public int remarkColumnIndex();

    public int attendance();
    public int onlineDiscussions();
    public int continuousAssessment1();
    public int continuousAssessment2();
    public int totalCwOutOf40();
    public int totalCwOutOf100();
    public int totalExamOutOf100();
    public int totalExamOutOf60();

    public List<PrintDocumentType> printDocumentTypes();

    public boolean testimonialHasStudentOption();

    public boolean transcriptHasStudentOption();

    public boolean hasProfilePictures();

    public boolean allowsResultsBeforeStudentIntake();
}
