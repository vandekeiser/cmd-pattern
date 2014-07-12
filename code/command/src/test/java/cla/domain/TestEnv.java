package cla.domain;

import cla.domain.carrots.CarrotRepository;
import cla.domain.carrots.TestCarrotRepository;
import cla.domain.typing.Display;
import cla.domain.typing.TestDisplay;

public class TestEnv implements Env {

	private final CarrotRepository carrots = new TestCarrotRepository();
	private final Display display = new TestDisplay();
	
	@Override public CarrotRepository carrots() {
		return carrots;
	}

	@Override public Display display() {
		return display;
	}
}
