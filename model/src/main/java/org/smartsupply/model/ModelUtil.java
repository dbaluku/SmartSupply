package org.smartsupply.model;


import org.smartsupply.model.search.BaseSearchParams;

public class ModelUtil {
    public static <T extends BaseSearchParams> void copySearchParams(T dest, T source) {
        source.copyTo(dest);
    }

}
