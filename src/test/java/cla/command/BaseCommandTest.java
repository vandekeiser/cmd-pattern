package cla.command;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;

import cla.domain.typing.Display;

public abstract class BaseCommandTest {

	protected Display display;
	
	//--------setup/teardown VVVVVVVVV
	@Before public void setup() {
		display = new Display();
		assertEquals("", display.displayed());//sanity check
	}
	
	@After public void teardown() {
		
	}
	//--------setup/teardown ^^^^^^^^^
	
}
