package task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task that has a description and a specified due date and time.
 * The due date and time must be provided in the "yyyy-MM-dd HHmm" format .
 */
public class Deadline extends Task {
    public LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and due date and time.
     *
     * @param des The description of the deadline task.
     * @param by The due date and time in the "yyyy-MM-dd HHmm" format.
     */
    public Deadline(String des, String by) {
        super(des);
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeException e) {
            System.out.println("Hey man, you have to use the yyyy-mm-dd HHmm format instead");
            this.by = null;
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter desiredFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + super.toString() + " (by: " + (by == null ? "I did not get the date, use yyyy-mm-dd HHmm."
                                                                 : by.format(desiredFormat)) + ")";
    }
}