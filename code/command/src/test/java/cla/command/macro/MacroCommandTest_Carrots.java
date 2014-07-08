package cla.command.macro;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cla.command.BaseCommandTest;
import cla.command.Command;
import cla.command.CommandFactory;

public class MacroCommandTest_Carrots extends BaseCommandTest {

	@Test public void macro() {
		Command addCarrot = CommandFactory.addCarrot(); 
		addCarrot.execute(env);
		assertEquals(1, env.carrots().numberOfCarrots());
	}
	
}
