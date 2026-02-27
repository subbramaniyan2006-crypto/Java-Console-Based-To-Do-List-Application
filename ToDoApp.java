import java.util.*;
import java.text.*;

public class ToDoApp {

    // Method to handle user choices
    public static String userChoice(int choice, ArrayList<String> taskNames, 
                                     ArrayList<Date> taskDeadlines, Scanner scanner) {

        if (choice == 1) {
            System.out.print("Enter task name: ");
            String taskName = scanner.nextLine();

            System.out.print("Enter deadline (DD-MM-YYYY): ");
            String deadline = scanner.nextLine();

            addTask(taskNames, taskDeadlines, taskName, deadline);

        } else if (choice == 2) {
            System.out.print("Enter task number to delete: ");
            int taskNumber = Integer.parseInt(scanner.nextLine());

            deleteTask(taskNames, taskDeadlines, taskNumber);

        } else if (choice == 3) {
            displayTasks(taskNames, taskDeadlines);

        } else if (choice == 4) {
            return "Exiting application. Goodbye!";
        } else {
            System.out.println("Invalid choice! Please choose a valid option.\n");
        }

        return null;
    }

    // Method to delete a task by its task number
    public static void deleteTask(ArrayList<String> taskNames, ArrayList<Date> taskDeadlines, int taskNumber) {
        if (taskNumber > 0 && taskNumber <= taskNames.size()) {
            taskNames.remove(taskNumber - 1);
            taskDeadlines.remove(taskNumber - 1);
            System.out.println("Task deleted successfully!\n");
        } else {
            System.out.println("Invalid task number! No task found.\n");
        }
    }

    // Method to validate date format (DD-MM-YYYY)
    public static Date validateDate(String deadline) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            sdf.setLenient(false);
            return sdf.parse(deadline);
        } catch (ParseException e) {
            System.out.println("Invalid date format! Please use DD-MM-YYYY.\n");
            return null;
        }
    }

    // Method to add a task with name and deadline
    public static void addTask(ArrayList<String> taskNames, ArrayList<Date> taskDeadlines, 
                               String taskName, String deadline) {

        Date validDate = validateDate(deadline);

        if (validDate != null) {
            taskNames.add(taskName);
            taskDeadlines.add(validDate);
            System.out.println("Task added successfully!\n");
        }
    }

    // Method to display all tasks with deadlines
    public static void displayTasks(ArrayList<String> taskNames, ArrayList<Date> taskDeadlines) {
        if (taskNames.isEmpty()) {
            System.out.println("No tasks available.\n");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("\nYour Tasks:");

        for (int i = 0; i < taskNames.size(); i++) {
            System.out.println((i + 1) + ". " + taskNames.get(i) + " - " + sdf.format(taskDeadlines.get(i)));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // ArrayLists to store tasks and deadlines
        ArrayList<String> taskNames = new ArrayList<>();
        ArrayList<Date> taskDeadlines = new ArrayList<>();
        
        // Scanner for user input
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the To-Do List Application!");

        // Menu loop to handle user choices
        while (true) {
            System.out.println("Choose one operation:");
            System.out.println("1. Add Task");
            System.out.println("2. Delete Task");
            System.out.println("3. Display Tasks");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            // Handle user choice
            String result = userChoice(choice, taskNames, taskDeadlines, scanner);

            // Exit condition
            if ("Exiting application. Goodbye!".equals(result)) {
                System.out.println(result);
                break;
            }
        }

        scanner.close();
    }
}