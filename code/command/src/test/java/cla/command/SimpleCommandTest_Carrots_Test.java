package cla.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SimpleCommandTest_Carrots_Test extends BaseCommandTest {

	@Test public void basicCommandExecution_addCarrot() {
		Command addCarrot = CommandFactory.addCarrot(); 
		addCarrot.execute(env);
		assertEquals(1, env.carrots().numberOfCarrots() );
	}
	
}
