package cla.command.undo.compensation;

import cla.command.Conversation;
import cla.command.undo.CommandUndoTest_Typing;
import cla.domain.typing.TypingCommandFactory;

public class CommandUndoTest_Compensation_Typing_Test extends CommandUndoTest_Typing<CompensableCommand> {

	@Override protected Conversation<CompensableCommand> newConversation() {
		return new CompensationConversation();
	}

	@Override protected CompensableCommand typeString(String stringToType) {
		return TypingCommandFactory.typeString(display, stringToType);
	}
}
