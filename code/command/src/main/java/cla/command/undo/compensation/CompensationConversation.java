package cla.command.undo.compensation;

import cla.command.undo.Conversation;
import cla.domain.Env;


public class CompensationConversation implements Conversation<CompensableCommand> {

	private final Env env;
	private final CompensableCommandStack undoStack, redoStack;
	
	public CompensationConversation(Env env) {
		this.env = env;
		this.undoStack = new CompensableCommandStack();
		this.redoStack = new CompensableCommandStack();
	}

	@Override public void exec(CompensableCommand todo) {
		todo.execute(this.env);
		undoStack.push(todo);
		redoStack.clear();
	}

	@Override public void undo() {
		CompensableCommand latestCmd = undoStack.pop();
		if(latestCmd==null) return; 
		latestCmd.compensate(env);
		redoStack.push(latestCmd);
	}

	@Override public void redo() {
		CompensableCommand latestCmd = redoStack.pop();
		if(latestCmd==null) return; 
		latestCmd.execute(env);
		undoStack.push(latestCmd);
	}

	@Override public String toString() {
		return String.format("%s{undoStack:%s, redoStack:%s}", CompensationConversation.class.getSimpleName(), undoStack, redoStack);
	}
}
