package cla.command.undo.compensation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cla.command.BaseCommandTest;
import cla.command.CommandFactory;

public class CommandUndoTest_Compensation extends BaseCommandTest {

	@Test public void undo() {
		ConversationCompensationImpl commands = new ConversationCompensationImpl(env);
		
		commands.ddo(CommandFactory.addCarrot());
		assertEquals(1, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
	
	@Test public void redo() {
		ConversationCompensationImpl commands = new ConversationCompensationImpl(env);
		
		commands.ddo(CommandFactory.addCarrot());
		assertEquals(1, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
		
		commands.redo();
		assertEquals(1, env.carrots().numberOfCarrots());
	}
	
	@Test public void redo_undo() {
		ConversationCompensationImpl commands = new ConversationCompensationImpl(env);
		
		commands.ddo(CommandFactory.addCarrot());
		assertEquals(1, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
		
		commands.redo();
		assertEquals(1, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
	

	@Test public void nothingToUndo_Noop() {
		ConversationCompensationImpl commands = new ConversationCompensationImpl(env);
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
	
}
