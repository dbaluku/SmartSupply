package org.smartsupply.api.utils;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.jarcommons.StringUtil;
import com.jarcommons.search.DateCond;
import org.smartsupply.api.Constants;
import org.smartsupply.model.BaseData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BuildSearchUtil {

    public static Search setPageNo(Search search, Integer pageNo) {
        if (pageNo != null) {
            search.setPage(pageNo > 0 ? pageNo - 1 : 0);
            search.setMaxResults(Constants.MAX_NUM_PAGE_RECORD);
        }
        return search;
    }

    public static void addStringFieldEqual(Search search, String field, String longValue, String separator) {
        String[] strings = longValue.split(separator);
        if (strings.length > 0) {
            search.addFilterIn(field, strings);
        }
    }

    public static void addEqual(Search search, String field, BaseData b) {
        if (b != null)
            search.addFilterEqual(field, b);
    }

    public static void addNotEqual(Search search, String field, BaseData b) {
        if (b != null)
            search.addFilterNotEqual(field, b);
    }

    public static void addEqual(Search search, String field, Double b) {
        if (b != null)
            search.addFilterEqual(field, b);
    }

    public static void addEqual(Search search, String field, Integer b) {
        if (b != null)
            search.addFilterEqual(field, b);
    }

    public static void addEqual(Search search, String field, Enum<?> value) {
        if (value != null)
            search.addFilterEqual(field, value);
    }

    public static void addEqual(Search search, String field, Boolean b) {
        if (b != null)
            search.addFilterEqual(field, b);
    }

    public static void addEqual(Search search, String field, String b) {
        if (StringUtil.isNotBlankOrEmpty(b))
            search.addFilterEqual(field, b);
    }

    public static void addFilterIn(Search search, String property, Collection<?> list) {
        if (list != null && list.size() > 0) {
            search.addFilterIn(property, list);
        }
    }

    public static void addDateCond(Search search, String field, DateCond cond) {
        List<Filter> condFilters = new ArrayList<>();
        if (cond != null) {
            if (cond.oneIsValid()) {
                condFilters.add(new Filter(field, cond.getValue1(), cond.getCond1().getFilterOption()));
            }
            if (cond.twoIsValid()) {
                condFilters.add(new Filter(field, cond.getValue2(), cond.getCond2().getFilterOption()));
            }
            search.addFilters(convertListToArray(condFilters));
        }
    }

    private static Filter[] convertListToArray(List<Filter> filters) {
        if (filters.size() > 0) {
            Filter[] filters1 = new Filter[filters.size()];
            for (int i = 0; i < filters.size(); i++) {
                filters1[i] = filters.get(i);
            }
            return filters1;
        }
        return new Filter[]{};
    }
}
