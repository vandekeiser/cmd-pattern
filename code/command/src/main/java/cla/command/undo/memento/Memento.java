package cla.command.undo.memento;

import cla.domain.Env;

//@Immutable
public interface Memento {
	
	void restore(Env env);
	
}
