package cla.domain.carrots;

import cla.domain.typing.TypeString;


public enum CarrotCommandFactory {

	; //Using no-values enum to emulate top-level static class
	
	public static AddCarrot addCarrot() {
		return new AddCarrot();
	}

	public static TypeString typeString(String stringToType) {
		return new TypeString(stringToType);
	}

}
