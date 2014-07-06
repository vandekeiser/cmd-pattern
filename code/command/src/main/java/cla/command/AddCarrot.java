package cla.command;

import java.util.Set;

import cla.command.undo.compensation.CompensableCommand;
import cla.command.undo.snapshot.Restorable;
import cla.command.undo.snapshot.SnapshotableCommand;
import cla.domain.Env;
import cla.domain.carrots.Carrot;

//TODO add generic add/remove/upd?
public class AddCarrot implements CompensableCommand, SnapshotableCommand {

	private Carrot addedCarrot;
	
	@Override public void execute(Env env) {
		addedCarrot = new Carrot();
		env.carrots().addCarrot(addedCarrot);
	}

	@Override public void compensate(Env env) {
		env.carrots().removeCarrot(addedCarrot);
	}

	@Override public Restorable snapshot(Env env) {
		//allCarrots() guarantees it does a defensive copy, otherwise we would have to do a defensive copy here
		Set<Carrot> snapshot =  env.carrots().getAllCarrots();
		return (Env e) -> {e.carrots().setAllCarrots(snapshot);};
	}
	
}