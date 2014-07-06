package cla.command;

/**
 *
 */
public interface Command 
{
	void execute(Env env);

	void undo(Env env);
}
