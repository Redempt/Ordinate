package redempt.ordinate.data;

import redempt.ordinate.command.Command;

public class CommandContext<T> {

	private Command<T> command;
	private CommandContext<T> parent;
	private T sender;
	private SplittableList<Argument> args;
	private Object[] parsed;
	private int lastParsed = -1;
	
	public CommandContext(Command<T> command, CommandContext<T> parent, T sender, SplittableList<Argument> args, int processAllocation) {
		this.parent = parent;
		this.sender = sender;
		this.args = args;
		parsed = new Object[processAllocation + 1];
		parsed[0] = sender;
	}
	
	public Command<T> getCommand() {
		return command;
	}
	
	public Argument peekArg() {
		return args.peek();
	}
	
	public Argument pollArg() {
		return args.poll();
	}
	
	public SplittableList<Argument> getArguments() {
		return args;
	}
	
	public T sender() {
		return sender;
	}
	
	public int getLastParsed() {
		return lastParsed;
	}
	
	public void setParsed(int pos, Object parsed) {
		lastParsed = Math.max(lastParsed, pos);
		this.parsed[pos + 1] = parsed;
	}
	
	public Object getParsed(int pos) {
		return parsed[pos + 1];
	}
	
	public int getArgCount() {
		return parsed.length - 1;
	}
	
	public CommandContext<T> getParent() {
		return parent;
	}
	
	public Object lastParsed(int pos) {
		if (pos == 0 && parent != null && parent.parsed.length > 1) {
			return parent.parsed[parent.parsed.length - 1];
		}
		if (pos == 0) {
			return null;
		}
		return parsed[pos];
	}
	
	public CommandContext<T> clone(Command<T> command) {
		return new CommandContext<>(command, parent, sender, args.split(0), parsed.length - 1);
	}
	
}
