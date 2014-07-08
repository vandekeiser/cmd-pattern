package cla.domain.carrots;

import java.util.Set;


public interface Carrots {

	void addCarrot(Carrot carrot);

	int numberOfCarrots();

	void removeCarrot(Carrot carrot);

	/**
	 * @return A defensive copy of the set of all carrots
	 */
	Set<Carrot> getAllCarrots();
	
	/**
	 * Resets state to a defensive copy (to avoid issues if the caller modifies newState after calling this method)
	 * @param newState
	 */
	void setAllCarrots(Set<Carrot> newState);

}
