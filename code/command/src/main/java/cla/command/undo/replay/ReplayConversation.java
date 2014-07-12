package cla.command.undo.replay;

import cla.command.undo.Conversation;
import cla.domain.Env;

//!!!!!!que se passe-t-il si une des cmds echoue??!!!
public class ReplayConversation implements Conversation<ResetableCommand> {

	private final Env env;
	private ReplayableList doneList, undoList, redoList;
	
	public ReplayConversation(Env env) {
		this.env = env;
		this.doneList = new ReplayableList();
		this.undoList = new ReplayableList();
		this.redoList = new ReplayableList();
	}

	@Override public void exec(ResetableCommand todo) {
		System.out.println("ReplayConversation/exec/START/doneList: " + doneList);
		System.out.println("ReplayConversation/exec/START/undoList: " + undoList);
		System.out.println("ReplayConversation/exec/START/redoList: " + redoList);
		
		undoList = doneList.copy();
		todo.execute(this.env);
		doneList.addLast(todo);
		redoList = doneList.copy();
		
		System.out.println("ReplayConversation/exec/END/doneList: " + doneList);
		System.out.println("ReplayConversation/exec/END/undoList: " + undoList);
		System.out.println("ReplayConversation/exec/END/redoList: " + redoList);
	}

	@Override public void undo() {
		System.out.println("ReplayConversation/undo/START/doneList: " + doneList);//ReplayableList{resetables: [TypeString{stringToType:a}]}
		System.out.println("ReplayConversation/undo/START/undoList: " + undoList);//ReplayableList{resetables: []}
		System.out.println("ReplayConversation/undo/START/redoList: " + redoList);//ReplayableList{resetables: [TypeString{stringToType:a}]}
		
		redoList = doneList.copy();
		doneList.reset(env);
		undoList.replay(env);
		doneList = undoList.copy();
		undoList.removeLast();
		
		System.out.println("ReplayConversation/undo/END/doneList: " + doneList);
		System.out.println("ReplayConversation/undo/END/undoList: " + undoList);
		System.out.println("ReplayConversation/undo/END/redoList: " + redoList);
	}

	@Override public void redo() {
		System.out.println("ReplayConversation/redo/START/doneList: " + doneList);
		System.out.println("ReplayConversation/redo/START/undoList: " + undoList);
		System.out.println("ReplayConversation/redo/START/redoList: " + redoList);
		
		undoList = doneList.copy();
		doneList.reset(env);
		redoList.replay(env);
		doneList = redoList.copy();
		redoList.removeLast();
		
		System.out.println("ReplayConversation/redo/END/doneList: " + doneList);
		System.out.println("ReplayConversation/redo/END/undoList: " + undoList);
		System.out.println("ReplayConversation/redo/END/redoList: " + redoList);
	}

	@Override public String toString() {
		return String.format("%s{undoList:%s, redoList:%s}", ReplayConversation.class.getSimpleName(), undoList, redoList);
	}
}
