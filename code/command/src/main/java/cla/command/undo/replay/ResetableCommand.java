package cla.command.undo.replay;

import cla.command.Command;

/**
 * @author User
 * A command that is not necessarily individually compensable, but defines a global reset of all state it might have modified. 
 */
public interface ResetableCommand extends Command, Resetable {
}
