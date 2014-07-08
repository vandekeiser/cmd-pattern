package cla.command.undo.snapshot;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cla.command.BaseCommandTest;
import cla.command.CommandFactory;

public class CommandUndoTest_Snapshot extends BaseCommandTest {

	@Test public void undo() {
		ConversationSnapshotImpl commands = new ConversationSnapshotImpl(env);
		
		commands.exec(CommandFactory.addCarrot());
		assertEquals(1, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
	
	@Test public void undo_undo() {
		ConversationSnapshotImpl commands = new ConversationSnapshotImpl(env);
		
		commands.exec(CommandFactory.addCarrot());
		assertEquals(1, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
	
	@Test public void undo_redo() {
		ConversationSnapshotImpl commands = new ConversationSnapshotImpl(env);
		
		commands.exec(CommandFactory.addCarrot());
		assertEquals(1, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
		
		commands.redo();
		assertEquals(1, env.carrots().numberOfCarrots());
	}
	
	@Test public void undo_redo_undo() {
		ConversationSnapshotImpl commands = new ConversationSnapshotImpl(env);
		
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
		ConversationSnapshotImpl commands = new ConversationSnapshotImpl(env);
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
	@Test public void nothingToUndoUndo_Noop() {
		ConversationSnapshotImpl commands = new ConversationSnapshotImpl(env);
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
	
	@Test public void nothingToRedo_Noop() {
		ConversationSnapshotImpl commands = new ConversationSnapshotImpl(env);
		commands.redo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
	@Test public void nothingToRedoRedo_Noop() {
		ConversationSnapshotImpl commands = new ConversationSnapshotImpl(env);
		commands.redo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
		
}
