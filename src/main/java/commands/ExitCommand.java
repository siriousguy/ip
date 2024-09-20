package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.util.Stack;

/**
 * Represents a command to exit Handsome.
 * This command will cause the chatbot to end the current session.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command, which sends a farewell message and ends the session.
     *
     * @param tasks The task list, not used in this command.
     * @param ui The UI object used for displaying messages to the user.
     * @param storage The storage object, not used in this command.
     * @param commandRecord A stack of command records, not used in this command.
     * @return A farewell message indicating the session is ending.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, Stack<Command> commandRecord) {
        return "Hey it has been a nice conversation, good day ahead!";
    }

    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) {
        return "Guess who's back? It's me handsome hahahaha.";
    }

    /**
     * Indicates that this command will cause Handsome to exit.
     *
     * @return true, indicating that the application will exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
