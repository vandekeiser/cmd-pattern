package cla.domain;

import java.io.PrintStream;

import cla.domain.carrots.Carrots;


//aka ExecutionEnvironment
public interface Env {

	Carrots carrots();

	Display display();

}
