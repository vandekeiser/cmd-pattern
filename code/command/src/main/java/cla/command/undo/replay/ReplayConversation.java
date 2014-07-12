package cla.command.undo.replay;

import cla.command.undo.Conversation;
import cla.domain.Env;

//!!!!!!que se passe-t-il si une des cmds echoue??!!!
public class ReplayConversation implements Conversation<ResetableCommand> {

	private final Env env;
	private final ResetableCommandStack undoStack, redoStack;
	
	public ReplayConversation(Env env) {
		this.env = env;
		this.undoStack = new ResetableCommandStack();
		this.redoStack = new ResetableCommandStack();
	}

	@Override public void exec(ResetableCommand todo) {
		System.out.println("ReplayConversation/exec/START/undoStack: " + undoStack);
		System.out.println("ReplayConversation/exec/START/redoStack: " + redoStack);
		
		todo.execute(this.env);
		undoStack.push(todo);
		redoStack.clear();
		
		System.out.println("ReplayConversation/exec/END/undoStack: " + undoStack);
		System.out.println("ReplayConversation/exec/END/redoList: " + redoStack);
	}

	@Override public void undo() {
		System.out.println("ReplayConversation/undo/START/undoStack: " + undoStack);
		System.out.println("ReplayConversation/undo/START/redoList: " + redoStack);

		if( undoStack.size() > 0 ) {
			ResetableCommand change = undoStack.pop() ;
            redoStack.push( change ) ;
            reset() ;
            undoStack.replay(env);
        }
	}

	private void reset() {
		env.display().reset();
	}

	@Override public void redo() {
		System.out.println("ReplayConversation/redo/START/undoStack: " + undoStack);
		System.out.println("ReplayConversation/redo/START/redoList: " + redoStack);

		if( redoStack.size() > 0 ) {
			ResetableCommand change = redoStack.pop() ;
			change.execute(env) ;
            undoStack.push( change ) ; 
        }
	}

	@Override public String toString() {
		return String.format("%s{undoStack:%s, redoList:%s}", ReplayConversation.class.getSimpleName(), undoStack, redoStack);
	}
}
