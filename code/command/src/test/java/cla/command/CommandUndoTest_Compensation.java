package cla.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cla.command.undo.compensation.SequenceOfCommands_CompensationImpl;

public class CommandUndoTest_Compensation extends BaseCommandTest {

	@Test public void undo() {
		SequenceOfCommands_CompensationImpl commands = new SequenceOfCommands_CompensationImpl(env);
		
		commands.ddo(Commands.addCarrot());
		assertEquals(1, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
	
	@Test public void redo() {
		SequenceOfCommands_CompensationImpl commands = new SequenceOfCommands_CompensationImpl(env);
		
		commands.ddo(Commands.addCarrot());
		assertEquals(1, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
		
		commands.redo();
		assertEquals(1, env.carrots().numberOfCarrots());
	}
	
	@Test public void redo_undo() {
		SequenceOfCommands_CompensationImpl commands = new SequenceOfCommands_CompensationImpl(env);
		
		commands.ddo(Commands.addCarrot());
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
