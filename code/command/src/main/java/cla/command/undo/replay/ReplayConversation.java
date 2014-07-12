package cla.command.undo.replay;

import cla.command.undo.Conversation;
import cla.domain.Env;

//!!!!!!que se passe-t-il si une des cmds echoue??!!!
public class ReplayConversation implements Conversation<ResetableCommand> {

	private final Env env;
	//private final Resets resets;
	private final ReplayableList undoList, redoList;
	
	public ReplayConversation(Env env) {
		this.env = env;
		//this.resets = new Resets();
		this.undoList = new ReplayableList();
		this.redoList = new ReplayableList();
	}

	@Override public void exec(ResetableCommand todo) {
		todo.execute(this.env);
		
		undoList.addLast(todo); 
		redoList.clear(); 
	}

	@Override public void undo() {
		undoList.reset(env);

		ResetableCommand latestCmd = undoList.removeLast(); 
		if(latestCmd==null) return;
		redoList.addLast(latestCmd);
		
		undoList.replay(env);
	}

	@Override public void redo() {
		redoList.reset(env);

		ResetableCommand latestCmd = redoList.peekLast(); 
		if(latestCmd==null) return;
		undoList.addLast(latestCmd);
		
		redoList.replay(env);
		redoList.removeLast();
	}

	@Override public String toString() {
		return String.format("%s{undoList:%s, redoList:%s}", ReplayConversation.class.getSimpleName(), undoList, redoList);
	}
}
