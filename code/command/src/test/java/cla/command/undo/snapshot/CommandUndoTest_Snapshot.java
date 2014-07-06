package cla.command.undo.snapshot;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import cla.command.BaseCommandTest;
import cla.command.CommandFactory;

public class CommandUndoTest_Snapshot extends BaseCommandTest {

	@Test public void undo() {
		SequenceOfCommands_SnapshotImpl commands = new SequenceOfCommands_SnapshotImpl(env);
		
		commands.exec(CommandFactory.addCarrot());
		assertEquals(1, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
	
	@Test public void undo_undo() {
		SequenceOfCommands_SnapshotImpl commands = new SequenceOfCommands_SnapshotImpl(env);
		
		commands.exec(CommandFactory.addCarrot());
		assertEquals(1, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
	
	@Test public void undo_redo() {
		SequenceOfCommands_SnapshotImpl commands = new SequenceOfCommands_SnapshotImpl(env);
		
		commands.exec(CommandFactory.addCarrot());
		assertEquals(1, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
		
		commands.redo();
		assertEquals(1, env.carrots().numberOfCarrots());
	}
	
	@Test public void undo_redo_undo() {
		SequenceOfCommands_SnapshotImpl commands = new SequenceOfCommands_SnapshotImpl(env);
		
		commands.exec(CommandFactory.addCarrot());
		assertEquals(1, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
		
		commands.redo();
		assertEquals(1, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
	

	@Test public void nothingToUndo_Noop() {
		SequenceOfCommands_SnapshotImpl commands = new SequenceOfCommands_SnapshotImpl(env);
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
	@Test public void nothingToUndoUndo_Noop() {
		SequenceOfCommands_SnapshotImpl commands = new SequenceOfCommands_SnapshotImpl(env);
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
	
	@Test public void nothingToRedo_Noop() {
		SequenceOfCommands_SnapshotImpl commands = new SequenceOfCommands_SnapshotImpl(env);
		commands.redo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
	@Test public void nothingToRedoRedo_Noop() {
		SequenceOfCommands_SnapshotImpl commands = new SequenceOfCommands_SnapshotImpl(env);
		commands.redo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
		
}
