package cla.command.undo.compensation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cla.command.BaseCommandTest;
import cla.domain.carrots.CarrotCommandFactory;

public class CommandUndoTest_Compensation_Carrots_Test extends BaseCommandTest  {

	@Test public void undo() {
		CompensationConversation commands = new CompensationConversation();
		
		commands.exec(CarrotCommandFactory.addCarrot(repo));
		assertEquals(1, repo.numberOfCarrots());
		
		commands.undo();
		assertEquals(0, repo.numberOfCarrots());
	}
	
	@Test public void redo() {
		CompensationConversation commands = new CompensationConversation();
		
		commands.exec(CarrotCommandFactory.addCarrot(repo));
		assertEquals(1, repo.numberOfCarrots());
		
		commands.undo();
		assertEquals(0, repo.numberOfCarrots());
		
		commands.redo();
		assertEquals(1, repo.numberOfCarrots());
	}
	
	@Test public void redo_undo() {
		CompensationConversation commands = new CompensationConversation();
		
		commands.exec(CarrotCommandFactory.addCarrot(repo));
		assertEquals(1, repo.numberOfCarrots());
		
		commands.undo();
		assertEquals(0, repo.numberOfCarrots());
		
		commands.redo();
		assertEquals(1, repo.numberOfCarrots());
		
		commands.undo();
		assertEquals(0, repo.numberOfCarrots());
	}
	

	@Test public void nothingToUndo_Noop() {
		CompensationConversation commands = new CompensationConversation();
		commands.undo();
		assertEquals(0, repo.numberOfCarrots());
	}

}
