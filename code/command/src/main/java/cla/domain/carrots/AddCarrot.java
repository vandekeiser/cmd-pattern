package cla.domain.carrots;

import java.util.Set;

import cla.command.undo.compensation.CompensableCommand;
import cla.command.undo.memento.Memento;
import cla.command.undo.memento.MementoableCommand;
import cla.domain.Env;

//TODO add generic add/remove/upd?
public class AddCarrot implements CompensableCommand, MementoableCommand {

	private Carrot addedCarrot;
	
	@Override public void execute(Env env) {
		addedCarrot = new Carrot();
		env.carrots().addCarrot(addedCarrot);
	}

	@Override public void compensate(Env env) {
		env.carrots().removeCarrot(addedCarrot);
	}

	@Override public Memento snapshotOf(Env env) {
		//allCarrots() guarantees it does a defensive copy, otherwise we would have to do a defensive copy here
		Set<Carrot> snapshot =  env.carrots().getAllCarrots();
		return (Env e) -> {e.carrots().setAllCarrots(snapshot);};
	}
	
	@Override public String toString() {
		return String.format("%s{addedCarrot:%s}", AddCarrot.class.getSimpleName(), addedCarrot);
	}
}