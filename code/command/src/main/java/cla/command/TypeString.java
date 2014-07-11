package cla.command;

import java.util.List;

import cla.command.undo.compensation.CompensableCommand;
import cla.command.undo.memento.Restorable;
import cla.command.undo.memento.SnapshotableCommand;
import cla.command.undo.replay.ResetableCommand;
import cla.domain.Env;

//TODO add generic add/remove/upd?
public class TypeString implements CompensableCommand, SnapshotableCommand, ResetableCommand {

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
		final List<String> snapshot =  env.display().getState();
		System.out.println("TypeString/snapshot: " + snapshot);
		
//		return (Env e) -> {
//			e.display().setState(snapshot);
//		};
		return new Restorable() {
			@Override public void restore(Env e) {
				e.display().setState(snapshot);
			}
			
			@Override public String toString() {
				return String.format("%s{snapshot: %s}", Restorable.class.getSimpleName(), snapshot);
			}
		};
	}
	
	@Override public Command resetCmd() {
		return (Env e) -> {
			e.display().reset();
		};
	}
	
	@Override public String toString() {
		return String.format("%s{stringToType:%s}", TypeString.class.getSimpleName(), stringToType);
	}
	
}