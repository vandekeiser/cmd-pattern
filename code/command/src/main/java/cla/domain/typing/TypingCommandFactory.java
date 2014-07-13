package cla.domain.typing;


public enum TypingCommandFactory {

	; //Using no-values enum to emulate top-level static class
	
	public static TypeString typeString(Display display, String stringToType) {
		return new TypeString(display, stringToType);
	}

}
