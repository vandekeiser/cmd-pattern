package cla.command.undo.snapshot;

import cla.command.env.Env;

public class SequenceOfCommands_SnapshotImpl {

	private final Env env;
	
	public SequenceOfCommands_SnapshotImpl(Env env) {
		this.env = env;
	}

	public void ddo(Snapshotable todo) {
		Restorable snapshot = todo.snapshot();
		todo.execute(this.env);
		//undoStack.push(todo);
	}

	public void undo() {
//		Command lastDone = undoStack.pop();
//		if(lastDone==null) return;//Dans une application quand la stack d'undo est vide, on ne fait rien (on ne crashe pas) 
//		lastDone.undo(env);
//		
//		Command lastUndone = lastDone;//La commande qui était la derniere executee est desormais la derniere annulee
//		redoStack.push(lastUndone);
	}

	public void redo() {
//		Command lastUndone = redoStack.pop();
//		if(lastUndone==null) return; 
//		lastUndone.execute(env);
//		
//		Command lastDone = lastUndone;//La commande qui était la derniere annulee est desormais la derniere executee
//		undoStack.push(lastDone);
	}

	
}
