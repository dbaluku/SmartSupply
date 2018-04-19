package org.smartsupply.api.service.impl.base;

import org.smartsupply.api.service.baseclasses.BaseQuickService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BaseQuickServiceImpl<ModelObject, SearchParams> extends BaseSearchServiceImpl<ModelObject, SearchParams>
        implements BaseQuickService<ModelObject, SearchParams> {

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public ModelObject getUnique(SearchParams params) throws Exception {
        List<ModelObject> results = get(params);
        if (results == null || results.size() == 0) {
            log.error("No matching object found");
            return null;
        }
        if (results.size() > 1) {
            throw new Exception("Multiple matching objects found");
        }
        return results.get(0);
    }

    @Override
    public List<ModelObject> get(SearchParams params) throws Exception {
        return get(params, null);
    }

    @Override
    public List<ModelObject> get(SearchParams params, Integer pageNo) throws Exception {
        return null;
    }

    @Override
    public int number(SearchParams params) throws Exception {
        List<ModelObject> objects = get(params);
        return objects != null ? objects.size() : 0;
    }
}
