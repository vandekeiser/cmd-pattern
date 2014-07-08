package cla.command.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cla.domain.Display;
import cla.domain.SimpleStack;

public class TestDisplay implements Display {

	private final List<String> displayElements = new ArrayList<>();
	
	@Override public void append(String stringToAppend) {
		System.out.printf("TestDisplay/append(%s)%n", stringToAppend);
		
		System.out.println("TestDisplay/append/BEFORE/displayElements: " + displayElements);
		displayElements.add(stringToAppend);
		System.out.println("TestDisplay/append/AFTER/displayElements: " + displayElements);
	}

	@Override public void unappend() {
		System.out.println("TestDisplay/unappend/BEFORE/displayElements: " + displayElements);
		displayElements.remove(displayElements.size()-1);
		System.out.println("TestDisplay/unappend/AFTER/displayElements: " + displayElements);
	}

	@Override public String displayed() {
		System.out.println("TestDisplay/displayed/displayElements: " + displayElements);
		StringBuilder sb = new StringBuilder();
		for(String s : displayElements) sb.append(s);
		return sb.toString();
	}

	@Override public List<String> getState() {
		return new ArrayList<String>(displayElements);
	}

	@Override public void setState(List<String> newState) {
		displayElements.clear();
		displayElements.addAll(newState);
	}

}
