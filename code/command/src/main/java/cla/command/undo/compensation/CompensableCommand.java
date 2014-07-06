package cla.command.undo.compensation;

import cla.command.Command;
import cla.command.env.Env;

/**
 *
 */
public interface CompensableCommand extends Command 
{
	void compensate(Env env);
}
