package cla.command.undo.snapshot;

import cla.domain.Env;


public class ConversationMementoImpl {

	private final Env env;
	private final MementoStack undoStack, redoStack;
	
	public ConversationMementoImpl(Env env) {
		this.env = env;
		this.undoStack = new MementoStack();
		this.redoStack = new MementoStack();
	}

	public void exec(SnapshotableCommand todo) {
		System.out.println("ConversationMementoImpl/exec/START");
		
		Restorable snapshotBefore = todo.snapshot(env);
		todo.execute(this.env);
		Restorable snapshotAfter = todo.snapshot(env);
		
		undoStack.push(new Memento(snapshotBefore, snapshotAfter));
		System.out.println("ConversationMementoImpl/exec/END/undoStack: " + undoStack);
	}

	public void undo() {
		System.out.println("ConversationMementoImpl/undo/START:" + undoStack);
		
		Memento latestMemento = undoStack.pop();
		System.out.println("ConversationMementoImpl/latestMemento:" + latestMemento);
		if(latestMemento==null) return;//Dans une application quand la stack d'undo est vide, on ne fait rien (on ne crashe pas)
		
		Restorable latestBefore = latestMemento.snapshotBefore;
		System.out.println("ConversationMementoImpl/latestBefore:" + latestBefore);
		latestBefore.restore(env);
		
		redoStack.push(latestMemento);
		System.out.println("ConversationMementoImpl/undo/END/undoStack: " + undoStack);
	}

	public void redo() {
		System.out.println("ConversationMementoImpl/redo/START");
		
		Memento latestMemento = redoStack.pop();
		if(latestMemento==null) return; 
		
		Restorable latestAfter = latestMemento.snapshotAfter;
		latestAfter.restore(env);
		
		undoStack.push(latestMemento);
	}

	@Override public String toString() {
		return String.format("%s{undoStack:%s, redoStack:%s}", ConversationMementoImpl.class.getSimpleName(), undoStack, redoStack);
	}
}
