package cla.command.undo;

import cla.command.Command;

public interface SequenceOfCommands {

	void ddo(Command addCarrot);

	void undo();

	void redo();

}
