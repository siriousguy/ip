import commands.Command;
import exceptions.HandsomeException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * Represents a chatbot that is able to handle user inputs.
 */
public class Handsome {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Handsome instance.
     *
     * @param filePath the relative path to where the files are saved. If not present, a new file will be made.
     */
    public Handsome(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.welcomeUser();
            tasks = new TaskList();
        }
    }

    /**
     * Initiates the interaction loop.
     */
    public void run() {
        ui.welcomeUser();
        boolean isExit = false;
        while(!isExit) {
            String fullCommand = ui.readCommand();
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IOException | HandsomeException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Marks the main entry point of the application
     *
     * @param args command line arguments that are not used
     */
    public static void main(String[] args) {
        new Handsome("./data/handsome.txt").run();
    }

    /**
     * Returns Handsome response to the user's input.
     *
     * @param input The user's input.
     * @return Handsome's response.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (HandsomeException e) {
            return e.getMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
