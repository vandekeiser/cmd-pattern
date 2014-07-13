package cla.domain;

import cla.domain.carrots.CarrotRepository;
import cla.domain.typing.Display;


//aka ExecutionEnvironment
public interface Env {

	CarrotRepository carrotRepository();

	Display display();

}
