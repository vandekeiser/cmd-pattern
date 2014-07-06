package cla.command;

public class UndoableCommands_StackImpl implements UndoableCommands {

	private final Env env;
	private final CommandStack stack;
	
	public UndoableCommands_StackImpl(Env env) {
		this.env = env;
		this.stack = new CommandStack();
	}

	@Override public void ddo(Command todo) {
		todo.execute(this.env);
		stack.push(todo);
	}

	@Override public void undo() {
		Command last = stack.pop();
		if(last==null) return;//Dans une application quand la stack d'undo est vide, on ne fait rien (on ne crashe pas) 
		last.undo(env);
	}

}
