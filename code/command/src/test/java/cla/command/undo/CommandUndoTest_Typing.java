package cla.command.undo;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import cla.command.BaseCommandTest;
import cla.command.Command;
import cla.command.Conversation;

public abstract class CommandUndoTest_Typing<C extends Command> extends BaseCommandTest {

	protected abstract Conversation<C> newConversation();
	protected abstract C typeString(String stringToType);
	
	/**
	 * a    --> "a" 
	 * undo --> ""
	 */
	@Test public void undo() {
		Conversation<C> commands = newConversation();
		
		commands.exec(typeString("a"));
		assertEquals("a", display.displayed());
		
		commands.undo();
		assertEquals("", display.displayed());
	}
	
	/**
	 * a    --> "a" 
	 * undo --> ""
	 * undo --> ""
	 */
	@Test public void undo_undo() {
		Conversation<C> commands = newConversation();
		
		commands.exec(typeString("a"));
		assertEquals("a", display.displayed());
		
		commands.undo();
		assertEquals("", display.displayed());
		
		commands.undo();
		assertEquals("", display.displayed());
	}
	
	/**
	 * a    --> "a" 
	 * undo --> ""
	 * redo --> "a"
	 */
	@Test public void undo_redo() {
		Conversation<C> commands = newConversation();
		
		commands.exec(typeString("a"));
		assertEquals("a", display.displayed());
		
		commands.undo();
		assertEquals("", display.displayed());
		
		commands.redo();
		assertEquals("a", display.displayed());
	}
	
	/**
	 * a    --> "a" 
	 * undo --> ""
	 * redo --> "a"
	 * undo --> ""
	 */
	@Test public void undo_redo_undo() {
		Conversation<C> commands = newConversation();
		
		commands.exec(typeString("a"));
		assertEquals("a", display.displayed());
		
		commands.undo();
		assertEquals("", display.displayed());
		
		commands.redo();
		assertEquals("a", display.displayed());
	}
	

	/**
	 * undo --> ""
	 */
	@Test public void nothingToUndo_Noop() {
		Conversation<C> commands = newConversation();
		
		commands.undo();
		assertEquals("", display.displayed());
	}
	/**
	 * undo --> ""
	 * undo --> ""
	 */
	@Test public void nothingToUndoUndo_Noop() {
		Conversation<C> commands = newConversation();
		
		commands.undo();
		assertEquals("", display.displayed());
		
		commands.undo();
		assertEquals("", display.displayed());
	}
	
	/**
	 * redo --> ""
	 */
	@Test public void nothingToRedo_Noop() {
		Conversation<C> commands = newConversation();
		
		commands.redo();
		assertEquals("", display.displayed());
	}
	/**
	 * redo --> ""
	 * redo --> ""
	 */
	@Test public void nothingToRedoRedo_Noop() {
		Conversation<C> commands = newConversation();
		
		commands.redo();
		assertEquals("", display.displayed());
		
		commands.redo();
		assertEquals("", display.displayed());
	}

	/**
	 * a    --> "a"
	 * b    --> "b" 
	 * undo --> ""
	 */
	@Test public void typeA_typeB_undo_undo() {
		Conversation<C> commands = newConversation();
		
		commands.exec(typeString("a"));
		assertEquals("a", display.displayed());
		
		commands.exec(typeString("b"));
		assertEquals("ab", display.displayed());
		
		commands.undo();
		assertEquals("a", display.displayed());
		
		commands.undo();
		assertEquals("", display.displayed());
	}
	
	@Test public void complexConversation() {
		Conversation<C> commands = newConversation();
		
		commands.exec(typeString("a"));
		assertEquals("a", display.displayed());
		
		commands.undo();
		assertEquals("", display.displayed());
		
		commands.exec(typeString("b"));
		assertEquals("b", display.displayed());
		
		commands.exec(typeString("c"));
		assertEquals("bc", display.displayed());
		
		commands.exec(typeString("d"));
		assertEquals("bcd", display.displayed());
		
		commands.undo();
		assertEquals("bc", display.displayed());
		
		commands.redo();
		assertEquals("bcd", display.displayed());
		
		commands.undo();
		assertEquals("bc", display.displayed());
		
		commands.undo();
		assertEquals("b", display.displayed());
		
		commands.exec(typeString("e"));
		assertEquals("be", display.displayed());
		
		commands.exec(typeString("f"));
		assertEquals("bef", display.displayed());
		
		commands.undo();
		assertEquals("be", display.displayed());
		
		commands.undo();
		assertEquals("b", display.displayed());
		
		commands.undo();
		assertEquals("", display.displayed());
		
		commands.redo();
		assertEquals("b", display.displayed());
		
		commands.redo();
		assertEquals("be", display.displayed());
		
		commands.redo();
		assertEquals("bef", display.displayed());
		
		commands.redo();
		assertEquals("bef", display.displayed());
		
		commands.undo();
		assertEquals("be", display.displayed());
		
		commands.undo();
		assertEquals("b", display.displayed());
		
		commands.undo();
		assertEquals("", display.displayed());
	}
	
	@Test public void exec_exec_undo_redo() {
		Conversation<C> commands = newConversation();
		
		commands.exec(typeString("a"));
		assertEquals("a", display.displayed());
		
		commands.exec(typeString("b"));
		assertEquals("ab", display.displayed());
		
		commands.undo();
		assertEquals("a", display.displayed());
		
		commands.redo();
		assertEquals("ab", display.displayed());
	}
	
	@Test public void exec_exec_undo_undo() {
		Conversation<C> commands = newConversation();
		
		commands.exec(typeString("a"));
		assertEquals("a", display.displayed());
		
		commands.exec(typeString("b"));
		assertEquals("ab", display.displayed());
		
		commands.undo();
		assertEquals("a", display.displayed());
		
		commands.undo();
		assertEquals("", display.displayed());
	}
	
	@Test public void exec_exec_undo_undo_redo_redo() {
		Conversation<C> commands = newConversation();
		
		commands.exec(typeString("a"));
		assertEquals("a", display.displayed());
		
		commands.exec(typeString("b"));
		assertEquals("ab", display.displayed());
		
		commands.undo();
		assertEquals("a", display.displayed());
		
		commands.undo();
		assertEquals("", display.displayed());
		
		commands.redo();
		assertEquals("a", display.displayed());
		
		commands.redo();
		assertEquals("ab", display.displayed());
	}
	
	@Test @Ignore public void failureCase() {
		//TODO
	}
	
	@Test @Ignore public void undoAvec2TypesDeResets() {
		//TODO
	}
}
