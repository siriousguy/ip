package task;

public class Event extends Task {
    public String from;
    public String to;

    public Event(String des, String from, String to) {
        super(des);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}