package cla.command;


public interface Conversation<C extends Command> {

	void exec(C cmd);

	void undo();

	void redo();

}
