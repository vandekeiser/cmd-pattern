package cla.domain.carrots;



public enum CarrotCommandFactory {

	; //Using no-values enum to emulate top-level static class
	
	public static AddCarrot addCarrot(CarrotRepository repository) {
		return new AddCarrot(repository);
	}

}
