package cla.command.undo.compensation;

import java.util.LinkedList;

class CompensableCommandStack {

	//La delegation permet de n'exposer que push et pop
	private final LinkedList<CompensableCommand> stack = new LinkedList<>();
	
	public void push(CompensableCommand cmd) {
		stack.addLast(cmd);
	}

	/**
	 * @return null si la stack est vide
	 */
	public CompensableCommand pop() {
		return stack.pollLast();
	}

	public void clear() {
		stack.clear();
	}
	
	@Override public String toString() {
		return String.format("%s{stack:%s}", CompensableCommandStack.class.getSimpleName(), stack);
	}
	
}
