package cla.command.undo.compensation;

import java.util.LinkedList;

import cla.command.Command;

class CommandStack {

	//La delegation permet de n'exposer que push et pop
	private final LinkedList<Command> stack = new LinkedList<>();
	
	public void push(Command cmd) {
		stack.push(cmd);
	}

	/**
	 * @return null si la stack est vide
	 */
	public Command pop() {
		return stack.pollLast();
	}

}
