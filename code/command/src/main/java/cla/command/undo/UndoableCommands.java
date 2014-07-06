package cla.command.undo;

import cla.command.Command;

public interface UndoableCommands {

	void ddo(Command addCarrot);

	void undo();

	void redo();

}
