package org.smartsupply.api.service.baseclasses;

import com.googlecode.genericdao.search.Search;

import java.util.List;

public interface BaseSearchService<ModelObject, SearchParams> extends BaseService<ModelObject> {

    ModelObject searchUniqueWithParams(SearchParams params) throws Exception;

    List<ModelObject> searchWithParams(SearchParams params) throws Exception;

    List<ModelObject> searchWithParams(SearchParams params, Integer pageNo) throws Exception;

    default Search prepareSearch(SearchParams params, Integer pageNo) throws Exception {
        return null;
    }

    int numberInSearch(SearchParams params) throws Exception;
}
