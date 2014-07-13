package cla.command.undo.memento;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cla.command.BaseCommandTest;
import cla.domain.carrots.CarrotCommandFactory;

public class CommandUndoTest_Memento_Carrots_Test extends BaseCommandTest {

	@Test public void undo() {
		MementoConversation commands = new MementoConversation();
		
		commands.exec(CarrotCommandFactory.addCarrot(repo));
		assertEquals(1, repo.numberOfCarrots());
		
		commands.undo();
		assertEquals(0, repo.numberOfCarrots());
	}
	
	@Test public void undo_undo() {
		MementoConversation commands = new MementoConversation();
		
		commands.exec(CarrotCommandFactory.addCarrot(repo));
		assertEquals(1, repo.numberOfCarrots());
		
		commands.undo();
		assertEquals(0, repo.numberOfCarrots());
		
		commands.undo();
		assertEquals(0, repo.numberOfCarrots());
	}
	
	@Test public void undo_redo() {
		MementoConversation commands = new MementoConversation();
		
		commands.exec(CarrotCommandFactory.addCarrot(repo));
		assertEquals(1, repo.numberOfCarrots());
		
		commands.undo();
		assertEquals(0, repo.numberOfCarrots());
		
		commands.redo();
		assertEquals(1, repo.numberOfCarrots());
	}
	
	@Test public void undo_redo_undo() {
		MementoConversation commands = new MementoConversation();
		
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
		MementoConversation commands = new MementoConversation();
		commands.undo();
		assertEquals(0, repo.numberOfCarrots());
	}
	@Test public void nothingToUndoUndo_Noop() {
		MementoConversation commands = new MementoConversation();
		
		commands.undo();
		assertEquals(0, repo.numberOfCarrots());
		
		commands.undo();
		assertEquals(0, repo.numberOfCarrots());
	}
	
	@Test public void nothingToRedo_Noop() {
		MementoConversation commands = new MementoConversation();
		commands.redo();
		assertEquals(0, repo.numberOfCarrots());
	}
	@Test public void nothingToRedoRedo_Noop() {
		MementoConversation commands = new MementoConversation();
		commands.redo();
		assertEquals(0, repo.numberOfCarrots());
	}
		
}
