package cla.command.undo.compensation;

import cla.command.Command;

public interface CompensableCommand extends Command {
	void compensate();
}
