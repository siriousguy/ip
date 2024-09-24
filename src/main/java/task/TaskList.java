package task;

import exceptions.HandsomeException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks that the user can manipulate.
 * This class provides functionality to add, remove, mark, and find tasks in the list.
 */
public class TaskList {
    private final List<Task> tasks;
    private static Task lastDeletedTask;

    /**
     * Constructs a new empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added to the list.
     * @return A string message indicating the task was successfully added.
     */
    public String addTask(Task task) {
        assert task != null : "Well... Does it really make sense to add nothing?";
        tasks.add(task);
        return "Looking productive! I have added: \n" + task +
                ("\nSeems like the task count is " + tasks.size() + ", don't overwork yourself wookie?");
    }

    /**
     * Returns a string representation of the tasks in the task list.
     *
     * @return A string showing all the tasks in the list.
     */
    public String printList() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hey, here are the tasks you have!\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(".").append(tasks.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param taskNum The index of the task to be marked as done.
     * @return A string message indicating the task has been marked as done.
     */
    public String markDone(int taskNum) {
        tasks.get(taskNum).markAsDone();
        return "Congrats, you finally finished this task!\n" + (tasks.get(taskNum).toString());
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param taskNum The index of the task to be marked as not done.
     * @return A string message indicating the task has been marked as undone.
     */
    public String markUndone(int taskNum) {
        tasks.get(taskNum).markAsUndone();
        return "Hey, the task is not done but time to stop procrastinating?" + (tasks.get(taskNum).toString());
    }

    /**
     * Deletes the task at the specified index after conducting some checking.
     * The deleted task is stored temporarily in case the user wants to undo the deletion.
     *
     * @param taskNum The index of the task to be deleted.
     * @return A string message indicating the task was successfully deleted.
     * @throws HandsomeException If the specified task number does not exist.
     */
    public String deleteCheck(int taskNum) throws HandsomeException {
        if (taskNum > tasks.size()) {
            throw new HandsomeException("Hey, that task simply... does not exist.");
        }
        // temporarily store task to let user know what is deleted
        Task removedTask = tasks.remove(taskNum);
        lastDeletedTask = removedTask;
        return ("Well... I have removed the task: \n" + removedTask)
                + ("\nNow you have " + tasks.size() + " count of tasks left to do");
    }

    /**
     * Adds the last deleted task back to the task list at the specified index.
     *
     * @param taskNum The index where the task will be added.
     * @return A string message indicating the task has been successfully re-added.
     */
    public String addTaskAtIndex(int taskNum) {
        tasks.add(taskNum, lastDeletedTask);
        return "Hey, I got you! " + lastDeletedTask.toString() + " has been added!";
    }

    /**
     * Finds the corresponding tasks based on the keyword the user inputs.
     *
     * @param keyword word that the user wants to find.
     * @return a list of those tasks with the keyword.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }

    /**
     * Returns the current list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    public int getTasksCount() {
        return this.tasks.size();
    }
}
