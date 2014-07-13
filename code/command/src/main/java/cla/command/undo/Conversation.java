package cla.command.undo;

import cla.command.Command;


public interface Conversation<C extends Command> {

	void exec(C cmd);

	void undo();

	void redo();

}
