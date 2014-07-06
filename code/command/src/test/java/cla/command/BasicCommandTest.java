package cla.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BasicCommandTest extends BaseCommandTest {

	@Test public void basicCommandExecution() {
		Command addCarrot = Commands.addCarrot(); 
		addCarrot.execute(env);
		assertEquals(1, env.carrots().numberOfCarrots() );
	}
	
	
}
