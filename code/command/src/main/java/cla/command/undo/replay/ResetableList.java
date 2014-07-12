package cla.command.undo.replay;

import java.util.LinkedList;
import java.util.List;

import cla.domain.Env;

public class ResetableList {

	private final List<ResetableCommand> resetables = new LinkedList<>();

	public void addLast(ResetableCommand cmd) {
		resetables.add(cmd);
	}

	public void clear() {
		resetables.clear();		
	}

	public void executeAll(Env env) {
		resetables.stream().forEachOrdered(r->{
			r.resetCmd().execute(env);
		});
	}

}
