package cla.command.macro;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cla.command.BaseCommandTest;
import cla.command.Command;
import cla.domain.carrots.CarrotCommandFactory;

public class MacroCommandTest_Carrots_Test extends BaseCommandTest {

	@Test public void macro() {
		Command addCarrot = CarrotCommandFactory.addCarrot(); 
		addCarrot.execute(env);
		assertEquals(1, env.carrots().numberOfCarrots());
	}
	
}
