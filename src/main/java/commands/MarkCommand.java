package commands;

import exceptions.HandsomeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;
import java.util.Stack;

/**
 * Represents a command to mark a task as done in the task list.
 * The command marks the specified task as completed and allows the operation to be undone.
 */
public class MarkCommand extends Command {
    private final int taskNum;

    /**
     * Constructs a MarkCommand with the index of the task to be marked as done.
     *
     * @param taskNum The index of the task to be marked as done.
     */
    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the command by marking the task at the specified index as done,
     * saving the updated task list to storage, and returning the result.
     *
     * @param tasks The task list containing the task to be marked as done.
     * @param ui The UI object used for displaying messages to the user.
     * @param storage The storage object used for saving the updated task list.
     * @param commandRecords A stack of command records for undo functionality.
     * @return A string message indicating the task has been successfully marked as done.
     * @throws IOException If an I/O error occurs while saving the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, Stack<Command> commandRecords) throws IOException {
        if (taskNum < 0 || taskNum >= tasks.getTasksCount()) {
            return "Hey buddy, give me number between 1 and " + tasks.getTasksCount() + " okie?";
        }

        String doneTask = tasks.markDone(taskNum);
        storage.save(tasks.getTasks());
        return doneTask;
    }

    /**
     * Undo the mark operation by marking the task at the specified index as undone,
     * saving the updated task list to storage, and returning the result.
     *
     * @param tasks The task list containing the task to be marked as undone.
     * @param ui The UI object used for displaying messages during the undo operation.
     * @param storage The storage object used for saving the updated task list.
     * @return A string message indicating the task has been successfully marked as undone.
     * @throws IOException If an I/O error occurs while saving the task list.
     */
    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) throws IOException, HandsomeException {
        if (taskNum < 0 || taskNum >= tasks.getTasksCount()) {
            throw new HandsomeException("Hey buddy, I can't undo that mark command as the number is out of range.");
        }

        String undo = tasks.markUndone(taskNum);
        storage.save(tasks.getTasks());
        return undo;
    }
}
