package cla.command;

public class UndoableCommands_StackImpl implements UndoableCommands {

	private final Env env;
	private final CommandStack undoStack, redoStack;
	
	public UndoableCommands_StackImpl(Env env) {
		this.env = env;
		this.undoStack = new CommandStack();
		this.redoStack = new CommandStack();
	}

	@Override public void ddo(Command todo) {
		todo.execute(this.env);
		undoStack.push(todo);
	}

	@Override public void undo() {
		Command lastDone = undoStack.pop();
		if(lastDone==null) return;//Dans une application quand la stack d'undo est vide, on ne fait rien (on ne crashe pas) 
		lastDone.undo(env);
		
		Command lastUndone = lastDone;//clarity
		redoStack.push(lastUndone);
	}

	@Override public void redo() {
		Command lastUndone = redoStack.pop();
		if(lastUndone==null) return; 
		lastUndone.execute(env);
	}

}
