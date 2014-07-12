package cla.domain.typing;


public enum TypingCommandFactory {

	; //Using no-values enum to emulate top-level static class
	
	public static TypeString typeString(String stringToType) {
		return new TypeString(stringToType);
	}

}
