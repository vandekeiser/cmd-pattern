package cla.command;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;

import cla.domain.carrots.CarrotRepository;
import cla.domain.carrots.TestCarrotRepository;
import cla.domain.typing.Display;
import cla.domain.typing.TestDisplay;

public abstract class BaseCommandTest {

	protected CarrotRepository repo;
	protected Display display;
	
	//--------setup/teardown VVVVVVVVV
	@Before public void setup() {
		display = new TestDisplay();
		repo = new TestCarrotRepository();
		assertEquals(0, repo.numberOfCarrots());//sanity check
		assertEquals("", display.displayed());//sanity check
	}
	
	@After public void teardown() {
		
	}
	//--------setup/teardown ^^^^^^^^^
	
}
