package cla.domain;

import java.util.ArrayList;
import java.util.LinkedList;

public class SimpleStack<T> {
	
	private final LinkedList<T> stack = new LinkedList<>();

	public void push(T t) {
		stack.push(t);
	}

	public void pop() {
		stack.pop();		
	}

	public Iterable<T> allElements() {
		return new ArrayList<T>(stack);
	}

	public void setSingleElement(T singleElement) {
		stack.clear();
		stack.push(singleElement);
	}
	
}
