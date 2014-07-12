package cla.command.undo.memento;


//@Immutable
class BeforeAfterMemento {

	final Restorable snapshotBefore, snapshotAfter;
	
	/**
	 * Both snapshots must be immutable.
	 * @param snapshotBefore
	 * @param snapshotAfter
	 */
	public BeforeAfterMemento(Restorable snapshotBefore, Restorable snapshotAfter) {
		this.snapshotBefore = snapshotBefore;
		this.snapshotAfter = snapshotAfter;
	}

	@Override public String toString() {
		return String.format("%s{snapshotBefore:%s, snapshotAfter:%s}", BeforeAfterMemento.class.getSimpleName(), snapshotBefore, snapshotAfter);
	}
}
