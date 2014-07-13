package cla.command.undo.memento;

import cla.command.undo.AbstractConversation;

public class MementoConversation extends AbstractConversation<MementoableCommand, BeforeAfter> {

	@Override public void exec(MementoableCommand todo) {
		Memento before = todo.takeSnapshot();
		todo.execute();
		Memento after = todo.takeSnapshot();
		
		undoStack.push(new BeforeAfter(before, after));
		redoStack.clear();
	}

	@Override public void undo() {
		BeforeAfter latestMemento = undoStack.pop();
		if(latestMemento==null) return;
		Memento latestBefore = latestMemento.before;
		latestBefore.restore();
		redoStack.push(latestMemento);
	}

	@Override public void redo() {
		BeforeAfter latestMemento = redoStack.pop();
		if(latestMemento==null) return; 
		Memento latestAfter = latestMemento.after;
		latestAfter.restore();
		undoStack.push(latestMemento);
	}
	
}
