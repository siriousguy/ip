package commands;

import task.TaskList;
import ui.Ui;
import storage.Storage;

import java.util.Stack;

/**
 * Represents a command to list all tasks in the task list.
 * The command displays all tasks currently stored in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the command by returning the string representation of the task list.
     *
     * @param tasks The task list containing the tasks to be displayed.
     * @param ui The UI object used for displaying messages to the user.
     * @param storage The storage object, not used in this command.
     * @param commandRecords A stack of command records, not used in this command.
     * @return A string representing the list of all tasks in the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, Stack<Command> commandRecords) {
        return tasks.printList();
    }

    /**
     * Undo the list operation, in the event it happens.
     *
     * @param tasks The task list, not used in this command.
     * @param ui The UI object used for displaying messages.
     * @param storage The storage object, not used in this command.
     * @return A message indicating that undoing a list command is not possible.
     */
    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) {
        return "I am unfortunately, unable to undo such a command.";
    }
}
