package commands;

import exceptions.HandsomeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;
import java.util.Stack;

/**
 * Represents a command to delete a task from the task list in Handsome.
 * The command stores the index of the task to be deleted.
 */
public class DeleteCommand extends Command {
    private final int taskNum;

    /**
     * Constructs a DeleteCommand with the index of the task to be deleted.
     *
     * @param taskNum The index of the task to be deleted.
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the command by deleting the task at the specified index from the task list,
     * saving the updated task list to storage, and returning the result.
     *
     * @param tasks The task list from which the task will be deleted.
     * @param ui The UI object used for displaying messages to the user.
     * @param storage The storage object used for saving the updated task list.
     * @param commandStack A stack of command records for undo functionality.
     * @return A string message indicating the task has been successfully deleted.
     * @throws IOException If an I/O error occurs while saving the task list.
     * @throws HandsomeException If an error specific to Handsome occurs during task deletion.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage,
                          Stack<Command> commandStack) throws IOException, HandsomeException {
        assert taskNum >= 0 && taskNum <= tasks.getTasks().size() : "Hey there, try a number within range?";

        if (taskNum < 0 || taskNum >= tasks.getTasksCount()) {
            return "Hey buddy, give me number between 1 and " + tasks.getTasksCount() + " okie?";
        }

        String deletedTask = tasks.deleteCheck(this.taskNum);
        storage.save(tasks.getTasks());
        return deletedTask;
    }

    /**
     * Undo the delete operation by re-adding the deleted task at the specified index
     * and saving the updated task list to storage.
     *
     * @param tasks The task list to which the deleted task will be re-added.
     * @param ui The UI object used for displaying messages during the undo operation.
     * @param storage The storage object used for saving the updated task list.
     * @return A string message indicating the task has been successfully re-added.
     * @throws IOException If an I/O error occurs while saving the task list.
     * @throws HandsomeException If an error specific to Handsome occurs during task re-adding.
     */
    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) throws IOException, HandsomeException {
        assert tasks != null : "Can't delete non existent things haha...";

        if (taskNum < 0 || taskNum >= tasks.getTasksCount()) {
            throw new HandsomeException("Hey buddy, I can't undo that deletion as the number is out of range.");
        }

        String completed = tasks.addTaskAtIndex(taskNum);
        storage.save(tasks.getTasks());
        return completed;
    }
}
