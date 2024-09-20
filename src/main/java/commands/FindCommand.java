package commands;

import task.Task;
import task.TaskList;
import ui.Ui;
import storage.Storage;
import exceptions.HandsomeException;

import java.util.List;
import java.util.Stack;

/**
 * Finds the corresponding tasks with the keyword requested by the user.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in the task list.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command by searching the task list for tasks containing the keyword.
     * If matching tasks are found, they are returned; otherwise, a message indicating
     * no matches is returned.
     *
     * @param tasks The task list to search through.
     * @param ui The UI object used for displaying messages to the user.
     * @param storage The storage object, not used in this command.
     * @param commandRecords A stack of command records, not used in this command.
     * @return A string representing the list of tasks that match the keyword, or a message if no matches are found.
     * @throws HandsomeException If an error specific to Handsome occurs during the find operation.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage,
                          Stack<Command> commandRecords) throws HandsomeException {
        List<Task> matchedTasks = tasks.findTasks(keyword);
        if (matchedTasks.isEmpty()) {
            return "Sadly, no matching tasks found.";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("I got you, these are the matching tasks! \n");
            for (int i = 0; i < matchedTasks.size(); i++) {
                sb.append((i + 1)).append(".").append(matchedTasks.get(i).toString()).append("\n");
            }
            return sb.toString();
        }
    }

    @Override
    public String undo(TaskList taskList, Ui ui, Storage storage) {
        return "Well... what do you think you will get from undoing a find?";
    }
}
