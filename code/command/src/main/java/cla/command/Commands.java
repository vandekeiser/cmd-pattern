package cla.command;

import cla.command.env.Env;
import cla.domain.carrots.Carrot;

public enum Commands {

	; //Using no-values enum to emulate C# static class
	
	public static class AddCarrot implements Command {

		private Carrot addedCarrot;
		
		@Override public void execute(Env env) {
			addedCarrot = new Carrot();
			env.carrots().addCarrot(addedCarrot);
		}

		@Override public void undo(Env env) {
			env.carrots().removeCarrot(addedCarrot);
		}
		
	}

	public static Command addCarrot() {
		return new AddCarrot();
	}

}
