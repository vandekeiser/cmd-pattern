package cla.command.undo.replay;

import cla.command.undo.Conversation;
import cla.domain.Env;

//!!!!!!que se passe-t-il si une des cmds echoue??!!!
public class ReplayConversation implements Conversation<ResetableCommand> {

	private final Env env;
	private ReplayableList undoList, redoList;
	
	public ReplayConversation(Env env) {
		this.env = env;
		this.undoList = new ReplayableList();
		this.redoList = new ReplayableList();
	}

	@Override public void exec(ResetableCommand todo) {
		System.out.println("ReplayConversation/exec/START/undoList: " + undoList);
		System.out.println("ReplayConversation/exec/START/redoList: " + redoList);
		
		todo.execute(this.env);
		
		System.out.println("ReplayConversation/exec/END/undoList: " + undoList);
		System.out.println("ReplayConversation/exec/END/redoList: " + redoList);
	}

	@Override public void undo() {
		System.out.println("ReplayConversation/undo/START/undoList: " + undoList);
		System.out.println("ReplayConversation/undo/START/redoList: " + redoList);

		undoList.replay(env);
		
		System.out.println("ReplayConversation/undo/END/undoList: " + undoList);
		System.out.println("ReplayConversation/undo/END/redoList: " + redoList);
	}

	@Override public void redo() {
		System.out.println("ReplayConversation/redo/START/undoList: " + undoList);
		System.out.println("ReplayConversation/redo/START/redoList: " + redoList);

		redoList.replay(env);
		
		System.out.println("ReplayConversation/redo/END/undoList: " + undoList);
		System.out.println("ReplayConversation/redo/END/redoList: " + redoList);
	}

	@Override public String toString() {
		return String.format("%s{undoList:%s, redoList:%s}", ReplayConversation.class.getSimpleName(), undoList, redoList);
	}
}
