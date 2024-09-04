package task;

public class Deadline extends Task {
    public String by;

    public Deadline(String des, String by) {
        super(des);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}