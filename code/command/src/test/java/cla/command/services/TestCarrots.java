package cla.command.services;

import java.util.HashSet;
import java.util.Set;

import cla.domain.carrots.Carrot;
import cla.domain.carrots.Carrots;

//@NotThreadSafe //OK for tests
class TestCarrots implements Carrots {

	private final Set<Carrot> carrots = new HashSet<>();
	
	@Override public void addCarrot(Carrot carrot) {
		carrots.add(carrot);
	}

	@Override public int numberOfCarrots() {
		return carrots.size();
	}

	@Override public void removeCarrot(Carrot carrot) {
		carrots.remove(carrot);
	}

	@Override public Set<Carrot> getAllCarrots() {
		return new HashSet<>(carrots);
	}

	@Override public void setAllCarrots(Set<Carrot> newState) {
		carrots.clear();
		carrots.addAll(newState);
	}

}
