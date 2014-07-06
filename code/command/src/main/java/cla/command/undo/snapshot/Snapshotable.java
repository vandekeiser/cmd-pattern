package cla.command.undo.snapshot;

import cla.domain.Env;


public interface Snapshotable {

	Restorable snapshot(Env env);

}
