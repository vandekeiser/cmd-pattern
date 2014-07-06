package cla.command.undo.stack;

import cla.command.Command;
import cla.command.env.Env;
import cla.command.undo.SequenceOfCommands;

public class SequenceOfCommands_CompensationImpl implements SequenceOfCommands {

	private final Env env;
	private final CommandStack undoStack, redoStack;
	
	public SequenceOfCommands_CompensationImpl(Env env) {
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
		
		Command lastUndone = lastDone;//La commande qui était la derniere executee est desormais la derniere annulee
		redoStack.push(lastUndone);
	}

	@Override public void redo() {
		Command lastUndone = redoStack.pop();
		if(lastUndone==null) return; 
		lastUndone.execute(env);
		
		Command lastDone = lastUndone;//La commande qui était la derniere annulee est desormais la derniere executee
		undoStack.push(lastDone);
	}

}
