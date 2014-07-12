package cla.domain;

import cla.domain.carrots.CarrotRepository;
import cla.domain.carrots.typing.Display;


//aka ExecutionEnvironment
public interface Env {

	CarrotRepository carrots();

	Display display();

}
