package cla.domain.typing;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Display {

	private final LinkedList<String> displayElements = new LinkedList<>();
	
	public void append(String stringToAppend) {
		displayElements.addLast(stringToAppend);
	}

	public void unappend() {
		displayElements.removeLast();
	}

	public String displayed() {
		return displayElements.stream().collect(Collectors.joining());
	}

	/**
	 * @return A defensive copy of the current display state.
	 */
	public List<String> getState() {
		return new LinkedList<String>(displayElements);
	}

	/**
	 * Sets display state to a defensive copy of state.
	 * @param state
	 */
	public void setState(List<String> newState) {
		displayElements.clear();
		displayElements.addAll(newState);
	}

	public void clear() {
		displayElements.clear();
	}

}
