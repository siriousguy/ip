package storage;

import task.Event;
import task.ToDo;
import task.Task;
import task.Deadline;

import java.io.BufferedReader;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

/**
 * Handles the loading and saving of tasks to and from the storage file.
 * The storage is responsible for reading and writing tasks.
 */
public class Storage {
    private final String filePath;
    private static final DateTimeFormatter DESIRED_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Constructs a Storage object with the given file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file and returns them as a list of tasks.
     * If the file does not exist, it creates a new empty file.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If an I/O error occurs during the loading process.
     */
    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        assert file.exists() : "Well... This file surprisingly, does not exist";

        // create the txt file if it does not exist
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return tasks;
       }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String tempInfo;
        while ((tempInfo = reader.readLine()) != null) {
            String[] taskInfo = tempInfo.split(" \\| ");
            String taskType = taskInfo[0];
            boolean isDone = taskInfo[1].equals("1");
            String des = taskInfo[2];

            Task task;
            switch (taskType) {
            case "T":
                task = new ToDo(des);
                break;
            case "D":
                String by = taskInfo.length > 3 ? taskInfo[3] : null;

                try {
                    task = new Deadline(des, by);
                } catch (DateTimeException e) {
                    System.out.println("Error as tasks are added without valid date.");
                    task = new Deadline(des, null);
                }
                break;
            case "E":
                String from = taskInfo.length > 3 ? taskInfo[3] : null;
                String to = taskInfo.length > 4 ? taskInfo[4] : null;

                try {
                    task = new Event(des, from, to);
                } catch (DateTimeException e) {
                    System.out.println("Error as tasks are added without valid date.");
                    task = new Event(des, null, null);
                }
                break;
            default:
                System.out.println("Well, I have never seen this task type before.");
                continue;
            }

            if (isDone) {
                task.markAsDone();
            }
            tasks.add(task);
        }
        reader.close();
        return tasks;
    }

    /**
     * Saves the provided list of tasks to the storage file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If an I/O error occurs during the saving process.
     */
    public void save(List<Task> tasks) throws IOException {
        assert tasks != null : "Hey man, the task list should not be null.";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (Task task : tasks) {
            writer.write(taskToFile(task));
            writer.newLine();
        }
        writer.close();
    }

    /**
     * Writes the tasks onto the file.
     *
     * @param task The task which has been written.
     * @return The string representation of task onto the handsome.txt file.
     */
    private String taskToFile(Task task) {
        StringBuilder storedTask = new StringBuilder();

        assert task != null : "Well, seems like I'm writing a null file.";

        if (task instanceof ToDo) {
            storedTask.append("T");
            storedTask.append(" | ").append(task.isDone ? "1" : "0");
            storedTask.append(" | ").append(task.description);
        } else if (task instanceof Deadline) {
            storedTask.append("D");
            storedTask.append(" | ").append(task.isDone ? "1" : "0");
            storedTask.append(" | ").append(task.description);
            storedTask.append(" | ").append(((Deadline) task).by == null
                                                                ? "Invalid"
                                                                : DESIRED_FORMAT.format(((Deadline) task).by));
        } else if (task instanceof Event) {
            storedTask.append("E");
            storedTask.append(" | ").append(task.isDone ? "1" : "0");
            storedTask.append(" | ").append(task.description);
            storedTask.append(" | ").append(((Event) task).from == null
                                                                ? "Invalid"
                                                                : DESIRED_FORMAT.format(((Event) task).from));
            storedTask.append(" | ").append(((Event) task).to == null
                                                                ? "Invalid"
                                                                : DESIRED_FORMAT.format(((Event) task).to));
        }
        return storedTask.toString();
    }
}
