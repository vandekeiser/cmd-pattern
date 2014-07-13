package cla.command.undo.compensation;

import cla.command.Conversation;
import cla.util.Stack;


public class CompensationConversation implements Conversation<CompensableCommand> {

	private final Stack<CompensableCommand> undoStack, redoStack;
	
	public CompensationConversation() {
		this.undoStack = new Stack<CompensableCommand>();
		this.redoStack = new Stack<CompensableCommand>();
	}

	@Override public void exec(CompensableCommand todo) {
		todo.execute();
		undoStack.push(todo);
		redoStack.clear();
	}

	@Override public void undo() {
		CompensableCommand latestCmd = undoStack.pop();
		if(latestCmd==null) return; 
		latestCmd.compensate();
		redoStack.push(latestCmd);
	}

	@Override public void redo() {
		CompensableCommand latestCmd = redoStack.pop();
		if(latestCmd==null) return; 
		latestCmd.execute();
		undoStack.push(latestCmd);
	}

	@Override public String toString() {
		return String.format(
				"%s{undoStack:%s, redoStack:%s}", 
				CompensationConversation.class.getSimpleName(), 
				undoStack, 
				redoStack
	    );
	}
}
