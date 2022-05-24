package redempt.ordinate.dispatch;

import redempt.ordinate.help.HelpDisplayer;

public interface CommandManager<T> {

	public CommandRegistrar<T> getRegistrar();
	public HelpDisplayer<T> getHelpDisplayer();

}