package cla.command.undo.replay;

import cla.command.Command;
import cla.command.Conversation;

//!!!!!!que se passe-t-il si une des cmds echoue??!!!
public class ReplayConversation implements Conversation<Command> {

	private final Command reset;
	private final CommandStack undoStack, redoStack;
	
	public ReplayConversation(Command reset) {
		this.reset = reset;
		this.undoStack = new CommandStack();
		this.redoStack = new CommandStack();
	}

	@Override public void exec(Command todo) {
		todo.execute();
		undoStack.push(todo);
		redoStack.clear();
	}

	@Override public void undo() {
		if( undoStack.size() > 0 ) {
			Command change = undoStack.pop() ;
            redoStack.push( change ) ;
            reset.execute();
            undoStack.replay();
        }
	}

	@Override public void redo() {
		if( redoStack.size() > 0 ) {
			Command change = redoStack.pop() ;
			change.execute() ;
            undoStack.push( change ) ; 
        }
	}

	@Override public String toString() {
		return String.format("%s{undoStack:%s, redoList:%s}", ReplayConversation.class.getSimpleName(), undoStack, redoStack);
	}
}
