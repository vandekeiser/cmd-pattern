package cla.command.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cla.domain.typing.Display;

public class TestDisplay implements Display {

	private final LinkedList<String> displayElements = new LinkedList<>();
	
	@Override public void append(String stringToAppend) {
		displayElements.addLast(stringToAppend);
	}

	@Override public void unappend() {
		displayElements.removeLast();
	}

	@Override public String displayed() {
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

	@Override public void reset() {
		displayElements.clear();
	}

}
