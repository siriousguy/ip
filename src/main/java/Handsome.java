import java.util.*;

public class Handsome {
    private static final ArrayList<Task> data = new ArrayList<>();
    private static int dataCount = 0;

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " ");
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void markAsUndone() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            return ("[" + this.getStatusIcon() + "] " + this.description);
        }
    }

    public static class ToDo extends Task {
        public ToDo(String description) {
            super(description);
        }
        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static class Deadline extends Task {
        protected String by;

        public Deadline(String des, String by) {
            super(des.split(" ", 2)[1]);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    public static class Event extends Task {
        protected String from;
        protected String to;

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


    /* route the task adding through this error checker first
    private static void toDoCheck(String des) throws HandsomeException {
        if (des.isEmpty()) {
            throw new HandsomeException("Hey man, give me a description to work with?");
        }
    } */

    private static void addTask(Task task) {
        data.add(task);
        dataCount++;
        System.out.println("Looking productive! I have added: \n" + task.toString());
        System.out.println("Seems like the task count is " + dataCount + ", don't overwork yourself okie?");
    }

    private static void printList() {
        System.out.println("Hey, here are the tasks you have!");
        for (int i = 0; i < dataCount; i++) {
            System.out.println((i + 1) + "." + data.get(i).toString());
        }
    }
    private static void markDone(int taskNum) {
        data.get(taskNum).markAsDone();
        System.out.println("Congrats, you finally finished this task!");
        System.out.println(data.get(taskNum).toString());
    }

    private static void markUndone(int taskNum) {
        data.get(taskNum).markAsUndone();
        System.out.println("Hey, the task is not done but time to stop procrastinating?");
        System.out.println(data.get(taskNum).toString());
    }

    private static void deleteCheck(String[] des) throws HandsomeException {
        if (des.length < 2 ) {
            throw new HandsomeException("Hey, please let me know the task that you want to delete.");
        }
        int taskNum = Integer.parseInt(des[1]) - 1;
        if (taskNum > data.size()) {
            throw new HandsomeException("Hey, that task simply... does not exist.");
        }
        // temporarily store task so i can let user know what is deleted
        Task removedTask = data.remove(taskNum);
        System.out.println("Well... I have removed the task: \n" + removedTask);
        System.out.println("Now you have " + data.size() + " count of tasks left to do");
        dataCount--; // admittedly, I should change these to sizes... but I dont want bugs for now
    }

    public static class HandsomeException extends Exception {
        public HandsomeException(String error) {
            super(error);
        }
    }

    public static void main(String[] args) throws HandsomeException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Handsome\nWhat can I do for you?\n");

        while (true) {
            String input = scanner.nextLine();
            String[] specifiedInput = input.split(" ", 2); // for mark 2 etc.

            if (input.equals("bye")) {
                System.out.println("Bye, it was an amazing conversation!");
                break;
            }

            switch (specifiedInput[0]) {
                case "list":
                    printList();
                    break;

                case "mark":
                    markDone(Integer.parseInt(specifiedInput[1]) - 1);
                    break;

                case "unmark":
                    markUndone(Integer.parseInt(specifiedInput[1]) - 1);
                    break;

                case "todo":
                    if (specifiedInput.length < 2 || specifiedInput[1].trim().isEmpty()) {
                        throw new HandsomeException("Hey man... give me a descriptor to work with?");
                    }
                    addTask(new ToDo(specifiedInput[1]));
                    break;

                case "deadline":
                    if (specifiedInput.length < 2 || specifiedInput[1].trim().isEmpty()) {
                        throw new HandsomeException("Hey man... give me a descriptor to work with?");
                    }
                    String[] taskAndTime = input.split(" /by ", 2);
                    addTask(new Deadline(taskAndTime[0], taskAndTime[1]));
                    break;

                case "event":
                    if (specifiedInput.length < 2 || specifiedInput[1].trim().isEmpty()) {
                        throw new HandsomeException("Hey man... give me a descriptor to work with?");
                    }
                    String[] fromAndTo = specifiedInput[1].split(" /from | /to ");
                    addTask(new Event(fromAndTo[0], fromAndTo[1], fromAndTo[2]));
                    break;

                case "delete":
                    deleteCheck(specifiedInput);
                    break;

                default:
                    System.out.println("Hey handsome, unfortunately I dont know what you are saying :( ");
                    break;

            }
        }
        scanner.close();
    }
}
