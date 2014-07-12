package cla.command.undo.replay;

import java.util.LinkedList;

import cla.command.Command;
import cla.domain.Env;

public class ReplayableList {

	private final LinkedList<Command> resets;
	private final LinkedList<Command> replays;

	ReplayableList() {
		this.resets = new LinkedList<>();
		this.replays = new LinkedList<>();
	}

	public void reset(Env env) {
		resets.stream().forEach(cmd->{
			cmd.execute(env);
		});
	}
	
	public void replay(Env env) {
		replays.stream().forEachOrdered(cmd->{
			cmd.execute(env);
		});		
	}

	
//	public void addLast(ResetableCommand cmd) {
//		resetables.add(cmd);
//	}
//
//	public void clear() {
//		resetables.clear();		
//	}
//
//	public ResetableCommand peekLast() {
//		return resetables.peekLast();
//	}
//	
//	public ResetableCommand removeLast() {
//		return resetables.pollLast();
//	}
//
//	public void reset(Env env) {
//		resetables.stream().forEach(cmd->{
//			cmd.resetCmd().execute(env);
//		});
//	}
//	
//	public void replay(Env env) {
//		resetables.stream().forEachOrdered(cmd->{
//			cmd.execute(env);
//		});
//	}
//	
//	@Override public String toString() {
//		return String.format("%s{resetables: %s}", ReplayableList.class.getSimpleName(), resetables);
//	}
//
//	public ReplayableList copy() {
//		return new ReplayableList(this.resetables);
//	}

}
