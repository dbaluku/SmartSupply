package org.smartsupply.web.forms;

import org.smartsupply.model.BaseData;
import org.smartsupply.model.util.MyStringUtil;

public class KeyValue<E extends Enum> {

    private String key, strValue;
    private Integer intValue;
    private E enumValue;
    private BaseData baseData;
    private Boolean booleanValue;
    private Double doubleValue;

    //========================================================

    public KeyValue(String key, String strValue) {
        this.key = key;
        this.strValue = strValue;
    }

    public KeyValue(String key, Integer intValue) {
        this.key = key;
        this.intValue = intValue;
    }

    public KeyValue(String key, BaseData baseData) {
        this.key = key;
        this.baseData = baseData;
    }

    public KeyValue(String key, E enumValue) {
        this.key = key;
        this.enumValue = enumValue;
    }

    public KeyValue(String key, Boolean booleanValue) {
        this.key = key;
        this.booleanValue = booleanValue;
    }

    public KeyValue(String key, Double doubleValue) {
        this.key = key;
        this.doubleValue = doubleValue;
    }

    //========================================================

    public String getKey() {
        return key;
    }

    public String getStrValue() {
        return strValue;
    }

    public Boolean getBooleanValue() {
        return booleanValue;
    }

    public BaseData getBaseData() {
        return baseData;
    }

    public E getEnumValue() {
        return enumValue;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    //========================================================

    public boolean isStr() {
        return MyStringUtil.isNotBlankOrEmpty(strValue);
    }

    public Boolean isBoolean() {
        return booleanValue != null;
    }

    public Boolean isBaseData() {
        return baseData != null;
    }

    public Boolean isEnum() {
        return enumValue != null;
    }

    public Boolean isInt() {
        return intValue != null;
    }

    public Boolean isDouble() {
        return doubleValue != null;
    }

    private String getValueString() {
        if (isStr()) {
            return strValue;
        }
        if (isBaseData()) {
            return baseData.getId();
        }
        if (isBoolean()) {
            return booleanValue.toString();
        }
        if (isEnum()) {
            return enumValue.toString();
        }
        if (isInt()) {
            return intValue.toString();
        }
        if (isDouble()) {
            return doubleValue.toString();
        }

        return "";
    }

    @Override
    public String toString() {
        return key + " ";
    }
}
