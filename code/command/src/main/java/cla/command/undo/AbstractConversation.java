package cla.command.undo;

import cla.domain.Env;

//Mieux testé que compensation..
public abstract class AbstractConversation<STACK, CMD, CAPTURE, BEFOREAFTER> {

	protected final Env env;
	protected final STACK undoStack, redoStack;
	
	public AbstractConversation(Env env) {
		this.env = env;
		this.undoStack = initUndoStack();//PAS BEAU!!! overridable in consrtuctor
		this.redoStack = initRedoStack();//PAS BEAU!!! overridable in consrtuctor
	}

	protected abstract STACK initUndoStack();
	protected abstract STACK initRedoStack();

	public void exec(CMD todo) {
		System.out.println("AbstractConversation/exec/START");
		
		CAPTURE before = captureUndoMeans(todo);
		//todo.execute(this.env);
		executeCmd(todo);
		CAPTURE after = captureUndoMeans(todo);
		
		//undoStack.push(new Memento(snapshotBefore, snapshotAfter));
		pushUndo(createBeforeAfter(before, after));
		//redoStack.clear();
		clearRedo();
		System.out.println("AbstractConversation/exec/END/undoStack: " + undoStack);
	}

	protected abstract CAPTURE captureUndoMeans(CMD cmd);
	protected abstract CAPTURE extractBefore(BEFOREAFTER latestBeforeAfter);
	protected abstract CAPTURE extractAfter(BEFOREAFTER latestBeforeAfter);
	
	protected abstract BEFOREAFTER createBeforeAfter(CAPTURE before, CAPTURE after);
	
	protected abstract void pushUndo(BEFOREAFTER createBeforeAfter);
	protected abstract void pushRedo(BEFOREAFTER createBeforeAfter);
	protected abstract BEFOREAFTER popUndo();
	protected abstract BEFOREAFTER popRedo();
	protected abstract void clearRedo();
	
	protected abstract void executeCmd(CMD todo);

	public void undo() {
		System.out.println("AbstractConversation/undo/START:" + undoStack);
		
		//BEFOREAFTER latestBeforeQfter = undoStack.pop();
		BEFOREAFTER latestBeforeAfter = popUndo();
		System.out.println("AbstractConversation/latestBeforeAfter:" + latestBeforeAfter);
		if(latestBeforeAfter==null) return;//Dans une application quand la stack d'undo est vide, on ne fait rien (on ne crashe pas)
		
		//Restorable latestBefore = latestBeforeAfter.snapshotBefore;
		CAPTURE latestBefore = extractBefore(latestBeforeAfter);
		System.out.println("AbstractConversation/latestBefore:" + latestBefore);
		//latestBefore.restore(env);
		restore(latestBefore);
		
		//redoStack.push(latestBeforeAfter);
		pushRedo(latestBeforeAfter);
		System.out.println("AbstractConversation/undo/END/undoStack: " + undoStack);
	}

	protected abstract void restore(CAPTURE capture);


	public void redo() {
		System.out.println("AbstractConversation/redo/START/redoStack: " + redoStack);
		
		BEFOREAFTER latestMemento = popRedo();
		if(latestMemento==null) return; 
		
		CAPTURE latestAfter = extractAfter(latestMemento);
		restore(latestAfter);
		
		pushUndo(latestMemento);
	}

	@Override public String toString() {
		return String.format(
				"%s{undoStack:%s, redoStack:%s}", 
				AbstractConversation.class.getSimpleName(), 
				undoStack, 
				redoStack
		);
	}
}
