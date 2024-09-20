package task;

/**
 * Represents a task which only has the description and no specifc date associated.
 */
public class ToDo extends Task {
    /**
     * Constructs a task with the specified description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the task, including its type and status.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}