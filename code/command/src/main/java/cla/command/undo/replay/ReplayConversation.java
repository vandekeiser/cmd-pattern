package cla.command.undo.replay;

import cla.command.Command;
import cla.command.Conversation;
import cla.util.Stack;

//!!!!!!que se passe-t-il si une des cmds echoue??!!!
public class ReplayConversation implements Conversation<Command> {

	private final Command reset;
	private final Stack<Command> undoStack, redoStack;
	
	public ReplayConversation(Command reset) {
		this.reset = reset;
		this.undoStack = new Stack<Command>();
		this.redoStack = new Stack<Command>();
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

	@Override public String toString() {
		return String.format(
				"%s{undoStack:%s, redoList:%s}", 
				ReplayConversation.class.getSimpleName(), 
				undoStack, 
				redoStack
		);
	}
}
