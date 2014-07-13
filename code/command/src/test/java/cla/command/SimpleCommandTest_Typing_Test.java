package cla.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cla.domain.typing.TypingCommandFactory;

public class SimpleCommandTest_Typing_Test extends BaseCommandTest {

	@Test public void basicCommandExecution_typeString() {
		Command typeString = TypingCommandFactory.typeString(display, "a"); 
		typeString.execute();
		assertEquals("a", display.displayed() );
	}
}
