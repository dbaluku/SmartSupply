package org.smartsupply.api.service.impl.base;

import com.googlecode.genericdao.search.Search;
import org.smartsupply.api.dao.BaseDAO;
import org.smartsupply.api.service.baseclasses.BaseService;
import org.smartsupply.api.service.baseclasses.FieldValue;
import org.smartsupply.api.utils.BuildSearchUtil;
import org.smartsupply.model.enums.RecordStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public abstract class BaseServiceImpl<ModelObject> implements BaseService<ModelObject> {

    @Autowired
    public BaseDAO<ModelObject> baseDAO;

    @Autowired
    public void setBaseDao(BaseDAO<ModelObject> daoToSet) {
        this.baseDAO = daoToSet;
    }

    public BaseServiceImpl() {
    }

    @Override
    public void validate(ModelObject modelObject) throws Exception {
    }

    @Override
    public void validateDelete(String[] ids) throws Exception {

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ModelObject save(ModelObject modelObject) throws Exception {
        return baseDAO.save(modelObject);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ModelObject[] merge(ModelObject[] modelObjects) {
        return baseDAO.merge(modelObjects);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public ModelObject getById(String id) {
        return getByField("id", id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public ModelObject getByField(String field, Object value) {
        return baseDAO.searchUniqueByPropertyEqual(field, value);
    }

    @Override
    public ModelObject getBy(List<FieldValue> fieldValues) throws Exception {
        Search search = new Search();
        for (FieldValue t : fieldValues) {
            search.addFilterEqual(t.getFieldName(), t.getValue());
        }
        return baseDAO.searchUnique(search);
    }

    @Override
    public List<ModelObject> getAllByField(String field, Object value) {
        return baseDAO.searchByPropertyEqual(field, value);
    }

    @Override
    public List<ModelObject> all() {
        return all(null);
    }

    @Override
    public List<ModelObject> all(Integer pageNo){
        Search search = new Search();
        search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
        addDefaultSort(search);
        search = BuildSearchUtil.setPageNo(search, pageNo);
        return baseDAO.search(search);
    }

    @Override
    public List<ModelObject> byIds(String[] ids){
        Search search = new Search();
        search.addFilterIn("id", ids);
        return baseDAO.search(search);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Integer countAll() {
        return baseDAO.count(new Search());
    }

    @Override
    public void deleteByIds(String[] ids) throws Exception {
        validateDelete(ids);
        baseDAO.removeByIds(ids);
    }


    @Override
    public void addDefaultSort(Search search) {

    }
}
