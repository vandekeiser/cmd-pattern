package cla.command.undo.snapshot;

import java.util.LinkedList;

class SnapshotStack {

	//La delegation permet de n'exposer que push et pop
	private final LinkedList<Restorable> stack = new LinkedList<>();
	
	public void push(Restorable cmd) {
		stack.push(cmd);
	}

	/**
	 * @return null si la stack est vide
	 */
	public Restorable pop() {
		return stack.pollLast();
	}

}
