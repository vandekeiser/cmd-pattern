package cla.command.undo.compensation;

import cla.command.undo.CommandUndoTest_Typing;
import cla.command.undo.Conversation;
import cla.domain.typing.TypeString;

public class CommandUndoTest_Compensation_Typing_Test extends CommandUndoTest_Typing<CompensableCommand> {

	@Override protected Conversation<CompensableCommand> newConversation() {
		return new CompensationConversation();
	}

	@Override protected CompensableCommand typeString(String stringToType) {
		return new TypeString(display, stringToType);
	}
}
