package org.smartsupply.api.utils;

import org.smartsupply.api.service.baseclasses.BaseService;
import org.smartsupply.model.BaseData;
import org.smartsupply.model.exception.ValidationException;
import org.smartsupply.model.util.MyStringUtil;

import java.util.Date;
import java.util.List;

public class MyValidate {

    public static boolean isNotNull(Object object) {
        return !(object == null);
    }

    public static boolean isNotNullAll(Object... objects) {
        for (Object o : objects) {
            if (isNull(o)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotNullAny(Object... objects) {
        for (Object o : objects) {
            if (isNotNull(o)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNull(Object object) {
        return (object == null);
    }

    public static void disallowNull(Object object, String name) throws ValidationException {
        if (isNull(object))
            throw new ValidationException(name + " is required");
    }

    public static void disallowNoneNull(Object object, String name) throws ValidationException {
        if (isNotNull(object))
            throw new ValidationException(name + " is NOT required");
    }

    public static void disallowNullIfTrue(Object object, boolean condition, String fieldName, String conditionString) throws ValidationException {
        if (condition && object == null)
            throw new ValidationException(fieldName + " required since " + conditionString);
    }

    public static void disallowNoneNullIfTrue(Object object, boolean condition, String fieldName, String conditionString) throws ValidationException {
        if (condition && object != null)
            throw new ValidationException(fieldName + " NOT required since " + conditionString);
    }

    public static void disallowNullWithError(Object object, String error) throws ValidationException {
        if (object == null)
            throw new ValidationException(error);
    }

    public static void isNull(Object object, String name) throws ValidationException {
        if (object != null)
            throw new ValidationException(name + " is expected to be blank");
    }

    public static void isNotBlank(String str, String name) throws ValidationException {
        if (MyStringUtil.isBlankOrEmpty(str))
            throw new ValidationException(name + " can not be blank");
    }

    public static boolean isNotBlankOrEmpty(String str) {
        return MyStringUtil.isNotBlankOrEmpty(str);
    }

    public static void isNotFutureDate(Date date, String name) throws ValidationException {
        disallowNull(date, name);

        if (date.after(new Date()))
            throw new ValidationException(name + " can not be a future date");
    }

    public static void gt(Integer value, Integer limit, String name) throws ValidationException {
        disallowNull(value, name);
        if (value < limit)
            throw new ValidationException(name + " must be greater than " + limit);
    }

    public static void gt(Double value, Double limit, String name) throws ValidationException {
        disallowNull(value, name);

        if (value < limit || value.equals(limit))
            throw new ValidationException(name + " must be greater than "
                    + limit);
    }

    public static void gtWithMessage(Double value, Double limit, String error) throws ValidationException {
        if (isNull(value) || isNull(limit))
            throw new ValidationException("Invalid value(s) passed to validator");

        if (value < limit)
            throw new ValidationException(error);
    }

    public static boolean ge(Double value, Double limit) {
        return value.equals(limit) || value > limit;
    }

    public static void ge(Double value, Double limit, String name) throws ValidationException {
        if (!ge(value, limit))
            throw new ValidationException(name + " must be greater than or equal to " + limit);
    }

    public static void notNullAndGe(Double value, Double limit, String name) throws ValidationException {
        disallowNull(value, name);
        ge(value, limit, name);
    }

    public static void ifNotNullThenGE(Double value, Double limit, String name) throws ValidationException {
        if (isNotNull(value)) {
            if (value < limit) {
                throw new ValidationException(name + " can not be less than " + limit);
            }
        }
    }

    public static void ifNotNullThenGE_ForAll(Double limit, String name, String separator, Double... values) throws ValidationException {
        List<String> names = MyStringUtil.convertStringToList(name, separator);
        for (int i = 0; i < values.length; i++) {
            ifNotNullThenGE(values[i], limit, i < names.size() - 1 ? names.get(i) : "Some Field");
        }
    }


    public static void isNotGt(Integer value, Integer limit, String name) throws ValidationException {
        disallowNull(value, name);

        if (value > limit)
            throw new ValidationException(name + " must not be greater than " + limit);
    }

    public static void isNotGt(Double value, Double limit, String name) throws ValidationException {
        disallowNull(value, name);

        if (value > limit)
            throw new ValidationException(name + " must not be greater than " + limit);
    }

    public static void isNotLessThanZero(Integer value, String name) throws ValidationException {
        if (value < 0)
            throw new ValidationException(name + " can not be less than 0");
    }

    public static void isNotLessThanZero(Double value, String name)
            throws ValidationException {
        if (value < 0.0)
            throw new ValidationException(name + " can not be less than 0");
    }

    public static void isNotLessOrEqualToZero(Integer value, String name) throws ValidationException {
        if (value <= 0)
            throw new ValidationException(name + " can not be less or equal to 0");
    }

    public static void isNotLessOrEqualToZero(Double value, String name) throws ValidationException {
        if (value <= 0.0)
            throw new ValidationException(name + " can not be less or equal to 0");
    }

    public static void isInRange(Double value, Double start, Double end, String name) throws ValidationException {
        if (value != null && (value < start || value > end))
            throw new ValidationException(name + " can't be less than " + start + " or greater than " + end);
    }

    public static void isInRange(Double start, Double end, String name, String separator, Double... values) throws ValidationException {
        List<String> names = MyStringUtil.convertStringToList(name, separator);
        for (int i = 0; i < values.length; i++) {
            isInRange(values[i], start, end, i < names.size() - 1 ? names.get(i) : "Some Field");
        }
    }

    public static void isInRange(Integer value, Integer start, Integer end, String name) throws ValidationException {
        if (value != null && (value < start || value > end))
            throw new ValidationException(name + " can't be less than " + start + " or greater than " + end);
    }

    public static void isEqualTo(Double value, Double secondValue, String name) throws ValidationException {
        if (value != null && !value.equals(secondValue))
            throw new ValidationException(name + " must be equal to " + secondValue);
    }

    public static void validateUniqueProperty(BaseData existing, BaseData inComing, String objectName,
                                              String fieldName, String value) throws ValidationException {
        if (existing != null && (inComing.idIsBlankOrEmpty() || inComing.idIsNOTBlankOrEmpty() && !existing.equals(inComing))) {
            throw new ValidationException(objectName + " " + fieldName + " " + value + " already exists");
        }
    }

    public static <T extends BaseService, X extends BaseData> void uniqueStringFieldExists(
            String fieldValue, String fieldName, String fieldDisplayName, String objectDisplayName, X incoming, T service)
            throws ValidationException {
        Object existing = service.getByField(fieldName, fieldValue);
        if (existing != null && (incoming.idIsBlankOrEmpty() || !incoming.equals(existing))) {
            throw new ValidationException(objectDisplayName + " with " + fieldDisplayName + " " + fieldValue + " already exists");
        }
    }

    public static <T extends BaseService, X extends BaseData> void uniqueStringFieldExists(
            BaseData baseData, String fieldName, String fieldDisplayName, String objectDisplayName, X incoming, T service)
            throws ValidationException {
        Object existing = service.getByField(fieldName, baseData);
        if (existing != null && (incoming.idIsBlankOrEmpty() || !existing.equals(incoming))) {
            throw new ValidationException(objectDisplayName + " for " + fieldDisplayName + " " + baseData + " already exists");
        }
    }

    public static <T extends BaseService, X extends BaseData> void uniqueStringFieldExists(
            Object value, String fieldName, String fieldDisplayName, String objectDisplayName, X incoming, T service)
            throws ValidationException {
        Object existing = service.getByField(fieldName, value);
        if (existing != null && (incoming.idIsBlankOrEmpty() || !existing.equals(incoming))) {
            throw new ValidationException(objectDisplayName + " for " + fieldDisplayName + " " + value.toString() + " already exists");
        }
    }

    public static void uniqueStringFieldExists(String objectDisplayName, String uniqueValue, BaseData incoming, BaseData existing)
            throws ValidationException {
        if (existing != null && (incoming.idIsBlankOrEmpty() || !existing.equals(incoming))) {
            throw new ValidationException(objectDisplayName + " for " + uniqueValue + " " + " already exists");
        }
    }
}
