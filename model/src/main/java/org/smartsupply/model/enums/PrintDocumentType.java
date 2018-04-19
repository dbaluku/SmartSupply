package org.smartsupply.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum PrintDocumentType {
    TESTIMONIAL("Testimonial", 0, "testimonial", false, true),
    TRANSCRIPT("Transcript", 1, "transcript", true, false),
    CERTIFICATE("Certificate", 2, "certificate", true, false);

    PrintDocumentType(String name, Integer ordinalValue, String code, boolean isGradDocument, boolean isTestimonial) {
        this.name = name;
        this.ordinalValue = ordinalValue;
        this.code = code;
        this.isGradDocument = isGradDocument;
        this.isTestimonial = isTestimonial;
    }

    private String name, code;
    private Integer ordinalValue;
    private boolean isGradDocument, isTestimonial;

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public Integer getOrdinalValue() {
        return ordinalValue;
    }

    public boolean isGradDocument() {
        return isGradDocument;
    }

    public boolean isTestimonial() {
        return isTestimonial;
    }

    public static List<PrintDocumentType> values(boolean isGradDocument) {
        List<PrintDocumentType> gradDocs = new ArrayList<>();
        for (PrintDocumentType t : PrintDocumentType.values()) {
            if (t.isGradDocument == isGradDocument) {
                gradDocs.add(t);
            }
        }
        return gradDocs;
    }

    public static PrintDocumentType getPrintDocumentType(Integer number) {
        for (PrintDocumentType status : PrintDocumentType.values()) {
            if (status.getOrdinalValue().equals(number)) {
                return status;
            }
        }
        return null;
    }
}
