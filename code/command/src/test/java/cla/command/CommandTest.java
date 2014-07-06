package cla.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
