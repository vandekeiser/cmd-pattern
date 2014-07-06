package cla.command.undo.snapshot;

import cla.command.Command;

public interface Snapshotable extends Command {

	Restorable snapshot();

}
