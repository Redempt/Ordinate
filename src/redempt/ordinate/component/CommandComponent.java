package redempt.ordinate.component;

import redempt.ordinate.data.CommandContext;
import redempt.ordinate.data.CommandResult;
import redempt.ordinate.help.HelpComponent;

import java.util.List;

public abstract class CommandComponent<T> {
	
	private int index;
	private CommandComponent<T> parent;
	private int depth;
	
	protected void setParent(CommandComponent<T> parent) {
		this.parent = parent;
		depth = parent == null ? 0 : parent.depth + 1;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}
	
	public int getDepth() {
		return depth;
	}
	
	public CommandComponent<T> getParent() {
		return parent;
	}
	
	public CommandResult<T> success() {
		return new CommandResult<>(this, null);
	}
	
	public CommandResult<T> failure(String... error) {
		return new CommandResult<>(this, error);
	}
	
	public List<String> completions(CommandContext<T> context) {
		return null;
	}

	public abstract int getMaxConsumedArgs();
	public abstract int getMaxParsedObjects();
	public abstract int getPriority();
	public abstract HelpComponent getHelpDisplay();
	public abstract boolean canParse(CommandContext<T> context);
	public abstract CommandResult<T> parse(CommandContext<T> context);
	
}
