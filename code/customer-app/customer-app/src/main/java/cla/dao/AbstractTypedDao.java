package cla.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.Query;



public abstract class AbstractTypedDao<T> extends Dao {

	private final Class<T> type;
	
	public AbstractTypedDao(Class<T> type) {
		this.type = type;
	}
	
	public void persist(T entity) {
		em.persist(entity);
	}
	
	public T merge(T entity) {
		return em.merge(entity);
	}

	public List<T> findAll() {
		Query findAll = em.createQuery("from " + type.getSimpleName());
		List<?> all = findAll.getResultList();
		@SuppressWarnings("unchecked") List<T> allUnsafeCast = (List<T>)all;
		return Collections.checkedList(allUnsafeCast, type);
	}
	
	public void deleteAll() {
		Query deleteAll = em.createQuery("delete from " + type.getSimpleName());
		deleteAll.executeUpdate();
		em.clear();//Necessary because batch operations can't notify the EntityManager of changes
	}

	protected List<T> safeCast(List<?> untyped) {
		@SuppressWarnings("unchecked") List<T> unsafeCast = (List<T>)untyped;
		return Collections.checkedList(unsafeCast, type);
	}
}
