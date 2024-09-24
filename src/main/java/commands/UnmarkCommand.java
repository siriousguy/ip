package commands;

import exceptions.HandsomeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;
import java.util.Stack;

/**
 * Represents a command to unmark a task as done in the task list.
 * The command marks the specified task as not completed and allows the operation to be undone.
 */
public class UnmarkCommand extends Command {
    private final int taskNum;

    /**
     * Constructs an UnmarkCommand with the index of the task to be unmarked as done.
     *
     * @param taskNum The index of the task to be unmarked as done.
     */
    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the command by marking the task at the specified index as not done,
     * saving the updated task list to storage, and returning the result.
     *
     * @param tasks The task list containing the task to be unmarked as done.
     * @param ui The UI object used for displaying messages to the user.
     * @param storage The storage object used for saving the updated task list.
     * @param commandRecords A stack of command records for undo functionality.
     * @return A string message indicating the task has been successfully unmarked as done.
     * @throws IOException If an I/O error occurs while saving the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, Stack<Command> commandRecords) throws IOException {
        if (taskNum < 0 || taskNum >= tasks.getTasksCount()) {
            return "Hey buddy, give me number between 1 and " + tasks.getTasksCount() + " okie?";
        }

        String undone = tasks.markUndone(taskNum);
        storage.save(tasks.getTasks());
        return undone;
    }

    /**
     * Undo the unmark operation by marking the task at the specified index as done again,
     * saving the updated task list to storage, and returning the result.
     *
     * @param tasks The task list containing the task to be marked as done.
     * @param ui The UI object used for displaying messages during the undo operation.
     * @param storage The storage object used for saving the updated task list.
     * @return A string message indicating the task has been successfully marked as done again.
     * @throws IOException If an I/O error occurs while saving the task list.
     */
    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) throws IOException, HandsomeException {
        if (taskNum < 0 || taskNum >= tasks.getTasksCount()) {
            throw new HandsomeException("Hey buddy, I can't undo that unmark command as the number is out of range.");
        }

        String undo = tasks.markDone(taskNum);
        storage.save(tasks.getTasks());
        return undo;
    }
}
