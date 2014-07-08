package cla.command.services;

import cla.domain.Display;
import cla.domain.SimpleStack;

public class TestDisplay implements Display {

	private final SimpleStack<String> stack = new SimpleStack<String>();
	
	@Override public void append(String stringToAppend) {
		stack.push(stringToAppend);
	}

	@Override public void unappend() {
		stack.pop();
	}

	@Override public String displayed() {
		StringBuilder sb = new StringBuilder();
		for(String s : stack.allElements()) sb.append(s);
		return sb.toString();
	}

	@Override public void setDisplay(String snapshot) {
		stack.setSingleElement(snapshot);
	}

}
