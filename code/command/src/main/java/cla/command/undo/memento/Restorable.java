package cla.command.undo.memento;

import cla.domain.Env;

//@Immutable
public interface Restorable {
	
	void restore(Env env);
	
}
