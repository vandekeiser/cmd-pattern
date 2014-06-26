package cla.command;

import cla.domain.carrots.Carrot;

public class TestEnv implements Env {

	@Override
	public Carrots carrots() {
		return new TestCarrots();
	}

	//--------test impls VVVVVVVVV
	public class TestCarrots implements Carrots {

		@Override public void addCarrot(Carrot carrot) {
			
		}

		@Override public int numberOfCarrots() {
			return 0;
		}

	}
	//--------test impls ^^^^^^^^^
}
