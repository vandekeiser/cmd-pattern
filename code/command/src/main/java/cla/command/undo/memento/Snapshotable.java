package cla.command.undo.memento;

import cla.domain.Env;

//Suppr et mettre la methode ds snapshotablecmd? non si on arive a faie un bridge
interface Snapshotable {

	/**
	 * @param env Might be required to create a snapshot.
	 * @return
	 */
	Restorable snapshot(Env env);

}
