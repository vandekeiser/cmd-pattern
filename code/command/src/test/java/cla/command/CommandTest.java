package cla.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cla.command.undo.SequenceOfCommands;
import cla.command.undo.stack.SequenceOfCommands_CompensationImpl;

public class CommandTest extends BaseCommandTest {

	@Test public void undo() {
		SequenceOfCommands commands = new SequenceOfCommands_CompensationImpl(env);
		
		commands.ddo(Commands.addCarrot());
		assertEquals(1, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
	
	@Test public void redo() {
		SequenceOfCommands commands = new SequenceOfCommands_CompensationImpl(env);
		
		commands.ddo(Commands.addCarrot());
		assertEquals(1, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
		
		commands.redo();
		assertEquals(1, env.carrots().numberOfCarrots());
	}
	
	@Test public void redo_undo() {
		SequenceOfCommands commands = new SequenceOfCommands_CompensationImpl(env);
		
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
		SequenceOfCommands commands = new SequenceOfCommands_CompensationImpl(env);
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
	
}
