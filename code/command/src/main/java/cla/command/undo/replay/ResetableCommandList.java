package cla.command.undo.replay;

import java.util.ArrayList;
import java.util.List;

import cla.domain.Env;

public class ResetableCommandList {

	private final List<ResetableCommand> list = new ArrayList<>();
	
	public void add(ResetableCommand todo) {
		list.add(todo);
	}

	public void replayUpTo(int position, Env env) {
		list.stream().limit(position).forEachOrdered(cmd->{
			cmd.execute(env);
		});
	}

}
