package cla.command.services;

import cla.domain.Display;
import cla.domain.Env;
import cla.domain.carrots.Carrots;

public class TestEnv implements Env {

	private final Carrots carrots = new TestCarrots();
	private final Display display = new TestDisplay();
	
	@Override public Carrots carrots() {
		return carrots;
	}

	@Override public Display display() {
		return display;
	}
}
