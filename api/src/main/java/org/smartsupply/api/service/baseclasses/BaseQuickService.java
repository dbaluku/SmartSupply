package org.smartsupply.api.service.baseclasses;

import java.util.List;

public interface BaseQuickService<ModelObject, SearchParams> extends BaseSearchService<ModelObject, SearchParams> {

    ModelObject getUnique(SearchParams params) throws Exception;

    List<ModelObject> get(SearchParams params) throws Exception;

    List<ModelObject> get(SearchParams params, Integer pageNo) throws Exception;

    default String buildQuery(SearchParams params, Integer pageNo) {
        return "";
    }

    int number(SearchParams params) throws Exception;
}
