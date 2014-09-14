package cla.command.undo.compensation;

import cla.command.undo.AbstractConversation;



public class CompensationConversation extends AbstractConversation<CompensableCommand, CompensableCommand> {

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
	
}
