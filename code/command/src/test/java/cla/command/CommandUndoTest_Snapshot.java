package cla.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cla.command.undo.snapshot.SequenceOfCommands_SnapshotImpl;

public class CommandUndoTest_Snapshot extends BaseCommandTest {

	@Test public void undo() {
		SequenceOfCommands_SnapshotImpl commands = new SequenceOfCommands_SnapshotImpl(env);
		
		commands.ddo(Commands.addCarrot());
		assertEquals(1, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
	
		
}
