package cla.command.undo.replay;

import cla.command.Command;
import cla.command.undo.AbstractConversation;

public class ReplayConversation extends AbstractConversation<Command, Command> {

	private final Command reset;
	
	public ReplayConversation(Command reset) {
		this.reset = reset;
	}

	@Override public void exec(Command todo) {
		todo.execute();
		undos.push(todo);
		redos.clear();
	}

	@Override public void undo() {
		Command latestCmd = undos.pop() ;
		if(latestCmd==null) return;
        redos.push(latestCmd);
        reset.execute();
        undos.forEachFifo(Command::execute);
	}

	@Override public void redo() {
		Command latestCmd = redos.pop() ;
		if(latestCmd==null) return;
		latestCmd.execute();
        undos.push(latestCmd); 
	}
	
}
