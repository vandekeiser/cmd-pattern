package cla.command.undo.replay;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import cla.command.Command;
import cla.domain.Env;

//!!!!!!que se passe-t-il si une des cmds echoue??!!!
public class ReplayConversation {

	private final Env env;
	private final Resets resets;
	
	public ReplayConversation(Env env) {
		this.env = env;
		this.resets = new Resets();
	}

	public void exec(ResetableCommand todo) {
		todo.execute(this.env);
		Command correspondingReset = todo.resetCmd();
		
		resets.add(correspondingReset); 
		//redoStack.clear(); 
	}

	public void undo() {
		resets.executeAll(env);
//		ResetableCommand latestCmd = undoStack.pop();
//		if(latestCmd==null) return;//Dans une application quand la stack d'undo est vide, on ne fait rien (on ne crashe pas) 
//		replayFifoUpTo(latestCmd);
//		
//		redoStack.push(latestCmd);
	}

	public void redo() {
//		ResetableCommand latestCmd = redoStack.pop();
//		if(latestCmd==null) return; 
//		latestCmd.execute(env);
//		
//		undoStack.push(latestCmd);
//		replayFifoUpTo(latestCmd);
	}

//	private void replayFifoUpTo(ResetableCommand latestCmd) {
//		List<ResetableCommand> allCmdsFifo = new LinkedList<>();
//		ResetableCommand cmd;
//		Set<Command> resets = new HashSet<>();//reset cmds should be equal by type..
//		
//		while((cmd = undoStack.pop())!=null) {
//			allCmdsFifo.add(cmd);
//			resets.add(cmd.resetCmd());
//		}
//		allCmdsFifo.add(latestCmd);
//		resets.add(latestCmd.resetCmd());
//		
//		//Execute all kinfs of resets
//		for(Command reset : resets) {
//			reset.execute(env);
//		}
//		
//		//Replay
//		Collections.reverse(allCmdsFifo);
//		for(Command _cmd : allCmdsFifo) {
//			_cmd.execute(env);
//		}
//	}
	
//	@Override public String toString() {
//		return String.format("%s{undoStack:%s, redoStack:%s}", ReplayConversation.class.getSimpleName(), undoStack, redoStack);
//	}
}
