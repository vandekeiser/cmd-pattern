package cla.command.undo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cla.command.BaseCommandTest;
import cla.command.Command;

public abstract class CommandUndoTest_Typing<C extends Command> extends BaseCommandTest {

	protected abstract Conversation<C> newConversation();
	protected abstract C typeString(String stringToType);
	
	/**
	 * Basic undo
	 * a    --> "a" 
	 * undo --> ""
	 */
	@Test public void basicUndo() {
		Conversation<C> commands = newConversation();
		
		commands.exec(typeString("a"));
		assertEquals("a", display.displayed());
		
		commands.undo();
		assertEquals("", display.displayed());
	}
	
	/**
	 * Undo is noop when there were no execs
	 * undo --> ""
	 */
	@Test public void basicNoopUndo() {
		Conversation<C> commands = newConversation();
		
		commands.undo();
		assertEquals("", display.displayed());
	}
	
	/**
	 * Undo is noop when there were no execs, even when undo is called several times 
	 * undo --> ""
	 * undo --> ""
	 */
	@Test public void basicNoopUndoTwice() {
		Conversation<C> commands = newConversation();
		
		commands.undo();
		assertEquals("", display.displayed());
		
		commands.undo();
		assertEquals("", display.displayed());
	}
	
	/**
	 * Undo is noop when there is nothing more to undo
	 * a    --> "a" 
	 * undo --> ""
	 * undo --> ""
	 */
	@Test public void noopUndo() {
		Conversation<C> commands = newConversation();
		
		commands.exec(typeString("a"));
		assertEquals("a", display.displayed());
		
		commands.undo();
		assertEquals("", display.displayed());
		
		commands.undo();
		assertEquals("", display.displayed());
	}
	
	/**
	 * Basic redo
	 * a    --> "a" 
	 * undo --> ""
	 * redo --> "a"
	 */
	@Test public void basicRedo() {
		Conversation<C> commands = newConversation();
		
		commands.exec(typeString("a"));
		assertEquals("a", display.displayed());
		
		commands.undo();
		assertEquals("", display.displayed());
		
		commands.redo();
		assertEquals("a", display.displayed());
	}
	
	/**
	 * Redo is noop when there were no undos
	 * redo --> ""
	 */
	@Test public void basicNoopRedo() {
		Conversation<C> commands = newConversation();
		
		commands.redo();
		assertEquals("", display.displayed());
	}
	
	/**
	 * Redo is noop when there were no undos, even when redo is called several times 
	 * redo --> ""
	 * redo --> ""
	 */
	@Test public void basicNoopRedoTwice() {
		Conversation<C> commands = newConversation();
		
		commands.redo();
		assertEquals("", display.displayed());
		
		commands.redo();
		assertEquals("", display.displayed());
	}
	
	/**
	 * Slightly more complex interaction between undo and redo
	 * a    --> "a" 
	 * undo --> ""
	 * redo --> "a"
	 * undo --> ""
	 */
	@Test public void exec_undo_redo_undo() {
		Conversation<C> commands = newConversation();
		
		commands.exec(typeString("a"));
		assertEquals("a", display.displayed());
		
		commands.undo();
		assertEquals("", display.displayed());
		
		commands.redo();
		assertEquals("a", display.displayed());
		
		commands.undo();
		assertEquals("", display.displayed());
	}
	
	/**
	 * a    --> "a"
	 * b    --> "ab" 
	 * undo --> "a"
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
	
	/**
	 * a    --> "a"
	 * b    --> "ab" 
	 * undo --> "a"
	 * redo --> "ab"
	 */
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
	
	/**
	 * a    --> "a"
	 * b    --> "ab" 
	 * undo --> "a"
	 * undo --> ""
	 * redo --> "a"
	 * redo --> "ab"
	 */
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
	
}
