package cla.command.undo.compensation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cla.command.BaseCommandTest;
import cla.command.CommandFactory;
import cla.command.undo.compensation.SequenceOfCommands_CompensationImpl;

public class CommandUndoTest_Compensation extends BaseCommandTest {

	@Test public void undo() {
		SequenceOfCommands_CompensationImpl commands = new SequenceOfCommands_CompensationImpl(env);
		
		commands.ddo(CommandFactory.addCarrot());
		assertEquals(1, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
	
	@Test public void redo() {
		SequenceOfCommands_CompensationImpl commands = new SequenceOfCommands_CompensationImpl(env);
		
		commands.ddo(CommandFactory.addCarrot());
		assertEquals(1, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
		
		commands.redo();
		assertEquals(1, env.carrots().numberOfCarrots());
	}
	
	@Test public void redo_undo() {
		SequenceOfCommands_CompensationImpl commands = new SequenceOfCommands_CompensationImpl(env);
		
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
		SequenceOfCommands_CompensationImpl commands = new SequenceOfCommands_CompensationImpl(env);
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
	
}
