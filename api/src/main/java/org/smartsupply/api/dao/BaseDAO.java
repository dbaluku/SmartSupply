package org.smartsupply.api.dao;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import org.smartsupply.model.enums.RecordStatus;

import java.util.List;

public interface BaseDAO<T> extends GenericDAO<T, String> {

	public void setClazz(Class<T> clazzToSet);

	List<T> searchByPropertyEqual(String property, Object value);

	T searchUniqueByPropertyEqual(String property, Object value);

	List<T> searchByPropertyEqual(String property, Object value, RecordStatus recordStatus);

	List<T> searchByRecordStatus(RecordStatus recordStatus);

	T searchUniqueByPropertyEqual(String property, Object value, RecordStatus recordStatus);

	void delete(T entity);
}
