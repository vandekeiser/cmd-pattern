package cla.command.undo.memento;

import cla.command.undo.AbstractConversation;
import cla.domain.Env;

//Mieux testé que compensation..
public class MementoConversation2 
extends AbstractConversation<MementoStack, SnapshotableCommand, Restorable, Memento> {

	private MementoStack undoStack, redoStack;
	
	public MementoConversation2(Env env) {
		super(env);
		this.undoStack = initUndoStack();//PAS BEAU!!! overridable in consrtuctor
		this.redoStack = initRedoStack();//PAS BEAU!!! overridable in consrtuctor
	}
	
	@Override protected MementoStack initUndoStack() {
		MementoStack _undoStack = new MementoStack();
		this.undoStack = _undoStack;
		return _undoStack;
	}
	@Override protected MementoStack initRedoStack() {
		MementoStack _redoStack = new MementoStack();
		this.redoStack = _redoStack;
		return _redoStack;
	}

	@Override protected Restorable captureUndoMeans(SnapshotableCommand cmd) {
		return cmd.snapshot(env);
	}
	@Override protected Restorable extractBefore(Memento m) {
		return m.snapshotBefore;
	}
	@Override protected Restorable extractAfter(Memento m) {
		return m.snapshotAfter;
	}

	@Override protected Memento createBeforeAfter(Restorable before, Restorable after) {
		return new Memento(before, after);
	}

	@Override protected void pushUndo(Memento m) {
		undoStack.push(m);
	}
	@Override protected void pushRedo(Memento m) {
		redoStack.push(m);
	}
	@Override protected Memento popUndo() {
		return undoStack.pop();
	}
	@Override protected Memento popRedo() {
		return redoStack.pop();
	}
	@Override protected void clearRedo() {
		undoStack.clear();
	}

	@Override protected void executeCmd(SnapshotableCommand todo) {
		todo.execute(env);
	}

	@Override protected void restore(Restorable capture) {
		capture.restore(env);
	}

	
}
