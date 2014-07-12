package cla.command.undo.replay;

import cla.command.undo.Conversation;
import cla.domain.Env;

//!!!!!!que se passe-t-il si une des cmds echoue??!!!
public class ReplayConversation implements Conversation<ResetableCommand> {

	private final Env env;
	//private ReplayableList undoList, redoList;
	private final ResetableCommandStack undoStack, redoStack;
	
	public ReplayConversation(Env env) {
		this.env = env;
		this.undoStack = new ResetableCommandStack();
		this.redoStack = new ResetableCommandStack();
	}

	@Override public void exec(ResetableCommand todo) {
		System.out.println("ReplayConversation/exec/START/undoStack: " + undoStack);
		System.out.println("ReplayConversation/exec/START/redoStack: " + redoStack);
		
		todo.execute(this.env);
		undoStack.push(todo);
		redoStack.clear();
		
		System.out.println("ReplayConversation/exec/END/undoStack: " + undoStack);
		System.out.println("ReplayConversation/exec/END/redoList: " + redoStack);
	}

	@Override public void undo() {
		System.out.println("ReplayConversation/undo/START/undoStack: " + undoStack);
		System.out.println("ReplayConversation/undo/START/redoList: " + redoStack);

		//Pb:
		// -si pop tt de suite: supprime cmd ET reset -->la liste des resets est vide aussi
		// -si pop pas tt de suite: le clean marche mais qd on fait replay la stack contient encore la derniere commande
		//-->popCmd qui pop pas le reset
		//ResetableCommand latestCmd = undoStack.peek();//latest.resetCmd.reset(env)?
		ResetableCommand latestCmd = undoStack.pop();
		
		if(latestCmd==null) return; 
		//si on a fait peek: contient encore la derniere commande
		//si on a fait pop:  resets vide.. 
		latestCmd.resetCmd().execute(env);
		undoStack.replay(env);
		//todo: pop reset?
		//undoStack.pop();
		redoStack.push(latestCmd);
		
		System.out.println("ReplayConversation/undo/END/undoStack: " + undoStack);
		System.out.println("ReplayConversation/undo/END/redoList: " + redoStack);
	}

	@Override public void redo() {
		System.out.println("ReplayConversation/redo/START/undoStack: " + undoStack);
		System.out.println("ReplayConversation/redo/START/redoList: " + redoStack);

		ResetableCommand latestCmd = redoStack.peek();
		if(latestCmd==null) return; 
		redoStack.replay(env);
		redoStack.pop();
		undoStack.push(latestCmd);
		
		System.out.println("ReplayConversation/redo/END/undoStack: " + undoStack);
		System.out.println("ReplayConversation/redo/END/redoList: " + redoStack);
	}

	@Override public String toString() {
		return String.format("%s{undoStack:%s, redoList:%s}", ReplayConversation.class.getSimpleName(), undoStack, redoStack);
	}
}
