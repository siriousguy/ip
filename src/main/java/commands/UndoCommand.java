package commands;

import exceptions.HandsomeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;
import java.util.Stack;

/**
 * Represents a command to undo the last command in Handsome.
 * The command allows users to revert the most recent action taken.
 */
public class UndoCommand extends Command{

    /**
     * Executes the command by undoing the last command in the stack, if available.
     * If there is no command to undo, a message indicating that is returned.
     *
     * @param tasks The task list involved in the undo operation.
     * @param ui The UI object used for displaying messages to the user.
     * @param storage The storage object used for saving any changes after undoing.
     * @param commandRecords A stack of command records, from which the last command is undone.
     * @return A string message indicating the result of the undo operation.
     * @throws HandsomeException If an error specific to Handsome occurs during the undo operation.
     * @throws IOException If an I/O error occurs during the undo operation.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage,
                          Stack<Command> commandRecords) throws HandsomeException, IOException {
        if (!commandRecords.isEmpty()) {
            Command lastCommand = commandRecords.pop();
            lastCommand.undo(tasks, ui, storage);
            return "Well, I have undone your previous action, make up your mind ok?";
        } else {
            return "Unfortunately, I cannot undo what is non-existent.";
        }
    }

    /**
     * The undo operation cannot undo itself.
     *
     * @param tasks The task list, not used in this command.
     * @param ui The UI object used for displaying messages.
     * @param storage The storage object, not used in this command.
     * @return A message indicating that the undo operation cannot be undone.
     */
    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) {
        return "Undo operation cannot be undone.";
    }
}
