package cla.command.undo.replay;

import cla.command.undo.Conversation;
import cla.domain.Env;

//!!!!!!que se passe-t-il si une des cmds echoue??!!!
public class ReplayConversation implements Conversation<ResetableCommand> {

	private final Env env;
	//private ReplayableList undoList, redoList;
	//private final ResetableCommandStack stack;
	private final ResetableCommandList list;
	private int position = 0;
	
	public ReplayConversation(Env env) {
		this.env = env;
		this.list = new ResetableCommandList();
	}

	@Override public void exec(ResetableCommand todo) {
		System.out.println("ReplayConversation/exec/START/todo: " + todo);
		System.out.println("ReplayConversation/exec/START/list: " + list);
		
		todo.execute(this.env);
		list.add(todo);
		incPosition();
		
		System.out.println("ReplayConversation/exec/END/list: " + list);
	}

	@Override public void undo() {
		System.out.println("ReplayConversation/undo/START/list: " + list);
		env.display().reset();
		decPosition();
		list.replayUpTo(position, env);
		System.out.println("ReplayConversation/undo/END/list: " + list);
	}

	@Override public void redo() {
		System.out.println("ReplayConversation/redo/START/list: " + list);
		env.display().reset();
		incPosition();
		list.replayUpTo(position, env);
		System.out.println("ReplayConversation/redo/END/list: " + list);
	}

	private void incPosition() {
		++position;
	}
	private void decPosition() {
		position = Math.max(0, position - 1);
	}
	
	@Override public String toString() {
		return String.format("%s{list:%s, position:%s}", ReplayConversation.class.getSimpleName(), list, position);
	}
}
