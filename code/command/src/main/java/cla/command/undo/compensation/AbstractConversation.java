package cla.command.undo.compensation;

import cla.command.Command;
import cla.command.Conversation;
import cla.util.Stack;

/**
 * @author User
 *
 * @param <C> Command type
 * @param <S> Stack type
 */
public abstract class AbstractConversation<C extends Command, S> implements Conversation<C> {

	protected final Stack<S> undoStack, redoStack;
	
	public AbstractConversation() {
		this.undoStack = new Stack<S>();
		this.redoStack = new Stack<S>();
	}

	@Override public abstract void exec(C todo);

	@Override public abstract void undo();

	@Override public abstract void redo();

	@Override public String toString() {
		return String.format(
				"%s{undoStack:%s, redoList:%s}", 
				getClass().getSimpleName(), 
				undoStack, 
				redoStack
		);
	}
}
