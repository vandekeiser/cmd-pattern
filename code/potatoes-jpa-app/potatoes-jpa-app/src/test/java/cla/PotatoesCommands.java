package cla;

import java.util.List;

import cla.command.undo.memento.Memento;
import cla.command.undo.memento.MementoableCommand;
import cla.dao.PotatoDao;
import cla.domain.potatoes.Potato;

public class PotatoesCommands {

	public static MementoableCommand createPotato(String race, PotatoDao dao) {
		return new MementoableCommand() {
			@Override public void execute() {
				dao.persist(new Potato(race));				
			}
			
			@Override public Memento takeSnapshot() {
				List<Potato> allPotatoes = dao.findAll();
				return () -> {
					dao.deleteAll();
					allPotatoes.stream().forEach(p -> dao.merge(p));
					//allPotatoes.stream().forEach(p -> dao.persist(p));//KO: PersistentObjectException: detached entity passed to persist
				};
			}
		};
	}

}
