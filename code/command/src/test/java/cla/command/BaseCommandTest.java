package cla.command;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;

import cla.domain.Env;
import cla.domain.TestEnv;

public abstract class BaseCommandTest {

	protected Env env;
	
	//--------setup/teardown VVVVVVVVV
	@Before public void setup() {
		env = new TestEnv();
		assertEquals(0, env.carrotRepository().numberOfCarrots());//sanity check
		assertEquals("", env.display().displayed());//sanity check
	}
	
	@After public void teardown() {
		
	}
	//--------setup/teardown ^^^^^^^^^
	
}
