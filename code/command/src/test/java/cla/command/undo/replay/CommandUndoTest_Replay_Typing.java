package cla.command.undo.replay;

import cla.command.CommandFactory;
import cla.command.undo.CommandUndoTest_Typing;
import cla.command.undo.Conversation;
import cla.domain.Env;

public class CommandUndoTest_Replay_Typing extends CommandUndoTest_Typing<ResetableCommand> {

	@Override protected Conversation<ResetableCommand> newConversation(Env env) {
		return new ReplayConversation(env);
	}

	@Override protected ResetableCommand typeString(String stringToType) {
		return CommandFactory.typeString(stringToType);
	}
	
}
