package cla.command.undo.replay;

import cla.command.Command;
import cla.command.undo.CommandUndoTest_Typing;
import cla.command.undo.Conversation;
import cla.domain.typing.TypeString;

public class CommandUndoTest_Replay_Typing_Test extends CommandUndoTest_Typing<Command> {

	@Override protected Conversation<Command> newConversation() {
		return new ReplayConversation(()->{
			display.clear();
		});
	}

	@Override protected Command typeString(String stringToType) {
		return new TypeString(display, stringToType);
	}
	
}
