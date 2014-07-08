package cla.command.undo.compensation;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import cla.command.BaseCommandTest;
import cla.command.CommandFactory;
import cla.command.undo.snapshot.ConversationSnapshotImpl;

public class CommandUndoTest_Compensation_Typing extends BaseCommandTest {

	@Test public void undo() {
		ConversationCompensationImpl commands = new ConversationCompensationImpl(env);
		
		commands.exec(CommandFactory.typeString("a"));
		assertEquals("a", env.display().displayed());
		
		commands.undo();
		assertEquals("", env.display().displayed());
	}
	
	@Test public void redo() {
		ConversationCompensationImpl commands = new ConversationCompensationImpl(env);
		
		commands.exec(CommandFactory.typeString("a"));
		assertEquals("a", env.display().displayed());
		
		commands.undo();
		assertEquals("", env.display().displayed());
		
		commands.redo();
		assertEquals("a", env.display().displayed());
	}
	
	@Test @Ignore public void redo_undo() {
		ConversationCompensationImpl commands = new ConversationCompensationImpl(env);
		
		commands.exec(CommandFactory.addCarrot());
		assertEquals(1, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
		
		commands.redo();
		assertEquals(1, env.carrots().numberOfCarrots());
		
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
	

	@Test @Ignore public void nothingToUndo_Noop() {
		ConversationCompensationImpl commands = new ConversationCompensationImpl(env);
		commands.undo();
		assertEquals(0, env.carrots().numberOfCarrots());
	}
	
}
