package cla.command.undo.compensation;

import cla.command.undo.AbstractConversation;



public class CompensationConversation extends AbstractConversation<CompensableCommand, CompensableCommand> {

	@Override public void exec(CompensableCommand todo) {
		todo.execute();
		undos.push(todo);
		redos.clear();
	}

	@Override public void undo() {
		CompensableCommand latestCmd = undos.pop();
		if(latestCmd==null) return; 
		latestCmd.compensate();
		redos.push(latestCmd);
	}

	@Override public void redo() {
		CompensableCommand latestCmd = redos.pop();
		if(latestCmd==null) return; 
		latestCmd.execute();
		undos.push(latestCmd);
	}
	
}
