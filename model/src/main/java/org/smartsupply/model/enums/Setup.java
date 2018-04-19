package org.smartsupply.model.enums;

import java.util.Arrays;
import java.util.List;

public enum Setup implements SetupInterface {

    MUK("Muk", "muk", 0) {

        @Override
        public boolean hasStudentNumbers() {
            return true;
        }

        @Override
        public boolean hasCampuses() {
            return true;
        }

        @Override
        public int regNoColumnIndex() {
            return 1;
        }

        @Override
        public int studentNoColumnIndex() {
            return 2;
        }

        @Override
        public int nameColumnIndex() {
            return 3;
        }

        @Override
        public int finalMarkColumnIndex() {
            return 4;
        }

        @Override
        public int cw1ColumnIndex() {
            return 6;
        }

        @Override
        public int cw2ColumnIndex() {
            return 7;
        }

        @Override
        public int cw3ColumnIndex() {
            return 8;
        }

        @Override
        public int cwColumnIndex() {
            return 9;
        }

        @Override
        public int secAColumnIndex() {
            return 10;
        }

        @Override
        public int q1ColumnIndex() {
            return 11;
        }

        @Override
        public int q2ColumnIndex() {
            return 12;
        }

        @Override
        public int q3ColumnIndex() {
            return 13;
        }

        @Override
        public int q4ColumnIndex() {
            return 14;
        }

        @Override
        public int q5ColumnIndex() {
            return 15;
        }

        @Override
        public int q6ColumnIndex() {
            return 16;
        }

        @Override
        public int q7ColumnIndex() {
            return 17;
        }

        @Override
        public int q8ColumnIndex() {
            return 18;
        }

        @Override
        public int q9ColumnIndex() {
            return 19;
        }

        @Override
        public int examColumnIndex() {
            return 20;
        }

        @Override
        public int remarkColumnIndex() {
            return 21;
        }

        @Override
        public int attendance() {
            return -1;
        }

        @Override
        public int onlineDiscussions() {
            return -1;
        }

        @Override
        public int continuousAssessment1() {
            return -1;
        }

        @Override
        public int continuousAssessment2() {
            return -1;
        }

        @Override
        public int totalCwOutOf40() {
            return -1;
        }

        @Override
        public int totalCwOutOf100() {
            return -1;
        }

        @Override
        public int totalExamOutOf60() {
            return -1;
        }

        @Override
        public int totalExamOutOf100() {
            return -1;
        }

        @Override
        public List<PrintDocumentType> printDocumentTypes() {
            return Arrays.asList(PrintDocumentType.TESTIMONIAL);
        }

        @Override
        public boolean testimonialHasStudentOption() {
            return false;
        }

        @Override
        public boolean transcriptHasStudentOption() {
            return false;
        }

        @Override
        public boolean hasProfilePictures() {
            return false;
        }

        @Override
        public boolean allowsResultsBeforeStudentIntake() {
            return false;
        }

    },

    UTAMU("UTAMU", "utamu", 1) {

        @Override
        public boolean hasStudentNumbers() {
            return false;
        }

        @Override
        public boolean hasCampuses() {
            return false;
        }

        @Override
        public int regNoColumnIndex() {
            return 1;
        }

        @Override
        public int studentNoColumnIndex() {
            return -1;
        }

        @Override
        public int nameColumnIndex() {
            return 2;
        }

        @Override
        public int finalMarkColumnIndex() {
            return 3;
        }

        @Override
        public int cw1ColumnIndex() {
            return 4;
        }

        @Override
        public int cw2ColumnIndex() {
            return 5;
        }

        @Override
        public int cw3ColumnIndex() {
            return 6;
        }

        @Override
        public int cwColumnIndex() {
            return 7;
        }

        @Override
        public int secAColumnIndex() {
            return 8;
        }

        @Override
        public int q1ColumnIndex() {
            return 9;
        }

        @Override
        public int q2ColumnIndex() {
            return 10;
        }

        @Override
        public int q3ColumnIndex() {
            return 11;
        }

        @Override
        public int q4ColumnIndex() {
            return 12;
        }

        @Override
        public int q5ColumnIndex() {
            return 13;
        }

        @Override
        public int q6ColumnIndex() {
            return 14;
        }

        @Override
        public int q7ColumnIndex() {
            return 15;
        }

        @Override
        public int q8ColumnIndex() {
            return 16;
        }

        @Override
        public int q9ColumnIndex() {
            return 17;
        }

        @Override
        public int examColumnIndex() {
            return 18;
        }

        @Override
        public int remarkColumnIndex() {
            return 19;
        }

        @Override
        public int attendance() {
            return 4;
        }

        @Override
        public int onlineDiscussions() {
            return 5;
        }

        @Override
        public int continuousAssessment1() {
            return 6;
        }

        @Override
        public int continuousAssessment2() {
            return 7;
        }

        @Override
        public int totalCwOutOf40() {
            return 8;
        }

        @Override
        public int totalCwOutOf100() {
            return 9;
        }

        @Override
        public int totalExamOutOf60() {
            return 10;
        }

        @Override
        public int totalExamOutOf100() {
            return 11;
        }

        @Override
        public List<PrintDocumentType> printDocumentTypes() {
            return Arrays.asList(PrintDocumentType.TESTIMONIAL, PrintDocumentType.TRANSCRIPT, PrintDocumentType.CERTIFICATE);
        }

        @Override
        public boolean testimonialHasStudentOption() {
            return true;
        }

        @Override
        public boolean transcriptHasStudentOption() {
            return true;
        }

        @Override
        public boolean hasProfilePictures() {
            return true;
        }

        @Override
        public boolean allowsResultsBeforeStudentIntake() {
            return true;
        }

    };

    private String name, prefix;
    private Integer ordinal;

    Setup(String name, String prefix, Integer ordinal) {
        this.name = name;
        this.prefix = prefix;
        this.ordinal = ordinal;
    }

    public String getName() {
        return this.name;
    }

    public String getPrefix() {
            return prefix;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public static Setup getByOrdinal(int ordinal) {
        for (Setup t : Setup.values()) {
            if (t.getOrdinal().equals(ordinal))
                return t;
        }
        return null;
    }

    public boolean testimonialHasStudentOption(){
        return testimonialHasStudentOption();
    }

}
