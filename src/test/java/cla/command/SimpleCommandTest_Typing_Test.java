package cla.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cla.domain.typing.TypeString;

public class SimpleCommandTest_Typing_Test extends BaseCommandTest {

	
	/**
	 * Sanity check 
	 */
	@Test public void basicCommandExecution_typeString() {
		Command typeString = new TypeString(display, "a"); 
		typeString.execute();
		assertEquals("a", display.displayed() );
	}
}
