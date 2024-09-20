package commands;

import exceptions.HandsomeException;
import storage.Storage;

import task.Task;
import task.TaskList;

import ui.Ui;

import java.io.IOException;
import java.util.Stack;

/**
 * Represents a command to add a task to the task list in Handsome.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddCommand with the specified task to be added.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command by adding the specified task to the task list,
     * saving the updated task list to storage, and returning the result.
     *
     * @param tasks The task list to which the task is added.
     * @param ui The UI object used for displaying messages to the user.
     * @param storage The storage object used for saving the updated task list.
     * @param commandRecords A stack of command records for undo functionality.
     * @return A string message indicating the task has been successfully added.
     * @throws IOException If an I/O error occurs while saving the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, Stack<Command> commandRecords) throws IOException {
        String addedTask = tasks.addTask(task);
        storage.save(tasks.getTasks());
        return addedTask;
    }

    /**
     * Undo the add operation by removing the last task that was added
     * and saving the updated task list to storage.
     *
     * @param tasks The task list from which the last added task is removed.
     * @param ui The UI object used for displaying messages during the undo operation.
     * @param storage The storage object used for saving the updated task list.
     * @return A string message indicating the task has been successfully removed.
     * @throws HandsomeException If there are errors specific to Handsome during the undo operation.
     * @throws IOException If an I/O error occurs while saving the task list.
     */
    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) throws HandsomeException, IOException {
        int taskNum = tasks.getTasks().size() - 1;
        String completed = tasks.deleteCheck(taskNum);
        storage.save(tasks.getTasks());
        return completed;
    }
}
