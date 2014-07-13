package cla.command.undo.replay;

import cla.command.Command;
import cla.command.undo.AbstractConversation;

public class ReplayConversation extends AbstractConversation<Command, Command> {

	private final Command reset;
	
	public ReplayConversation(Command reset) {
		this.reset = reset;
	}

	@Override public void exec(Command todo) {
		todo.execute();
		undoStack.push(todo);
		redoStack.clear();
	}

	@Override public void undo() {
		Command change = undoStack.pop() ;
		if(change==null) return;
        redoStack.push( change );
        reset.execute();
        undoStack.forEachFifo(cmd->cmd.execute());
	}

	@Override public void redo() {
		Command change = redoStack.pop() ;
		if(change==null) return;
		change.execute();
        undoStack.push( change ) ; 
	}
	
}
