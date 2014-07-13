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

import cla.command.undo.memento.Memento;
import cla.command.undo.memento.MementoConversation;
import cla.command.undo.memento.MementoableCommand;
import cla.dao.PotatoDao;
import cla.domain.potatoes.Potato;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Application.class)
@Transactional
public class PotatoJpaCommandTest {

	@Autowired PotatoDao dao;
	MementoConversation conversation; 
	
	@Test public void execUndoRedo() {
		String race = "Ratte du touquet";
		Potato ratte = new Potato(race);
		MementoableCommand createPotato = createPotato(ratte); 
		
		conversation.exec(createPotato);
		assertThat(dao.potatoesOfRace(race)).hasSize(1);
		
		conversation.undo();
		assertThat(dao.potatoesOfRace(race)).hasSize(0);
		
		conversation.redo();
		assertThat(dao.potatoesOfRace(race)).hasSize(1);
	}
	
	private MementoableCommand createPotato(Potato ratte) {
		return new MementoableCommand() {
			
			@Override public void execute() {
				dao.persist(ratte);				
			}
			
			@Override public Memento takeSnapshot() {
				List<Potato> allPotatoes = dao.findAll();
				return () -> {
					dao.deleteAll();
					allPotatoes.stream().forEach(p -> dao.merge(p));
				};
			}
		};
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
