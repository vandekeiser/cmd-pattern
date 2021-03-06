package cla.command.undo.memento;

import cla.command.undo.CommandUndoTest_Typing;
import cla.command.undo.Conversation;
import cla.domain.typing.TypeString;

public class CommandUndoTest_Memento_Typing_Test extends CommandUndoTest_Typing<MementoableCommand> {

	@Override protected Conversation<MementoableCommand> newConversation() {
		return new MementoConversation();
	}

	@Override protected MementoableCommand typeString(String stringToType) {
		return new TypeString(display, stringToType);
	}

}
