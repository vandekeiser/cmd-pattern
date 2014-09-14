package cla.command.undo.memento;


/**
 * Implementations must be immutable (the memento must capture a snapshot)
 */
@FunctionalInterface
public interface Memento {
	void restore();
}
