package org.smartsupply.api.dao.hibernateimpl;

import com.googlecode.genericdao.dao.jpa.GenericDAOImpl;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.jpa.JPASearchProcessor;
import org.apache.commons.lang.StringUtils;
import org.smartsupply.api.dao.BaseDAO;
import org.smartsupply.model.BaseData;
import org.smartsupply.model.enums.RecordStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("baseDAO")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class BaseDAOImpl<T> extends GenericDAOImpl<T, String> implements BaseDAO<T> {

    protected EntityManager entityManager;

    public BaseDAOImpl() {
    }

    @Override
    public void setClazz(Class<T> clazzToSet) {
        super.persistentClass = clazzToSet;
    }

    @PersistenceContext
    @Override
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
        this.entityManager = entityManager;
    }

    @Autowired
    @Override
    public void setSearchProcessor(JPASearchProcessor searchProcessor) {
        super.setSearchProcessor(searchProcessor);
    }

    @Override
    public List<T> searchByPropertyEqual(String property, Object value) {
        Search search = new Search();
        search.addFilterEqual(property, value);
        search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
        return search(search);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public T searchUniqueByPropertyEqual(String property, Object value) {
        Search search = new Search();
        search.addFilterEqual(property, value);
        search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
        return (T) searchUnique(search);
    }

    @Override
    public void delete(T entity) {
        if (entity != null) {
            if (entity instanceof BaseData) {
                ((BaseData) entity).setRecordStatus(RecordStatus.DELETED);
                super.save(entity);
            }
        }
    }

    @Override
    public List<T> searchByPropertyEqual(String property, Object value, RecordStatus recordStatus) {
        Search search = new Search();
        search.addFilterEqual(property, value);
        search.addFilterEqual("recordStatus", recordStatus);
        return search(search);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T searchUniqueByPropertyEqual(String property, Object value,
                                         RecordStatus recordStatus) {
        Search search = new Search();
        search.addFilterEqual(property, value);
        search.addFilterEqual("recordStatus", recordStatus);
        return (T) searchUnique(search);
    }

    @Override
    public List<T> searchByRecordStatus(RecordStatus recordStatus) {
        Search search = new Search();
        search.addFilterEqual("recordStatus", recordStatus);
        return search(search);
    }

    @Override
    public T save(T entity) {
        if (entity == null)
            return null;

        if (entity instanceof BaseData) {
            if (StringUtils.isBlank(((BaseData) entity).getId())) {
                ((BaseData) entity).setId(null);
            }
        }

        return super.save(entity);
    }

    @Override
    public void flush() {
        super.flush();
    }
}
