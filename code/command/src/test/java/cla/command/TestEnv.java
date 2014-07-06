package cla.command;

import java.util.HashSet;
import java.util.Set;

import cla.domain.carrots.Carrot;

public class TestEnv implements Env {

	private final Carrots carrots = new TestCarrots();
	
	@Override public Carrots carrots() {
		return carrots;
	}

	//--------test impls VVVVVVVVV
	class TestCarrots implements Carrots {

		Set<Carrot> carrots = new HashSet<>();
		
		@Override public void addCarrot(Carrot carrot) {
			carrots.add(carrot);
		}

		@Override public int numberOfCarrots() {
			return carrots.size();
		}

		@Override public void removeCarrot(Carrot carrot) {
			carrots.remove(carrot);
		}

	}
	//--------test impls ^^^^^^^^^
}
