package cla.command.undo.compensation;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import cla.command.BaseCommandTest;
import cla.command.CommandFactory;
import cla.command.undo.snapshot.ConversationSnapshotImpl;

public class CommandUndoTest_Compensation_Typing extends BaseCommandTest {

	/**
	 * a    --> "a" 
	 * undo --> ""
	 */
	@Test public void undo() {
		ConversationCompensationImpl commands = new ConversationCompensationImpl(env);
		
		commands.exec(CommandFactory.typeString("a"));
		assertEquals("a", env.display().displayed());
		
		commands.undo();
		assertEquals("", env.display().displayed());
	}
	
	/**
	 * a    --> "a" 
	 * undo --> ""
	 * redo --> "a"
	 */
	@Test public void redo() {
		ConversationCompensationImpl commands = new ConversationCompensationImpl(env);
		
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
	@Test public void redo_undo() {
		ConversationCompensationImpl commands = new ConversationCompensationImpl(env);
		
		commands.exec(CommandFactory.typeString("a"));
		assertEquals("a", env.display().displayed());
		
		commands.undo();
		assertEquals("", env.display().displayed());
		
		commands.redo();
		assertEquals("a", env.display().displayed());
		
		commands.undo();
		assertEquals("", env.display().displayed());
	}
	

	/**
	 * undo --> ""
	 */
	@Test public void nothingToUndo_Noop() {
		ConversationCompensationImpl commands = new ConversationCompensationImpl(env);
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
	
}
