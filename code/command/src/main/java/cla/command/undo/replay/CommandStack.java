package cla.command.undo.replay;

import java.util.LinkedList;

import cla.command.Command;

class CommandStack {

	//La delegation permet de n'exposer que push et pop
	private final LinkedList<Command> stack = new LinkedList<>();
	
	public void push(Command cmd) {
		stack.addLast(cmd);
	}

	/**
	 * @return null si la stack est vide
	 */
	public Command pop() {
		return stack.pollLast();
	}

	public void clear() {
		stack.clear();
	}
	
	@Override public String toString() {
		return String.format("%s{stack:%s}", CommandStack.class.getSimpleName(), stack);
	}

	public void replay() {
		stack.stream().forEachOrdered(cmd->{
			cmd.execute();
		});
	}

	public int size() {
		return stack.size();
	}
	
}
