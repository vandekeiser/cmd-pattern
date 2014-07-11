package cla.command.undo.replay;

import java.util.LinkedList;

class ResetableCommandStack {

	//La delegation permet de n'exposer que push et pop
	private final LinkedList<ResetableCommand> stack = new LinkedList<>();
	
	public void push(ResetableCommand cmd) {
		stack.addFirst(cmd);
	}

	/**
	 * @return null si la stack est vide
	 */
	public ResetableCommand pop() {
		return stack.pollFirst();
	}

	public void clear() {
		stack.clear();
	}
	
	@Override public String toString() {
		return String.format("%s{stack:%s}", ResetableCommandStack.class.getSimpleName(), stack);
	}
	
}
