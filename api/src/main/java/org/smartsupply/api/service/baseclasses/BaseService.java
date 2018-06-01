package org.smartsupply.api.service.baseclasses;

import com.googlecode.genericdao.search.Search;
import org.smartsupply.model.search.OrgUnitSearchParams;

import java.util.ArrayList;
import java.util.List;

public interface BaseService<ModelObject> {

    ModelObject save(ModelObject t) throws Exception;

    default ModelObject[] merge(ModelObject[] modelObjects) {
        return null;
    }

    void validate(ModelObject t) throws Exception;

    ModelObject getById(String id);

    List<ModelObject> byIds(String[] ids);

    default ModelObject getByField(String field, Object value) {
        return null;
    }

    default ModelObject getBy(List<FieldValue> fieldValues) throws Exception {
        return null;
    }

    default List<ModelObject> getAllByField(String field, Object value) {
        return new ArrayList<>();
    }

    List<ModelObject> all();

    List<ModelObject> all(Integer pageNo);

    void addDefaultSort(Search search);

    Integer countAll();

    void deleteByIds(String[] ids) throws Exception;

    void validateDelete(String[] ids) throws Exception;


}
