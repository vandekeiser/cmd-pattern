package cla.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SimpleCommandTest_Typing extends BaseCommandTest {

	@Test public void basicCommandExecution_typeString() {
		Command typeString = CommandFactory.typeString("a"); 
		typeString.execute(env);
		assertEquals("a", env.display().displayed() );
	}
}
