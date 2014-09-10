package cla.domain.typing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Display {

	private final LinkedList<String> displayElements = new LinkedList<>();
	
	public void append(String stringToAppend) {
		displayElements.addLast(stringToAppend);
	}

	public void unappend() {
		displayElements.removeLast();
	}

	public String displayed() {
		StringBuilder sb = new StringBuilder();
		for(String s : displayElements) sb.append(s);
		return sb.toString();
	}

	/**
	 * @return A defensive copy of the current display state.
	 */
	public List<String> getState() {
		return new ArrayList<String>(displayElements);
	}

	/**
	 * Sets display state to a defensive copy of state.
	 * @param state
	 */
	public void setState(List<String> newState) {
		displayElements.clear();
		displayElements.addAll(newState);
	}

	public void reset() {
		displayElements.clear();
	}

}
