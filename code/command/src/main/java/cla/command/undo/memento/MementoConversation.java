package cla.command.undo.memento;

import cla.command.Conversation;
import cla.domain.Env;

//Mieux testé que compensation..
public class MementoConversation implements Conversation<MementoableCommand> {

	private final Env env;
	private final BeforeAfterMementoStack undoStack, redoStack;
	
	public MementoConversation(Env env) {
		this.env = env;
		this.undoStack = new BeforeAfterMementoStack();
		this.redoStack = new BeforeAfterMementoStack();
	}

	@Override public void exec(MementoableCommand todo) {
		Memento before = todo.snapshotOf(env);
		todo.execute(this.env);
		Memento after = todo.snapshotOf(env);
		
		undoStack.push(new BeforeAfterMemento(before, after));
		redoStack.clear();
	}

	@Override public void undo() {
		BeforeAfterMemento latestMemento = undoStack.pop();
		if(latestMemento==null) return;
		Memento latestBefore = latestMemento.before;
		latestBefore.restore(env);
		redoStack.push(latestMemento);
	}

	@Override public void redo() {
		BeforeAfterMemento latestMemento = redoStack.pop();
		if(latestMemento==null) return; 
		Memento latestAfter = latestMemento.after;
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
