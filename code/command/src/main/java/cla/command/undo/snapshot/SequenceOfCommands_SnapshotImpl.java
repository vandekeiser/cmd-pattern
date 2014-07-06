package cla.command.undo.snapshot;

import cla.domain.Env;


public class SequenceOfCommands_SnapshotImpl {

	private final Env env;
	private final SnapshotStack undoStack, redoStack;
	
	public SequenceOfCommands_SnapshotImpl(Env env) {
		this.env = env;
		this.undoStack = new SnapshotStack();
		this.redoStack = new SnapshotStack();
	}

	public void ddo(SnapshotableCommand todo) {
		Restorable snapshot = todo.snapshot(env);
		todo.execute(this.env);
		undoStack.push(snapshot);
	}

	public void undo() {
		Restorable lastSnapshot = undoStack.pop();
		if(lastSnapshot==null) return;//Dans une application quand la stack d'undo est vide, on ne fait rien (on ne crashe pas) 
		lastSnapshot.restore(env);
		
		//XXX nommage
		Restorable lastUndone = lastSnapshot;//La commande qui était la derniere executee est desormais la derniere annulee
		redoStack.push(lastUndone);
	}

	public void redo() {
		Restorable lastUndone = redoStack.pop();
		if(lastUndone==null) return; 
		lastUndone.restore(env);
		
		//XXX nommage
		Restorable lastDone = lastUndone;//La commande qui était la derniere annulee est desormais la derniere executee
		undoStack.push(lastDone);
	}

	
}
