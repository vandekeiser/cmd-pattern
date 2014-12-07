package cla.command.undo.memento;

import cla.command.undo.AbstractConversation;

public class MementoConversation extends AbstractConversation<MementoableCommand, BeforeAfter> {

	@Override public void exec(MementoableCommand todo) {
		Memento before = todo.takeSnapshot();
		todo.execute();
		Memento after = todo.takeSnapshot();
		
		undos.push(new BeforeAfter(before, after));
		redos.clear();
	}

	@Override public void undo() {
		BeforeAfter latestMemento = undos.pop();
		if(latestMemento==null) return;
		Memento latestBefore = latestMemento.before;
		latestBefore.restore();
		redos.push(latestMemento);
	}

	@Override public void redo() {
		BeforeAfter latestMemento = redos.pop();
		if(latestMemento==null) return; 
		Memento latestAfter = latestMemento.after;
		latestAfter.restore();
		undos.push(latestMemento);
	}
	
}
