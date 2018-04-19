package org.smartsupply.api.service.impl.base;

import com.googlecode.genericdao.search.Search;
import org.smartsupply.api.service.baseclasses.BaseSearchService;
import org.smartsupply.api.utils.BuildSearchUtil;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class BaseSearchServiceImpl<ModelObject, SearchParams> extends BaseServiceImpl<ModelObject>
        implements BaseSearchService<ModelObject, SearchParams> {

    public BaseSearchServiceImpl() {
    }

    @Override
    @Transactional(readOnly = true)
    public ModelObject searchUniqueWithParams(SearchParams params) throws Exception {
        List<ModelObject> objects = searchWithParams(params);
        if (objects.size() == 0)
            return null;
        if (objects.size() == 1)
            return objects.get(0);
        else
            throw new Exception("Multiple records found matching search criteria");
    }

    @Override
    public List<ModelObject> searchWithParams(SearchParams params) throws Exception {
        return searchWithParams(params, null);
    }


    @Override
    @Transactional(readOnly = true)
    public List<ModelObject> searchWithParams(SearchParams params, Integer pageNo) throws Exception {
        Search search = prepareSearch(params, pageNo);
        return baseDAO.search(search);
    }

    @Override
    public Search prepareSearch(SearchParams params, Integer pageNo) throws Exception {
        Search search = new Search();
        search = BuildSearchUtil.setPageNo(search, pageNo);
        return search;
    }

    @Override
    @Transactional(readOnly = true)
    public int numberInSearch(SearchParams params) throws Exception {
        Search search = prepareSearch(params, null);
        return baseDAO.count(search);
    }
}

