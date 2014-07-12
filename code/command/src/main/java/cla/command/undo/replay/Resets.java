package cla.command.undo.replay;

import java.util.HashSet;
import java.util.Set;

import cla.command.Command;
import cla.domain.Env;

public class Resets {//utilioser une enum Resets

	private final Set<Command> resets = new HashSet<>();

	public void add(Command reset) {
		this.resets.add(reset);
	}

	public void executeAll(Env env) {
		resets.stream().forEach(r->{r.execute(env);});
	}

}
