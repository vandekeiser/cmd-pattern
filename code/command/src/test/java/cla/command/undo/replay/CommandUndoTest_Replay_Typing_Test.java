package cla.command.undo.replay;

import cla.command.Command;
import cla.command.CommandFactory;
import cla.command.undo.CommandUndoTest_Typing;
import cla.command.undo.Conversation;
import cla.domain.Env;
import cla.domain.carrots.typing.KnownTypingCommands;

public class CommandUndoTest_Replay_Typing_Test extends CommandUndoTest_Typing<Command> {

	@Override protected Conversation<Command> newConversation(Env env) {
		return new ReplayConversation(env, KnownTypingCommands.RESET);
	}

	@Override protected Command typeString(String stringToType) {
		return CommandFactory.typeString(stringToType);
	}
	
}
