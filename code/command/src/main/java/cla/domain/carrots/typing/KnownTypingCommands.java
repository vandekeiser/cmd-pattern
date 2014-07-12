package cla.domain.carrots.typing;

import cla.command.Command;
import cla.domain.Env;

public enum KnownTypingCommands implements Command {
	
	RESET {
		@Override public void execute(Env env) {
			env.display().reset();
		}
	};

}
