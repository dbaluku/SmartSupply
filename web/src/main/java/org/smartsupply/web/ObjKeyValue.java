package org.smartsupply.web;

import java.util.ArrayList;
import java.util.List;

public class ObjKeyValue {
    public String key;
    public Object value;

    public ObjKeyValue(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public static List<ObjKeyValue> ObjKeyValue(List<Object> objects) {
        List<ObjKeyValue> keyValues = new ArrayList<>();
        for (int i = 0; i < objects.size(); i += 2) {
            keyValues.add(new ObjKeyValue((String) objects.get(i), objects.get(i + 1)));
        }
        return keyValues;
    }
}
