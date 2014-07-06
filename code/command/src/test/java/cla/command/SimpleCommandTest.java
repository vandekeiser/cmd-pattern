package cla.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SimpleCommandTest extends BaseCommandTest {

	@Test public void basicCommandExecution() {
		Command addCarrot = CommandFactory.addCarrot(); 
		addCarrot.execute(env);
		assertEquals(1, env.carrots().numberOfCarrots() );
	}
	
	
}
