package cla.domain.typing;

import java.util.List;

import cla.command.undo.compensation.CompensableCommand;
import cla.command.undo.memento.Memento;
import cla.command.undo.memento.MementoableCommand;
import cla.domain.Env;

//TODO add generic add/remove/upd?
public class TypeString implements CompensableCommand, MementoableCommand {

	private final String stringToType;
	
	public TypeString(String stringToType) {
		this.stringToType = stringToType;
	}

	@Override public void execute(Env env) {
		env.display().append(stringToType);
	}

	@Override public void compensate(Env env) {
		env.display().unappend();
	}

	@Override public Memento snapshotOf(Env env) {
		final List<String> snapshot =  env.display().getState();//snapshot is a defensive copy
		return e -> e.display().setState(snapshot);
	}
	
	@Override public String toString() {
		return String.format("%s{stringToType:%s}", TypeString.class.getSimpleName(), stringToType);
	}
	
}