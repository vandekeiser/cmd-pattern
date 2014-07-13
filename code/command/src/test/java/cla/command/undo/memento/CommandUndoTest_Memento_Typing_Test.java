package cla.command.undo.memento;

import cla.command.Conversation;
import cla.command.undo.CommandUndoTest_Typing;
import cla.domain.typing.TypingCommandFactory;

public class CommandUndoTest_Memento_Typing_Test extends CommandUndoTest_Typing<MementoableCommand> {

	@Override protected Conversation<MementoableCommand> newConversation() {
		return new MementoConversation();
	}

	@Override protected MementoableCommand typeString(String stringToType) {
		return TypingCommandFactory.typeString(display, stringToType);
	}

}
