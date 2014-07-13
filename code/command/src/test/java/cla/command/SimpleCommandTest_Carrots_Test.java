package cla.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cla.domain.carrots.CarrotCommandFactory;

public class SimpleCommandTest_Carrots_Test extends BaseCommandTest {

	@Test public void basicCommandExecution_addCarrot() {
		Command addCarrot = CarrotCommandFactory.addCarrot(); 
		addCarrot.execute(env);
		assertEquals(1, env.carrotRepository().numberOfCarrots() );
	}
	
}
