package cla.command.undo.replay;

import java.util.LinkedList;
import java.util.List;

import cla.domain.Env;

public class ReplayableList {

	private final LinkedList<ResetableCommand> resetables = new LinkedList<>();

	public void addLast(ResetableCommand cmd) {
		resetables.add(cmd);
	}

	public void clear() {
		resetables.clear();		
	}

	public ResetableCommand peekLast() {
		return resetables.peekLast();
	}
	
	public ResetableCommand removeLast() {
		return resetables.pollLast();
	}

	public void reset(Env env) {
		resetables.stream().forEach(cmd->{
			cmd.resetCmd().execute(env);
		});		
	}

	public void replay(Env env) {
		resetables.stream().forEachOrdered(cmd->{
			cmd.execute(env);
		});
	}
	
	@Override public String toString() {
		return String.format("%s{resetables: %s}", resetables);
	}

}
