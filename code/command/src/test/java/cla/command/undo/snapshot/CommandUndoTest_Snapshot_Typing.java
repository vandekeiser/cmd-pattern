package cla.command.undo.snapshot;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cla.command.BaseCommandTest;
import cla.command.CommandFactory;

public class CommandUndoTest_Snapshot_Typing extends BaseCommandTest {

	/**
	 * a    --> "a" 
	 * undo --> ""
	 */
	@Test public void undo() {
		ConversationSnapshotImpl commands = new ConversationSnapshotImpl(env);
		
		commands.exec(CommandFactory.typeString("a"));
		assertEquals("a", env.display().displayed());
		
		commands.undo();
		assertEquals("", env.display().displayed());
	}
	
	/**
	 * a    --> "a" 
	 * undo --> ""
	 * undo --> ""
	 */
	@Test public void undo_undo() {
		ConversationSnapshotImpl commands = new ConversationSnapshotImpl(env);
		
		commands.exec(CommandFactory.typeString("a"));
		assertEquals("a", env.display().displayed());
		
		commands.undo();
		assertEquals("", env.display().displayed());
		
		commands.undo();
		assertEquals("", env.display().displayed());
	}
	
	/**
	 * a    --> "a" 
	 * undo --> ""
	 * redo --> "a"
	 */
	@Test public void undo_redo() {
		ConversationSnapshotImpl commands = new ConversationSnapshotImpl(env);
		
		commands.exec(CommandFactory.typeString("a"));
		assertEquals("a", env.display().displayed());
		
		commands.undo();
		assertEquals("", env.display().displayed());
		
		commands.redo();
		assertEquals("a", env.display().displayed());
	}
	
	/**
	 * a    --> "a" 
	 * undo --> ""
	 * redo --> "a"
	 * undo --> ""
	 */
	@Test public void undo_redo_undo() {
		ConversationSnapshotImpl commands = new ConversationSnapshotImpl(env);
		
		commands.exec(CommandFactory.typeString("a"));
		assertEquals("a", env.display().displayed());
		
		commands.undo();
		assertEquals("", env.display().displayed());
		
		commands.redo();
		assertEquals("a", env.display().displayed());
	}
	

	/**
	 * undo --> ""
	 */
	@Test public void nothingToUndo_Noop() {
		ConversationSnapshotImpl commands = new ConversationSnapshotImpl(env);
		
		commands.undo();
		assertEquals("", env.display().displayed());
	}
	/**
	 * undo --> ""
	 * undo --> ""
	 */
	@Test public void nothingToUndoUndo_Noop() {
		ConversationSnapshotImpl commands = new ConversationSnapshotImpl(env);
		
		commands.undo();
		assertEquals("", env.display().displayed());
		
		commands.undo();
		assertEquals("", env.display().displayed());
	}
	
	/**
	 * redo --> ""
	 */
	@Test public void nothingToRedo_Noop() {
		ConversationSnapshotImpl commands = new ConversationSnapshotImpl(env);
		
		commands.redo();
		assertEquals("", env.display().displayed());
	}
	/**
	 * redo --> ""
	 * redo --> ""
	 */
	@Test public void nothingToRedoRedo_Noop() {
		ConversationSnapshotImpl commands = new ConversationSnapshotImpl(env);
		
		commands.redo();
		assertEquals("", env.display().displayed());
		
		commands.redo();
		assertEquals("", env.display().displayed());
	}
		
}
