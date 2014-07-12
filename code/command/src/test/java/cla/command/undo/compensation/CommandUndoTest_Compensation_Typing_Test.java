package cla.command.undo.compensation;

import cla.command.undo.CommandUndoTest_Typing;
import cla.command.undo.Conversation;
import cla.domain.Env;
import cla.domain.typing.TypingCommandFactory;

public class CommandUndoTest_Compensation_Typing_Test extends CommandUndoTest_Typing<CompensableCommand> {

	@Override protected Conversation<CompensableCommand> newConversation(Env env) {
		return new CompensationConversation(env);
	}

	@Override protected CompensableCommand typeString(String stringToType) {
		return TypingCommandFactory.typeString(stringToType);
	}
}
