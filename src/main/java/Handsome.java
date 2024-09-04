import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import task.Task;
import task.ToDo;
import task.Deadline;
import task.Event;

/**
 * Chatbot that is able to differentiate tasks
 */
public class Handsome {
    private static ArrayList<Task> data = new ArrayList<>();
    private static final Storage storage = new Storage("./data/handsome.txt");
    private static void addTask(Task task) {
        data.add(task);
        saveToFile();
        System.out.println("Looking productive! I have added: \n" + task.toString());
        System.out.println("Seems like the task count is " + data.size() + ", don't overwork yourself wookie?");
    }
    private static void printList() {
        System.out.println("Hey, here are the tasks you have!");
        for (int i = 0; i < data.size(); i++) {
            System.out.println((i + 1) + "." + data.get(i).toString());
        }
    }
    private static void markDone(int taskNum) {
        data.get(taskNum).markAsDone();
        saveToFile();
        System.out.println("Congrats, you finally finished this task!");
        System.out.println(data.get(taskNum).toString());
    }

    private static void markUndone(int taskNum) {
        data.get(taskNum).markAsUndone();
        saveToFile();
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
        // temporarily store task to let user know what is deleted
        Task removedTask = data.remove(taskNum);
        saveToFile();
        System.out.println("Well... I have removed the task: \n" + removedTask);
        System.out.println("Now you have " + data.size() + " count of tasks left to do");
    }

    public static class HandsomeException extends Exception {
        public HandsomeException(String error) {
            super(error);
        }
    }

    public static void main(String[] args) throws HandsomeException {
        Scanner scanner = new Scanner(System.in);
        try {
            data = (ArrayList<Task>) storage.load();
            System.out.println("Of course I managed to load the tasks!");
        } catch (IOException e) {
            System.out.println("Well... I failed to load tasks: " + e.getMessage());
            data = new ArrayList<>();
        }

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
                String[] taskAndTime = specifiedInput[1].split(" /by ", 2);
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
                System.out.println("Hey handsome, unfortunately I don't know what you are saying :( ");
                break;
            }
        }
        scanner.close();
    }

    private static void saveToFile() {
        try {
            storage.save(data);
            System.out.println("Hey man, I got you; the tasks are saved handsomely!");
        } catch (IOException e) {
            System.out.println("Well... I failed to save tasks: " + e.getMessage());
        }
    }
}
