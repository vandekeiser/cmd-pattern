package cla.command.undo.memento;

import cla.command.undo.compensation.AbstractConversation;

public class MementoConversation extends AbstractConversation<MementoableCommand, BeforeAfterMemento> {

	@Override public void exec(MementoableCommand todo) {
		Memento before = todo.takeSnapshot();
		todo.execute();
		Memento after = todo.takeSnapshot();
		
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
	
}
