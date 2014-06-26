package cla.command;

import cla.domain.carrots.Carrot;

public enum Commands {

	; //Using no-values enum to emulate C# static class
	
	public static class AddCarrot implements Command {

		@Override public void execute(Env env) {
			env.carrots().addCarrot(new Carrot());
		}
		
	}

	public static Command addCarrot() {
		return new AddCarrot();
	}

}
