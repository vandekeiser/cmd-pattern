package cla.command.undo.memento;


//@Immutable
class BeforeAfterMemento {

	final Memento before, after;
	
	/**
	 * @param before :ust be immutable
	 * @param after  must be immutable
	 */
	public BeforeAfterMemento(Memento before, Memento after) {
		this.before = before;
		this.after = after;
	}

	@Override public String toString() {
		return String.format("%s{before:%s, after:%s}", BeforeAfterMemento.class.getSimpleName(), before, after);
	}
}
