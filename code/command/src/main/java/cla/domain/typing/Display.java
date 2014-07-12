package cla.domain.typing;

import java.util.List;

public interface Display {

	void append(String stringToAppend);

	public void unappend();

	/**
	 * @return A defensive copy of the current display state.
	 */
	List<String> getState();
	
	/**
	 * Sets display state to a defensive copy of state.
	 * @param state
	 */
	void setState(List<String> state);

	
	public String displayed();

	void reset();
	
}
