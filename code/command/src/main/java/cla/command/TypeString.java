package cla.command;

import cla.command.undo.compensation.CompensableCommand;
import cla.command.undo.snapshot.Restorable;
import cla.command.undo.snapshot.SnapshotableCommand;
import cla.domain.Env;

//TODO add generic add/remove/upd?
public class TypeString implements CompensableCommand, SnapshotableCommand {

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

	@Override public Restorable snapshot(Env env) {
		String snapshot =  env.display().displayed();
		System.out.println("TypeString/snapshot: " + snapshot);
		return (Env e) -> {e.display().setDisplay(snapshot);};
	}
	
	@Override public String toString() {
		return String.format("%s{stringToType:%s}", TypeString.class.getSimpleName(), stringToType);
	}
}