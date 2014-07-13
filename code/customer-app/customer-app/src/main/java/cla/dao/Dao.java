package cla.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class Dao {
	
	@PersistenceContext EntityManager em;
	
	public boolean contains(Object entity) {
		return em.contains(entity);
	}
}
