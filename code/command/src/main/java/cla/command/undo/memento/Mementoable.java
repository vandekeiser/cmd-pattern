package cla.command.undo.memento;

import cla.domain.Env;

interface Mementoable {

	Memento snapshotOf(Env env);

}
