package cla.command.undo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cla.command.BaseCommandTest;
import cla.command.Command;
import cla.domain.Env;

public abstract class CommandUndoTest_Typing<C extends Command> extends BaseCommandTest {

	protected abstract Conversation<C> newConversation(Env env);
	protected abstract C typeString(String stringToType);
	
	/**
	 * a    --> "a" 
	 * undo --> ""
	 */
	@Test public void undo() {
		Conversation<C> commands = newConversation(env);
		
		commands.exec(typeString("a"));
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
		Conversation<C> commands = newConversation(env);
		
		commands.exec(typeString("a"));
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
		Conversation<C> commands = newConversation(env);
		
		commands.exec(typeString("a"));
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
		Conversation<C> commands = newConversation(env);
		
		commands.exec(typeString("a"));
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
		Conversation<C> commands = newConversation(env);
		
		commands.undo();
		assertEquals("", env.display().displayed());
	}
	/**
	 * undo --> ""
	 * undo --> ""
	 */
	@Test public void nothingToUndoUndo_Noop() {
		Conversation<C> commands = newConversation(env);
		
		commands.undo();
		assertEquals("", env.display().displayed());
		
		commands.undo();
		assertEquals("", env.display().displayed());
	}
	
	/**
	 * redo --> ""
	 */
	@Test public void nothingToRedo_Noop() {
		Conversation<C> commands = newConversation(env);
		
		commands.redo();
		assertEquals("", env.display().displayed());
	}
	/**
	 * redo --> ""
	 * redo --> ""
	 */
	@Test public void nothingToRedoRedo_Noop() {
		Conversation<C> commands = newConversation(env);
		
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
		Conversation<C> commands = newConversation(env);
		
		System.out.println();System.out.println();
		commands.exec(typeString("a"));
		assertEquals("a", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.exec(typeString("b"));
		assertEquals("ab", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.undo();
		assertEquals("a", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.undo();
		assertEquals("", env.display().displayed());
	}
	
	@Test public void complexConversation() {
		Conversation<C> commands = newConversation(env);
		
		commands.exec(typeString("a"));
		assertEquals("a", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.undo();
		assertEquals("", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.exec(typeString("b"));
		assertEquals("b", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.exec(typeString("c"));
		assertEquals("bc", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.exec(typeString("d"));
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
		commands.exec(typeString("e"));
		assertEquals("be", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.exec(typeString("f"));
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
	
	@Test public void exec_exec_undo_redo() {
		Conversation<C> commands = newConversation(env);
		
		commands.exec(typeString("a"));
		assertEquals("a", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.exec(typeString("b"));
		assertEquals("ab", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.undo();
		assertEquals("a", env.display().displayed());
		
		System.out.println();System.out.println();
		commands.redo();
		assertEquals("ab", env.display().displayed());
	}
	
	@Test public void failureCase() {
		//TODO
	}
}
