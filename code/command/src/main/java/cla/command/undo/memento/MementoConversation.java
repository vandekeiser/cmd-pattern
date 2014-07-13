package cla.command.undo.memento;

import cla.command.Conversation;

//Mieux testé que compensation..
public class MementoConversation implements Conversation<MementoableCommand> {

	private final BeforeAfterMementoStack undoStack, redoStack;
	
	public MementoConversation() {
		this.undoStack = new BeforeAfterMementoStack();
		this.redoStack = new BeforeAfterMementoStack();
	}

	@Override public void exec(MementoableCommand todo) {
		Memento before = todo.snapshotOf();
		todo.execute();
		Memento after = todo.snapshotOf();
		
		undoStack.push(new BeforeAfterMemento(before, after));
		redoStack.clear();
	}

	@Override public void undo() {
		BeforeAfterMemento latestMemento = undoStack.pop();
		if(latestMemento==null) return;
		Memento latestBefore = latestMemento.before;
		latestBefore.restore();
		redoStack.push(latestMemento);
	}

	@Override public void redo() {
		BeforeAfterMemento latestMemento = redoStack.pop();
		if(latestMemento==null) return; 
		Memento latestAfter = latestMemento.after;
		latestAfter.restore();
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
