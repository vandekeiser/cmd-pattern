package cla.command.services;

import cla.domain.Env;
import cla.domain.carrots.Carrots;

public class TestEnv implements Env {

	private final Carrots carrots = new TestCarrots();
	
	@Override public Carrots carrots() {
		return carrots;
	}
}
