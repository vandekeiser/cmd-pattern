package cla.command.undo.snapshot;

import java.util.LinkedList;

class MementoStack {

	//La delegation permet de n'exposer que push et pop
	private final LinkedList<Memento> stack = new LinkedList<>();
	
	public void push(Memento cmd) {
		stack.push(cmd);
	}

	/**
	 * @return null si la stack est vide
	 */
	public Memento pop() {
		return stack.pollFirst();
	}

	public void clear() {
		stack.clear();
	}
	
	@Override public String toString() {
		return String.format("%s{stack:%s}", MementoStack.class.getSimpleName(), stack);
	}
	
}
