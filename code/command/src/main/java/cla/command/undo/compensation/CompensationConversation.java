package cla.command.undo.compensation;

import cla.domain.Env;


public class CompensationConversation {

	private final Env env;
	private final CompensableCommandStack undoStack, redoStack;
	
	public CompensationConversation(Env env) {
		this.env = env;
		this.undoStack = new CompensableCommandStack();
		this.redoStack = new CompensableCommandStack();
	}

	public void exec(CompensableCommand todo) {
		todo.execute(this.env);
		undoStack.push(todo);
		redoStack.clear();
	}

	public void undo() {
		CompensableCommand latestCmd = undoStack.pop();
		if(latestCmd==null) return;//Dans une application quand la stack d'undo est vide, on ne fait rien (on ne crashe pas) 
		latestCmd.compensate(env);
		
		redoStack.push(latestCmd);
	}

	public void redo() {
		CompensableCommand latestCmd = redoStack.pop();
		if(latestCmd==null) return; 
		latestCmd.execute(env);
		
		undoStack.push(latestCmd);
	}

	@Override public String toString() {
		return String.format("%s{undoStack:%s, redoStack:%s}", CompensationConversation.class.getSimpleName(), undoStack, redoStack);
	}
}
