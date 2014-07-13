package cla.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import cla.domain.potatoes.Potato;

@Service
public class PotatoDao extends AbstractTypedDao<Potato>{

	public PotatoDao() { super(Potato.class); }

	public List<Potato> potatoesOfRace(String race) {
		Query findByName = em.createQuery("from Potato where race=:race");
		findByName.setParameter("race", race);
		List<?> soNamed = findByName.getResultList();
		return safeCast(soNamed);
	}

}
