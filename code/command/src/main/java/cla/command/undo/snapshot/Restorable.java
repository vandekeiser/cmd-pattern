package cla.command.undo.snapshot;

import cla.domain.Env;


public interface Restorable {
	
	void restore(Env env);
	
}
