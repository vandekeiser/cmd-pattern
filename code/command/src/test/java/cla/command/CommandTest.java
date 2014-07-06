package cla.command;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cla.command.env.Env;
import cla.command.undo.UndoableCommands;
import cla.command.undo.stack.UndoableCommands_StackImpl;

public class CommandTest {

	private Env env;

	@Test public void addCarrot() {
		Command addCarrot = Commands.addCarrot(); 
		addCarrot.execute(env);
		assertEquals(1, env.carrots().numberOfCarrots() );
	}
	
	@Test public void macro() {
		Command addCarrot = Commands.addCarrot(); 
		addCarrot.execute(env);
		assertEquals(1, env.carrots().numberOfCarrots());
	}
	
	@Test public void undo() {
		UndoableCommands commands = new UndoableCommands_StackImpl(env);
		
		commands.ddo(Commands.addCarrot());
		assertEquals(1, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
	
	@Test public void redo() {
		UndoableCommands commands = new UndoableCommands_StackImpl(env);
		
		commands.ddo(Commands.addCarrot());
		assertEquals(1, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
		
		commands.redo();
		assertEquals(1, env.carrots().numberOfCarrots());
	}
	
	@Test public void redo_undo() {
		UndoableCommands commands = new UndoableCommands_StackImpl(env);
		
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
		UndoableCommands commands = new UndoableCommands_StackImpl(env);
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
	
	//--------setup/teardown VVVVVVVVV
	@Before public void setup() {
		env = new TestEnv();
		
		assertEquals(
				0, 
				env.carrots().numberOfCarrots()
		);
	}
	
	@After public void teardown() {
		
	}
	//--------setup/teardown ^^^^^^^^^
	
}
