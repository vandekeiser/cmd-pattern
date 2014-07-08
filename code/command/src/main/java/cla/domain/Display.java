package cla.domain;

import java.util.List;

public interface Display {

	void append(String stringToAppend);

	public void unappend();

	List<String> getState();
	void setState(List<String> state);

	
	public String displayed();
	
}
