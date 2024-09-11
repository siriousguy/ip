package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

public class UnmarkCommand extends Command {
    private final int taskNum;

    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String undone = tasks.markUndone(taskNum);
        storage.save(tasks.getTasks());
        return undone;
    }
}
