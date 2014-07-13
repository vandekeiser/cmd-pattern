package cla.domain.carrots;

import java.util.Set;

import cla.command.undo.compensation.CompensableCommand;
import cla.command.undo.memento.Memento;
import cla.command.undo.memento.MementoableCommand;

//TODO add generic add/remove/upd?
public class AddCarrot implements CompensableCommand, MementoableCommand {

	private final CarrotRepository repository;
	private Carrot addedCarrot;
	
	public AddCarrot(CarrotRepository repository) {
		this.repository = repository;
	}
	
	@Override public void execute() {
		addedCarrot = new Carrot();
		repository.addCarrot(addedCarrot);
	}

	@Override public void compensate() {
		repository.removeCarrot(addedCarrot);
	}

	@Override public Memento takeSnapshot() {
		//allCarrots() guarantees it does a defensive copy, otherwise we would have to do a defensive copy here
		Set<Carrot> snapshot =  repository.getAllCarrots();
		return () -> {repository.setAllCarrots(snapshot);};
	}
	
	@Override public String toString() {
		return String.format("%s{addedCarrot:%s}", AddCarrot.class.getSimpleName(), addedCarrot);
	}
}