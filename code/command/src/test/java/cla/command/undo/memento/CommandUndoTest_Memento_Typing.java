package cla.command.undo.memento;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cla.command.BaseCommandTest;
import cla.command.CommandFactory;
import cla.command.undo.memento.MementoConversation;

public class CommandUndoTest_Memento_Typing extends BaseCommandTest {

	/**
	 * a    --> "a" 
	 * undo --> ""
	 */
	@Test public void undo() {
		MementoConversation commands = new MementoConversation(env);
		
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
		MementoConversation commands = new MementoConversation(env);
		
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
		MementoConversation commands = new MementoConversation(env);
		
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
		MementoConversation commands = new MementoConversation(env);
		
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
		MementoConversation commands = new MementoConversation(env);
		
		commands.undo();
		assertEquals("", env.display().displayed());
	}
	/**
	 * undo --> ""
	 * undo --> ""
	 */
	@Test public void nothingToUndoUndo_Noop() {
		MementoConversation commands = new MementoConversation(env);
		
		commands.undo();
		assertEquals("", env.display().displayed());
		
		commands.undo();
		assertEquals("", env.display().displayed());
	}
	
	/**
	 * redo --> ""
	 */
	@Test public void nothingToRedo_Noop() {
		MementoConversation commands = new MementoConversation(env);
		
		commands.redo();
		assertEquals("", env.display().displayed());
	}
	/**
	 * redo --> ""
	 * redo --> ""
	 */
	@Test public void nothingToRedoRedo_Noop() {
		MementoConversation commands = new MementoConversation(env);
		
		commands.redo();
		assertEquals("", env.display().displayed());
		
		commands.redo();
		assertEquals("", env.display().displayed());
	}

	/**
	 * a    --> "a"
	 * b    --> "b" 
	 * undo --> ""
	 */
	@Test public void typeA_typeB_undo_undo() {
		MementoConversation commands = new MementoConversation(env);
		
		System.out.println();System.out.println();
		commands.exec(CommandFactory.typeString("a"));
		assertEquals("a", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.exec(CommandFactory.typeString("b"));
		assertEquals("ab", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.undo();
		assertEquals("a", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.undo();
		assertEquals("", env.display().displayed());
	}
	
	@Test public void complexConversation() {
		MementoConversation commands = new MementoConversation(env);
		
		commands.exec(CommandFactory.typeString("a"));
		assertEquals("a", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.undo();
		assertEquals("", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.exec(CommandFactory.typeString("b"));
		assertEquals("b", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.exec(CommandFactory.typeString("c"));
		assertEquals("bc", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.exec(CommandFactory.typeString("d"));
		assertEquals("bcd", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.undo();
		assertEquals("bc", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.redo();
		assertEquals("bcd", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.undo();
		assertEquals("bc", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.undo();
		assertEquals("b", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.exec(CommandFactory.typeString("e"));
		assertEquals("be", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.exec(CommandFactory.typeString("f"));
		assertEquals("bef", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.undo();
		assertEquals("be", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.undo();
		assertEquals("b", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.undo();
		assertEquals("", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.redo();
		assertEquals("b", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.redo();
		assertEquals("be", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.redo();
		assertEquals("bef", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.redo();
		assertEquals("bef", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.undo();
		assertEquals("be", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.undo();
		assertEquals("b", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.undo();
		assertEquals("", env.display().displayed());
	}
}
