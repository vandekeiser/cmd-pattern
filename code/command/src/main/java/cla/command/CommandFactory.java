package cla.command;


public enum CommandFactory {

	; //Using no-values enum to emulate C# static class
	
	public static AddCarrot addCarrot() {
		return new AddCarrot();
	}

	public static TypeString typeString(String stringToType) {
		return new TypeString(stringToType);
	}

}
