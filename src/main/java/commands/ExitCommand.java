package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Hey it has been a nice conversation, good day ahead!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
