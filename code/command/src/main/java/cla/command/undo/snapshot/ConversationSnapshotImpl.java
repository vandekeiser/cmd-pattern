package cla.command.undo.snapshot;

import cla.domain.Env;


public class ConversationSnapshotImpl {

	private final Env env;
	private final SnapshotStack undoStack, redoStack;
	
	public ConversationSnapshotImpl(Env env) {
		this.env = env;
		this.undoStack = new SnapshotStack();
		this.redoStack = new SnapshotStack();
	}

	public void exec(SnapshotableCommand todo) {
		System.out.println("SequenceOfCommands_SnapshotImpl/exec/START");
		
		Restorable snapshotBefore = todo.snapshot(env);
		todo.execute(this.env);
		Restorable snapshotAfter = todo.snapshot(env);
		
		undoStack.push(snapshotBefore);
		redoStack.push(snapshotAfter);
	}

	public void undo() {
		System.out.println("SequenceOfCommands_SnapshotImpl/undo/START");
		Restorable lastSnapshot = undoStack.pop();
		if(lastSnapshot==null) return;//Dans une application quand la stack d'undo est vide, on ne fait rien (on ne crashe pas) 
		
		lastSnapshot.restore(env);
		
		redoStack.push(lastSnapshot);
	}

	public void redo() {
		System.out.println("SequenceOfCommands_SnapshotImpl/redo/START");
		Restorable lastSnapshot = redoStack.pop();
		if(lastSnapshot==null) return; 
		lastSnapshot.restore(env);
		
		undoStack.push(lastSnapshot);
	}

	@Override public String toString() {
		return String.format("%s{undoStack:%s, redoStack:%s}", ConversationSnapshotImpl.class.getSimpleName(), undoStack, redoStack);
	}
}
