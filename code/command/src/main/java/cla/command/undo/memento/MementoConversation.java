package cla.command.undo.memento;

import cla.command.Conversation;
import cla.domain.Env;

//Mieux testé que compensation..
public class MementoConversation implements Conversation<SnapshotableCommand> {

	private final Env env;
	private final BeforeAfterMementoStack undoStack, redoStack;
	
	public MementoConversation(Env env) {
		this.env = env;
		this.undoStack = new BeforeAfterMementoStack();
		this.redoStack = new BeforeAfterMementoStack();
	}

	@Override public void exec(SnapshotableCommand todo) {
		System.out.println("ConversationMementoImpl/exec/START");
		
		Restorable snapshotBefore = todo.snapshot(env);
		todo.execute(this.env);
		Restorable snapshotAfter = todo.snapshot(env);
		
		undoStack.push(new BeforeAfterMemento(snapshotBefore, snapshotAfter));
		redoStack.clear();
		System.out.println("ConversationMementoImpl/exec/END/undoStack: " + undoStack);
	}

	@Override public void undo() {
		System.out.println("ConversationMementoImpl/undo/START:" + undoStack);
		
		BeforeAfterMemento latestMemento = undoStack.pop();
		System.out.println("ConversationMementoImpl/latestMemento:" + latestMemento);
		if(latestMemento==null) return;//Dans une application quand la stack d'undo est vide, on ne fait rien (on ne crashe pas)
		
		Restorable latestBefore = latestMemento.snapshotBefore;
		System.out.println("ConversationMementoImpl/latestBefore:" + latestBefore);
		latestBefore.restore(env);
		
		redoStack.push(latestMemento);
		System.out.println("ConversationMementoImpl/undo/END/undoStack: " + undoStack);
	}

	@Override public void redo() {
		System.out.println("ConversationMementoImpl/redo/START/redoStack: " + redoStack);
		
		BeforeAfterMemento latestMemento = redoStack.pop();
		if(latestMemento==null) return; 
		
		Restorable latestAfter = latestMemento.snapshotAfter;
		latestAfter.restore(env);
		
		undoStack.push(latestMemento);
	}

	@Override public String toString() {
		return String.format(
				"%s{undoStack:%s, redoStack:%s}", 
				MementoConversation.class.getSimpleName(), 
				undoStack, 
				redoStack
		);
	}
}
