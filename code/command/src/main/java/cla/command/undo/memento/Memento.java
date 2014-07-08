package cla.command.undo.memento;



public class Memento {

	final Restorable snapshotBefore, snapshotAfter;
	
	public Memento(Restorable snapshotBefore, Restorable snapshotAfter) {
		this.snapshotBefore = snapshotBefore;
		this.snapshotAfter = snapshotAfter;
	}

	@Override public String toString() {
		return String.format("%s{snapshotBefore:%s, snapshotAfter:%s}", Memento.class.getSimpleName(), snapshotBefore, snapshotAfter);
	}
}
