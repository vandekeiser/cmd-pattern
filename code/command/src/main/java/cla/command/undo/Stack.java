package cla.command.undo;

import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Consumer;

public class Stack<T> {

	//Delegate to avoid exposing too many Deque methods
	private final Deque<T> stack = new LinkedList<>();
	
	/**
	 * @return null if stack is empty
	 */
	public T pop() {
		return stack.pollLast(); //Not using pop since it throws NoSuchElementException if the deque is empty
	}
	
	public void push(T cmd) {
		stack.addLast(cmd);
	}

	public void clear() {
		stack.clear();
	}
	
	public void forEachFifo(Consumer<? super T> action) {
		stack.stream().forEachOrdered(action);
	}

	@Override public String toString() {
		return stack.toString();
	}
}
