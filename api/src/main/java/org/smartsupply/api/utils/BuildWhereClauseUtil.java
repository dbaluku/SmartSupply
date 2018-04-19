package org.smartsupply.api.utils;

import com.jarcommons.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.smartsupply.api.Constants;
import org.smartsupply.api.service.baseclasses.BaseServiceClass;
import org.smartsupply.model.BaseData;
import org.smartsupply.model.util.MyStringUtil;

import java.util.ArrayList;
import java.util.List;

import static org.smartsupply.model.util.MyStringUtil.checkAndAddPercent;

public class BuildWhereClauseUtil {

    public static void addFloor(StringBuilder whereClause, String field, Double number) {
        if (null == number)
            return;
        addAndToWhereClause(whereClause);
        whereClause.append(" " + field + " >= " + number + " ");
    }

    public static void addCeiling(StringBuilder whereClause, String field, Double number) {
        if (null == number)
            return;
        addAndToWhereClause(whereClause);
        whereClause.append(" " + field + " <= " + number + " ");
    }

    public static void addFloorAndCeiling(StringBuilder whereClause, String field, Double from, Double to) {
        addFloor(whereClause, field, from);
        addCeiling(whereClause, field, to);
    }

    public static void addStringLike(StringBuilder whereClause, String field, String string) {
        if (StringUtils.isNotBlank(string)) {
            addAndToWhereClause(whereClause);
            whereClause.append(" " + field + " like '" + checkAndAddPercent(string) + "' ");
        }
    }

    public static void addStringILike(StringBuilder whereClause, String field, String string) {
        if (StringUtils.isNotBlank(string)) {
            string = checkAndAddPercent(string);
            addAndToWhereClause(whereClause);
            whereClause.append(" " + field + BaseServiceClass.getILikeComparator() + " '" + string + "' ");
        }
    }

    public static void addStringsILike(
            StringBuilder whereClause, String fieldsString, String fieldSeparator, String string) {

        if (StringUtils.isNotBlank(fieldsString)
                && StringUtils.isNotBlank(string)) {

            String[] fields = fieldsString.split(fieldSeparator);

            // check if the string already has the '%' signs
            string = checkAndAddPercent(string);

            addAndToWhereClause(whereClause);

            if (fields.length > 1)
                for (int i = 0; i < fields.length; i++) {
                    whereClause.append((i == 0 ? " ( " : " or ") + fields[i] + BaseServiceClass.getILikeComparator() + " '" + string + "' ");
                }

            if (fields.length > 1)
                whereClause.append(" ) ");
        }
    }

    public static void addStringFieldsNOTEqual(
            StringBuilder whereClause, String field, String valueString, String valueSeparator) {

        if (StringUtils.isNotBlank(field)) {

            addAndToWhereClause(whereClause);
            whereClause.append(" " + field + " not in ('"
                    + valueString.replace(valueSeparator, "', '") + "') ");
        }
    }

    public static void addFieldInSubSelect(StringBuilder whereClause, String field, String subSelect) {

        if (StringUtils.isNotBlank(field) && MyStringUtil.isNotBlankOrEmpty(subSelect)) {
            addAndToWhereClause(whereClause);
            whereClause.append(" " + field + " in (" + subSelect + ")");
        }
    }

    public static void addStringFieldsNOTEqual(
            StringBuilder whereClause, String field, List<BaseData> values) {

        String separator = "---";
        String ids = "";

        for (BaseData t : values)
            ids += StringUtils.isBlank(ids) ? t.getId() : separator + t.getId();

        addStringFieldsNOTEqual(whereClause, field, ids, separator);
    }

    public static void addStringFieldEquals(
            StringBuilder whereClause, String field, String valueString, String valueSeparator) {

        if (StringUtil.isNotBlankOrEmpty(field) && StringUtil.isNotBlankOrEmpty(valueString)) {
            addAndToWhereClause(whereClause);
            whereClause.append(" " + field + " IN ('" + valueString.replace(valueSeparator, "', '") + "') ");
        }
    }

    public static List<Integer> getEnumOrdinalValues(List<Enum<?>> values) {
        List<Integer> ordinalValues = new ArrayList<>();
        for (Enum<?> value : values) {
            ordinalValues.add(value.ordinal());
        }
        return ordinalValues;
    }


