package cla;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cla.command.undo.memento.MementoConversation;
import cla.command.undo.memento.MementoableCommand;
import cla.dao.PotatoDao;
import cla.domain.potatoes.Potato;

//-FUCK JPA! essayer JDO2? 
//-clone on persist/xxx?
//-I always use a request scoped session and that makes the undo pretty easy: just throw an exception and the transaction gets rolled back immediately.
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Application.class)
@Transactional
public class PotatoJpaCommandTest {

	@Autowired PotatoDao dao;
	MementoConversation conversation; 
	
	@Test public void exec() {
		String race = "Ratte du touquet";
		MementoableCommand createPotato = createPotato(race); 
		
		conversation.exec(createPotato);
		assertThat(dao.potatoesOfRace(race)).hasSize(1);
	}
	
	@Test public void exec_exec() {
		String race = "Ratte du touquet";
		MementoableCommand createPotato = createPotato(race);//si on passe une instance de potato, on recree pas 2* la mm instance 
		
		conversation.exec(createPotato);
		assertThat(dao.potatoesOfRace(race)).hasSize(1);
		
		conversation.exec(createPotato);
		assertThat(dao.potatoesOfRace(race)).hasSize(2);
	}

	@Test public void undo() {
		String race = "Ratte du touquet";
		
		conversation.undo();
		assertThat(dao.potatoesOfRace(race)).hasSize(0);
	}
	
	@Test public void redo() {
		String race = "Ratte du touquet";
		
		conversation.undo();
		assertThat(dao.potatoesOfRace(race)).hasSize(0);
	}
	
	@Test public void execUndo() {
		String race = "Ratte du touquet";
		MementoableCommand createPotato = createPotato(race); 
		
		conversation.exec(createPotato);
		assertThat(dao.potatoesOfRace(race)).hasSize(1);
		
		conversation.undo();
		assertThat(dao.potatoesOfRace(race)).hasSize(0);
	}
	
	@Test public void execUndoRedo() {
		String race = "Ratte du touquet";
		MementoableCommand createPotato = createPotato(race); 
		
		conversation.exec(createPotato);
		assertThat(dao.potatoesOfRace(race)).hasSize(1);
		
		conversation.undo();
		assertThat(dao.potatoesOfRace(race)).hasSize(0);
		
		conversation.redo();
		assertThat(dao.potatoesOfRace(race)).hasSize(1);
	}
	
	private MementoableCommand createPotato(String race) {
		return PotatoesCommands.createPotato(race, dao);
	}

	//---------setup/teardown VVVVVVVVVV
	@Before public void setup() {
		List<Potato> allPotatoes = dao.findAll();
		assertThat(allPotatoes).isEmpty();
		conversation = new MementoConversation();
	}
	@Before public void teardown() {
		dao.deleteAll();
	}
	//---------setup/teardown ^^^^^^^^^^
	
}
