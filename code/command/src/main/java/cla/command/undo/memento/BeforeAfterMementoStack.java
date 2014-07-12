package cla.command.undo.memento;

import java.util.LinkedList;

class BeforeAfterMementoStack {

	//La delegation permet de n'exposer que push et pop
	private final LinkedList<BeforeAfterMemento> stack = new LinkedList<>();
	
	public void push(BeforeAfterMemento cmd) {
		stack.addLast(cmd);
	}

	/**
	 * @return null si la stack est vide
	 */
	public BeforeAfterMemento pop() {
		return stack.pollLast();
	}

	public void clear() {
		stack.clear();
	}
	
	@Override public String toString() {
		return String.format("%s{stack:%s}", BeforeAfterMementoStack.class.getSimpleName(), stack);
	}
	
}
