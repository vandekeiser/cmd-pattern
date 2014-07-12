package cla.command.services;

import cla.domain.Env;
import cla.domain.carrots.CarrotRepository;
import cla.domain.typing.Display;

public class TestEnv implements Env {

	private final CarrotRepository carrots = new TestCarrots();
	private final Display display = new TestDisplay();
	
	@Override public CarrotRepository carrots() {
		return carrots;
	}

	@Override public Display display() {
		return display;
	}
}
