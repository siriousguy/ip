package commands;

import task.Task;
import task.TaskList;
import ui.Ui;
import storage.Storage;
import exceptions.HandsomeException;

import java.util.List;

/**
 * Finds the corresponding tasks with the keyword requested by the user.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HandsomeException {
        List<Task> matchedTasks = tasks.findTasks(keyword);
        if (matchedTasks.isEmpty()) {
            Ui.show("Sadly, no matching tasks found.");
        } else {
            Ui.show("I got you, these are the matching tasks!");
            for (int i = 0; i < matchedTasks.size(); i++) {
                Ui.show((i + 1) + "." + matchedTasks.get(i).toString());
            }
        }
    }
}
