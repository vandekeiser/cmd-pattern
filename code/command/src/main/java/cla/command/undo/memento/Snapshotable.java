package cla.command.undo.memento;

import cla.domain.Env;


public interface Snapshotable {

	Restorable snapshot(Env env);

}
