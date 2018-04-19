package org.smartsupply.web.forms;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.smartsupply.model.BaseData;
import org.smartsupply.model.enums.StudentStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericCommand {

    @SuppressWarnings("unchecked")
    private Map<String, GenericCommandValue> propertiesMap = MapUtils.lazyMap(
            new HashMap<String, GenericCommandValue>(),
            FactoryUtils.instantiateFactory(GenericCommandValue.class));

    public Map<String, GenericCommandValue> getPropertiesMap() {
        return propertiesMap;
    }

    public void setPropertiesMap(Map<String, GenericCommandValue> propertiesMap) {
        this.propertiesMap = propertiesMap;
    }

    public String getValue(String key) {
        if (this.propertiesMap != null) {
            GenericCommandValue commandValue = this.propertiesMap.get(key);
            if (commandValue != null)
                return commandValue.getValue();
        }

        return null;
    }

    public List<StudentStatus> getStudentStatuses(String key) {
        if (this.propertiesMap != null) {
            GenericCommandValue commandValue = this.propertiesMap.get(key);
            if (commandValue != null)
                return commandValue.getStudentStatuses();
        }

        return null;
    }

    public Boolean getBooleanValue(String key) {
        if (this.propertiesMap != null) {
            GenericCommandValue commandValue = this.propertiesMap.get(key);
            if (commandValue != null)
                return commandValue.getBooleanValue();
        }

        return null;
    }

    public <E extends Enum<E>> E getAsEnumValue(Class<E> clazz, String key) {
        if (clazz == null || this.isBlank(key) || this.propertiesMap == null) {
            return null;
        }
        if (this.propertiesMap != null) {
            GenericCommandValue commandValue = this.propertiesMap.get(key);
            if (commandValue != null)
                return commandValue.getEnumValue(clazz);
        }
        return null;
    }

    public Integer getIntValue(String key) {

        if (this.isBlank(key))
            return null;

        if (this.propertiesMap != null) {
            GenericCommandValue commandValue = this.propertiesMap.get(key);
            if (commandValue != null)
                return commandValue.getIntValue();
        }

        return null;
    }

    public Integer getIntValue(String key, Integer defaultReturnValue) {
        Integer intValue = getIntValue(key);
        return intValue == null ? defaultReturnValue : intValue;
    }

    public Boolean isNotBlank(String key) {
        String value = "";

        if (this.propertiesMap != null) {
            GenericCommandValue commandValue = this.propertiesMap.get(key);
            if (commandValue != null)
                value = commandValue.getValue();
        }

        return StringUtils.isNotBlank(value);
    }

    public Boolean isBlank(String key) {
        String value = "";

        if (this.propertiesMap != null) {
            GenericCommandValue commandValue = this.propertiesMap.get(key);
            if (commandValue != null)
                value = commandValue.getValue();
        }

        return StringUtils.isBlank(value);
    }

    public void checkAndPut(KeyValue... keyValues) {
        for (KeyValue keyValue : keyValues) {
            if (keyValue.isStr()) {
                checkAndPut(keyValue.getKey(), keyValue.getStrValue());
                continue;
            }
            if (keyValue.isBaseData()) {
                checkAndPut(keyValue.getKey(), keyValue.getBaseData());
                continue;
            }
            if (keyValue.isBoolean()) {
                checkAndPut(keyValue.getKey(), keyValue.getBooleanValue());
                continue;
            }
            if (keyValue.isEnum()) {
                checkAndPut(keyValue.getKey(), keyValue.getEnumValue());
                continue;
            }
            if (keyValue.isInt()) {
                checkAndPut(keyValue.getKey(), keyValue.getIntValue());
                continue;
            }
            if (keyValue.isDouble()) {
                checkAndPut(keyValue.getKey(), keyValue.getDoubleValue());
                continue;
            }
        }
    }

    public void checkAndPut(String key, String value) {
        if (StringUtils.isNotBlank(value))
            this.getPropertiesMap().put(key, new GenericCommandValue(value));
    }

    public void checkAndPut(String key, Double value) {
        if (value != null)
            this.getPropertiesMap().put(key, new GenericCommandValue(value.toString()));
    }

    public void checkAndPut(String key, Integer value) {
        if (value != null)
            this.getPropertiesMap().put(key, new GenericCommandValue(value.toString()));
    }

    public void checkAndPut(String key, Boolean value) {
        if (value != null)
            this.getPropertiesMap().put(key, new GenericCommandValue(value.toString()));
    }

    public void checkAndPut(String key, BaseData value) {
        if (value != null)
            this.getPropertiesMap().put(key, new GenericCommandValue(value.getId()));
    }

    public void checkAndPut(String key, Enum<?> value) {
        if (null != value )
            this.getPropertiesMap().put(key, new GenericCommandValue(value.toString()));
    }

    // =======================================================================

    /**
     * puts a given value into the command object with the given key
     *
     * @param key
     * @param value
     */
    public static void checkAndAppend(String key, String value, StringBuffer buffer) {
        if (StringUtils.isNotBlank(value))
            buffer.append("&").append(key).append("=").append(value);
    }

    public static void checkAndAppend(String key, Double value, StringBuffer buffer) {
        if (value != null)
            buffer.append("&").append(key).append("=").append(value.toString());
    }

    public static void checkAndAppend(String key, Integer value, StringBuffer buffer) {
        if (value != null)
            buffer.append("&").append(key).append("=").append(value.toString());
    }

    public static void checkAndAppend(String key, Boolean value, StringBuffer buffer) {
        if (value != null)
            buffer.append("&").append(key).append("=").append(value.toString());
    }

    public static void checkAndAppend(String key, BaseData value, StringBuffer buffer) {
        if (value != null)
            buffer.append("&").append(key).append("=").append(value.getId());
    }

    public static void checkAndAppend(String key, Enum<?> enumValue, StringBuffer buffer) {
        if (null != enumValue)
            buffer.append("&").append(key).append("=").append(enumValue);
    }

}