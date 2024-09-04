package commands;

import exceptions.HandsomeException;
import task.TaskList;
import ui.Ui;
import storage.Storage;

import java.io.IOException;

/**
 * Represents an abstract command that can be executed by Handsome.
 */
public abstract class Command {
    /**
     * Executes the user's commands with the according tasklist, ui and storage.
     *
     * @param tasks contains the tasklist that can be used by the user commands.
     * @param ui used for displaying messages.
     * @param storage storage object to load and save files.
     * @throws IOException in the event the operation fails.
     * @throws HandsomeException for errors specific to Handsome.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, HandsomeException;

    /**
     * Indicates whether this command will cause the application to exit.
     *
     * @return true to exit the application, false if otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
