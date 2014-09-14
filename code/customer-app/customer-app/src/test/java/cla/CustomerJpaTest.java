package cla;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cla.domain.customer.Customer;


/**
 * Sanity check that persistence works
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Application.class)
@Transactional
public class CustomerJpaTest {

	@PersistenceContext	EntityManager em;
	
	@Test public void createdCustomerIsPersisted() {
		Customer p = new Customer("toto");
		em.persist(p);
		assertThat(em.contains(p)).isTrue();
	}
	
	//---------setup/teardown VVVVVVVV
	@Before public void setup() {
		Query findAll = em.createQuery("from Customer");
		List<?> allCustomers = findAll.getResultList();
		assertThat(allCustomers).isEmpty();
	}
	@Before public void teardown() {
		Query deleteAll = em.createQuery("delete from Customer");
		deleteAll.executeUpdate();
	}
	//---------setup/teardown ^^^^^^^^
	
}
