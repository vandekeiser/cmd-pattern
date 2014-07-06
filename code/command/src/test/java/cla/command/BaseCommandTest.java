package cla.command;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;

import cla.command.env.Env;

public abstract class BaseCommandTest {

	protected Env env;
	
	//--------setup/teardown VVVVVVVVV
	@Before public void setup() {
		env = new TestEnv();
		assertEquals(0, env.carrots().numberOfCarrots());//sanity check
	}
	
	@After public void teardown() {
		
	}
	//--------setup/teardown ^^^^^^^^^
	
}
