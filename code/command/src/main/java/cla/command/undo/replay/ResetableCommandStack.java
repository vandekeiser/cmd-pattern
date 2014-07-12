package cla.command.undo.replay;

import java.util.LinkedList;

import cla.domain.Env;

class ResetableCommandStack {

	//La delegation permet de n'exposer que push et pop
	private final LinkedList<ResetableCommand> stack = new LinkedList<>();
	
	public void push(ResetableCommand cmd) {
		stack.addLast(cmd);
	}

	/**
	 * @return null si la stack est vide
	 */
	public ResetableCommand pop() {
		return stack.pollLast();
	}

	public void clear() {
		stack.clear();
	}
	
	@Override public String toString() {
		return String.format("%s{stack:%s}", ResetableCommandStack.class.getSimpleName(), stack);
	}

	public void replay(Env env) {
//		stack.stream().forEach(cmd->{
//			cmd.resetCmd().execute(env);
//		});	
		stack.stream().forEachOrdered(cmd->{
			cmd.execute(env);
		});
//		stack.stream().limit(stack.size()-1).forEachOrdered(cmd->{
//			cmd.execute(env);
//		});
	}

	public int size() {
		return stack.size();
	}
	
}
