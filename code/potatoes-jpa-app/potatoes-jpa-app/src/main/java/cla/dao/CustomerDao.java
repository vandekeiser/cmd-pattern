package cla.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import cla.domain.potatoes.Customer;

@Service
public class CustomerDao extends AbstractTypedDao<Customer>{

	public CustomerDao() { super(Customer.class); }

	public List<Customer> potatoesOfRace(String name) {
		Query findByName = em.createQuery("from Customer where name=:name");
		findByName.setParameter("name", name);
		List<?> soNamed = findByName.getResultList();
		return safeCast(soNamed);
	}

}
