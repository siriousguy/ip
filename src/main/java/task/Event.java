package task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task that has a description, a specified start date/time, and an end date/time.
 * Both the start and end date/times must be provided in the "yyyy-MM-dd HHmm" format.
 */
public class Event extends Task {
    public LocalDateTime from;
    public LocalDateTime to;

    /**
     * Constructs an Event task with the specified description, start date/time, and end date/time.
     *
     * @param des The description of the event.
     * @param from The start date/time in the "yyyy-MM-dd HHmm" format.
     * @param to The end date/time in the "yyyy-MM-dd HHmm" format.
     */
    public Event(String des, String from, String to) {
        super(des);
        try {
            this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeException e) {
            System.out.println("Hey man, you have to use the yyyy-mm-dd HHmm format instead");
            this.from = null;
            this.to = null;
        }
    }

    /**
     * Returns a string representation of the Event task.
     * If the date is not provided or incorrectly formatted, an error message is shown.
     *
     * @return A string representing the Event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter desiredFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        if (from == null || to == null) {
            return "[E]" + super.toString() + " (I did not get the date, use yyyy-mm-dd HHmm.)";
        }
        return "[E]" + super.toString() + " (from: " + (from == null ? "I did not get the date, use yyyy-mm-dd HHmm."
                : from.format(desiredFormat)) + " to: " + (to == null ? "I did not get the date, use yyyy-mm-dd HHmm."
                : to.format(desiredFormat)) + ")";
    }
}