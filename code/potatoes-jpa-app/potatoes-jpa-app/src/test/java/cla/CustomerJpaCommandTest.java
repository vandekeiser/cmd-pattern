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
import cla.dao.CustomerDao;
import cla.domain.potatoes.Customer;

//-FUCK JPA! essayer JDO2? 
//-clone on persist/xxx?
//-I always use a request scoped session and that makes the undo pretty easy: just throw an exception and the transaction gets rolled back immediately.
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Application.class)
@Transactional
public class CustomerJpaCommandTest {

	@Autowired CustomerDao dao;
	MementoConversation conversation; 
	
	@Test public void exec() {
		String name = "Tartempion";
		MementoableCommand createCustomer = createCustomer(name); 
		
		conversation.exec(createCustomer);
		assertThat(dao.potatoesOfRace(name)).hasSize(1);
	}
	
	@Test public void exec_exec() {
		String name = "Tartempion";
		MementoableCommand createCustomer = createCustomer(name);//si on passe une instance de potato, on recree pas 2* la mm instance 
		
		conversation.exec(createCustomer);
		assertThat(dao.potatoesOfRace(name)).hasSize(1);
		
		conversation.exec(createCustomer);
		assertThat(dao.potatoesOfRace(name)).hasSize(2);
	}

	@Test public void undo() {
		String name = "Tartempion";
		
		conversation.undo();
		assertThat(dao.potatoesOfRace(name)).hasSize(0);
	}
	
	@Test public void redo() {
		String name = "Tartempion";
		
		conversation.undo();
		assertThat(dao.potatoesOfRace(name)).hasSize(0);
	}
	
	@Test public void execUndo() {
		String name = "Tartempion";
		MementoableCommand createCustomer = createCustomer(name); 
		
		conversation.exec(createCustomer);
		assertThat(dao.potatoesOfRace(name)).hasSize(1);
		
		conversation.undo();
		assertThat(dao.potatoesOfRace(name)).hasSize(0);
	}
	
	@Test public void execUndoRedo() {
		String name = "Tartempion";
		MementoableCommand createCustomer = createCustomer(name); 
		
		conversation.exec(createCustomer);
		assertThat(dao.potatoesOfRace(name)).hasSize(1);
		
		conversation.undo();
		assertThat(dao.potatoesOfRace(name)).hasSize(0);
		
		conversation.redo();
		assertThat(dao.potatoesOfRace(name)).hasSize(1);
	}
	
	private MementoableCommand createCustomer(String name) {
		return CustomerCommands.createCustomer(name, dao);
	}

	//---------setup/teardown VVVVVVVVVV
	@Before public void setup() {
		List<Customer> allCustomers = dao.findAll();
		assertThat(allCustomers).isEmpty();
		conversation = new MementoConversation();
	}
	@Before public void teardown() {
		dao.deleteAll();
	}
	//---------setup/teardown ^^^^^^^^^^
	
}
