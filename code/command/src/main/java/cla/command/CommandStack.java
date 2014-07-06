package cla.command;

import java.util.LinkedList;

public class CommandStack {

	//La delegation permet de n'exposer que push et pop
	private final LinkedList<Command> stack = new LinkedList<>();
	
	public void push(Command cmd) {
		stack.push(cmd);
	}

	public Command pop() {
		return stack.pop();
	}

}
