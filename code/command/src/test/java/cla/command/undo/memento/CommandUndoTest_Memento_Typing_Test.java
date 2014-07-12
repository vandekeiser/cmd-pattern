package cla.command.undo.memento;

import cla.command.CommandFactory;
import cla.command.undo.CommandUndoTest_Typing;
import cla.command.undo.Conversation;
import cla.domain.Env;

public class CommandUndoTest_Memento_Typing_Test extends CommandUndoTest_Typing<SnapshotableCommand> {

	@Override protected Conversation<SnapshotableCommand> newConversation(Env env) {
		return new MementoConversation(env);
	}

	@Override protected SnapshotableCommand typeString(String stringToType) {
		return CommandFactory.typeString(stringToType);
	}

}