    public static <T extends BaseData> void addStringFieldEquals(StringBuilder whereClause, String field, List<T> values) {
        if (values != null && values.size() > 0) {
            String separator = "---";
            String ids = "";
            for (BaseData t : values) {
                ids += StringUtils.isBlank(ids) ? t.getId() : separator + t.getId();
            }

            addStringFieldEquals(whereClause, field, ids, separator);
        }
    }

    public static <T extends BaseData> void addStringFieldsEqual2(StringBuilder whereClause, String field, List<T> list) {

        StringBuilder orClause = new StringBuilder();

        if (list != null && list.size() > 0) {

            for (int i = 0; i < list.size(); i++) {
                if (i > 0) {
                    addOrToWhereClause(orClause);
                }
                if (list.get(i).hasId()) {
                    orClause.append(" " + field + " = '" + list.get(i).getId() + "' ");
                }
            }
        }

        if(StringUtil.isNotBlankOrEmpty(orClause.toString())) {
            addAndToWhereClause(whereClause);
            addBracketToWhereClause(whereClause, true);
            whereClause.append(orClause.toString());
            addBracketToWhereClause(whereClause, false);
        }

    }

    public static void addOrToWhereClause(StringBuilder whereClause) {
        if (StringUtil.isNotBlankOrEmpty(whereClause.toString()))
            whereClause.append(" or ");
    }

    public static void addAndToWhereClause(StringBuilder whereClause) {
        if (StringUtils.isNotBlank(whereClause.toString()))
            whereClause.append(" and ");
    }

    public static void addBracketToWhereClause(StringBuilder whereClause, boolean opening) {
        whereClause.append(opening ? " ( " : " ) ");
    }

    public static void addEqual(StringBuilder whereClause, String field, BaseData b) {
        if (b != null) {
            addAndToWhereClause(whereClause);
            whereClause.append(" " + field + " = '" + b.getId() + "' ");
        }
    }

    public static void addEqual(StringBuilder whereClause, String field, Boolean b) {
        if (b != null) {
            addAndToWhereClause(whereClause);
            whereClause.append(" " + field + " = " + b + " ");
        }
    }

    public static void addEqual(StringBuilder whereClause, String field, Integer b) {
        if (b != null) {
            addAndToWhereClause(whereClause);
            whereClause.append(" " + field + " = '" + b + "' ");
        }
    }

    public static void addEqual(StringBuilder whereClause, String field, List<Integer> list) {
        if (list != null && list.size() > 0) {
            addAndToWhereClause(whereClause);
            addBracketToWhereClause(whereClause, true);
            for (int i = 0; i < list.size(); i++) {
                if (i > 0) {
                    addOrToWhereClause(whereClause);
                }
                whereClause.append(" " + field + " = " + list.get(i) + " ");
            }
            addBracketToWhereClause(whereClause, false);
        }
    }

    public static void addEqual(StringBuilder whereClause, String field, String b) {
        if (b != null) {
            addAndToWhereClause(whereClause);
            whereClause.append(" " + field + " = '" + b + "' ");
        }
    }

    public static void addNotEqual(StringBuilder whereClause, String field, BaseData b) {
        if (b != null) {
            addAndToWhereClause(whereClause);
            whereClause.append(" " + field + " != '" + b.getId() + "' ");
        }
    }

    public static String getPagingString(Integer pageNo) {
        if (pageNo == null || pageNo < 0) {
            return "";
        }

        if (pageNo > 0) {
            pageNo -= 1;
        }

        String str = " LIMIT " + Constants.MAX_NUM_PAGE_RECORD + " OFFSET "
                + Constants.MAX_NUM_PAGE_RECORD * pageNo;

        return str;
    }

    public static void addFieldIsNull(StringBuilder whereClause, String field) {
        addAndToWhereClause(whereClause);
        whereClause.append(" " + field + " is null");
    }

    public static void addFieldIsNotNull(StringBuilder whereClause, String field) {
        addAndToWhereClause(whereClause);
        whereClause.append(" " + field + " is not null");
    }

    public static void addFieldIsNull(StringBuilder whereClause, String field, Boolean condition) {
        if (condition != null) {
            if (condition) {
                addFieldIsNull(whereClause, field);
            } else {
                addFieldIsNotNull(whereClause, field);
            }
        }
    }

    public static void addFieldIsNotNull(StringBuilder whereClause, String field, Boolean condition) {
        if (condition != null) {
            if (condition) {
                addFieldIsNotNull(whereClause, field);
            } else {
                addFieldIsNull(whereClause, field);
            }
        }
    }
}
