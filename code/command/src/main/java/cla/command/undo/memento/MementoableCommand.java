package cla.command.undo.memento;

import cla.command.Command;

public interface MementoableCommand extends Command {
	
	Memento takeSnapshot();
	
}
