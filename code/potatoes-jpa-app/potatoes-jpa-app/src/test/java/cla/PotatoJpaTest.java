package cla;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

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

import cla.domain.potatoes.Potato;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Application.class)
@Transactional
public class PotatoJpaTest {

	@PersistenceContext
	EntityManager em;
	
	@Test public void toto() {
		assertEquals(1, 1);
	}
	
	@Test public void createPotato_isInEm() {
		Potato p = new Potato();
		em.persist(p);
		assertThat(em.contains(p)).isTrue();
	}
	
	@Test public void createPotato_isInEm2() {
		Potato p = new Potato();
		em.persist(p);
		assertThat(em.contains(p)).isTrue();
	}
	
	//---------setup/teardown
	@Before public void setup() {
		Query findAll = em.createQuery("from Potato");
		List<?> allPotatoes = findAll.getResultList();
		assertThat(allPotatoes).isEmpty();
	}
	@Before public void teardown() {
		Query deleteAll = em.createQuery("delete from Potato");
		deleteAll.executeUpdate();
	}
	
}
