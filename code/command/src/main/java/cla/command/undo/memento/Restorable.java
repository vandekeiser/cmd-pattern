package cla.command.undo.memento;

import cla.domain.Env;


public interface Restorable {
	
	void restore(Env env);
	
}
