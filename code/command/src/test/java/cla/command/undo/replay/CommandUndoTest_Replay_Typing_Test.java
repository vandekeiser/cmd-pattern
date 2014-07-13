package cla.command.undo.replay;

import cla.command.Command;
import cla.command.Conversation;
import cla.command.undo.CommandUndoTest_Typing;
import cla.domain.typing.TypingCommandFactory;

public class CommandUndoTest_Replay_Typing_Test extends CommandUndoTest_Typing<Command> {

	@Override protected Conversation<Command> newConversation() {
		return new ReplayConversation(()->{display.reset();});
	}

	@Override protected Command typeString(String stringToType) {
		return TypingCommandFactory.typeString(display, stringToType);
	}
	
}
