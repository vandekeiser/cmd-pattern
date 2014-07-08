package cla.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//<string>?
class SimpleStack<T> {
	
	private final LinkedList<T> stack;

	private SimpleStack(LinkedList<T> stack) {
		this.stack = stack;
	}
	
	public SimpleStack() {
		this(new LinkedList<>());
	}

	public void push(T t) {
		stack.push(t);
	}

	public void pop() {
		stack.pop();		
	}

	public SimpleStack<T> defensiveCopy() {
		return new SimpleStack<T>(stack);
	}

	public void resetTo(SimpleStack<T> newState) {
		stack.clear();
		stack.addAll(newState.stack);
	}

	public Iterable<T> allElementsInOrder() {
		List<T> all = new ArrayList<T>(stack);
		Collections.sort(all, Collections.reverseOrder());
		return all; 
	}
	
	@Override public String toString() {
		return String.format("%s{stack: %s}", SimpleStack.class.getSimpleName(), stack);
	}

}
