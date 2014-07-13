package cla.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cla.domain.carrots.CarrotCommandFactory;

public class SimpleCommandTest_Carrots_Test extends BaseCommandTest {

	@Test public void basicCommandExecution_addCarrot() {
		Command addCarrot = CarrotCommandFactory.addCarrot(repo); 
		addCarrot.execute();
		assertEquals(1, repo.numberOfCarrots() );
	}
	
}
