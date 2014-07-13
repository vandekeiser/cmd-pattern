package cla.command.macro;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cla.command.BaseCommandTest;
import cla.command.Command;
import cla.domain.carrots.CarrotCommandFactory;

public class MacroCommandTest_Carrots_Test extends BaseCommandTest {

	@Test public void macro() {
		Command addCarrot = CarrotCommandFactory.addCarrot(repo); 
		addCarrot.execute();
		assertEquals(1, repo.numberOfCarrots());
	}
	
}
