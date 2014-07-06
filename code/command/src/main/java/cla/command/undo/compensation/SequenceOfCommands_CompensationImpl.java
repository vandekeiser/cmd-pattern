package cla.command.undo.compensation;

import cla.domain.Env;


public class SequenceOfCommands_CompensationImpl {

	private final Env env;
	private final CommandStack undoStack, redoStack;
	
	public SequenceOfCommands_CompensationImpl(Env env) {
		this.env = env;
		this.undoStack = new CommandStack();
		this.redoStack = new CommandStack();
	}

	public void ddo(CompensableCommand todo) {
		todo.execute(this.env);
		undoStack.push(todo);
	}

	public void undo() {
		CompensableCommand lastDone = undoStack.pop();
		if(lastDone==null) return;//Dans une application quand la stack d'undo est vide, on ne fait rien (on ne crashe pas) 
		lastDone.compensate(env);
		
		CompensableCommand lastUndone = lastDone;//La commande qui était la derniere executee est desormais la derniere annulee
		redoStack.push(lastUndone);
	}

	public void redo() {
		CompensableCommand lastUndone = redoStack.pop();
		if(lastUndone==null) return; 
		lastUndone.execute(env);
		
		CompensableCommand lastDone = lastUndone;//La commande qui était la derniere annulee est desormais la derniere executee
		undoStack.push(lastDone);
	}

}
