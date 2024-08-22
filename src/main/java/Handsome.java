import java.util.*;

public class Handsome {
    private static final Task[] data = new Task[100];
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
        public String printTaskStatus() {
            return ("[" + this.getStatusIcon() + "] " + this.description);
        }
    }

    private static void addTask(String des) {
        data[dataCount] = new Task(des);
        dataCount++;
        System.out.println("added: " + des);
    }

    private static void printList() {
        System.out.println("Hey, here are the tasks you have!");
        for (int i = 0; i < dataCount; i++) {
            System.out.println((i + 1) + "." + data[i].printTaskStatus());
        }
    }
    private static void markDone(int taskNum) {
        data[taskNum].markAsDone();
        System.out.println("Congrats, you finally finished this task!");
        System.out.println(data[taskNum].printTaskStatus());
    }

    private static void markUndone(int taskNum) {
        data[taskNum].markAsUndone();
        System.out.println("Hey, the task is not done but time to stop procrastinating?");
        System.out.println(data[taskNum].printTaskStatus());
    }


    public static void main(String[] args) {
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

                default:
                    addTask(input);
                    break;

            }
        }
        scanner.close();
    }
}
