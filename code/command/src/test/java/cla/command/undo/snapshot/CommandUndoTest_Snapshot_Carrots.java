package cla.command.undo.snapshot;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cla.command.BaseCommandTest;
import cla.command.CommandFactory;

public class CommandUndoTest_Snapshot_Carrots extends BaseCommandTest {

	@Test public void undo() {
		ConversationMementoImpl commands = new ConversationMementoImpl(env);
		
		commands.exec(CommandFactory.addCarrot());
		assertEquals(1, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
	
	@Test public void undo_undo() {
		ConversationMementoImpl commands = new ConversationMementoImpl(env);
		
		commands.exec(CommandFactory.addCarrot());
		assertEquals(1, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
	
	@Test public void undo_redo() {
		ConversationMementoImpl commands = new ConversationMementoImpl(env);
		
		commands.exec(CommandFactory.addCarrot());
		assertEquals(1, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
		
		commands.redo();
		assertEquals(1, env.carrots().numberOfCarrots());
	}
	
	@Test public void undo_redo_undo() {
		ConversationMementoImpl commands = new ConversationMementoImpl(env);
		
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
		ConversationMementoImpl commands = new ConversationMementoImpl(env);
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
	@Test public void nothingToUndoUndo_Noop() {
		ConversationMementoImpl commands = new ConversationMementoImpl(env);
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
	
	@Test public void nothingToRedo_Noop() {
		ConversationMementoImpl commands = new ConversationMementoImpl(env);
		commands.redo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
	@Test public void nothingToRedoRedo_Noop() {
		ConversationMementoImpl commands = new ConversationMementoImpl(env);
		commands.redo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
		
}
