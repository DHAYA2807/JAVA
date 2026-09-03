
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class UndoRedoSimulator {

    public static void main(String[] args) {
        // Deque is the modern interface for Stacks
        Deque<String> undoStack = new ArrayDeque<>();
        Deque<String> redoStack = new ArrayDeque<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Action");
            System.out.println("2. Undo");
            System.out.println("3. Redo");
            System.out.println("4. Display last action");
            System.out.println("5. Exit");
            System.out.print("Choose any one: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    // --- ACTION ---
                    System.out.print("Give action as input: ");
                    String action = scanner.nextLine();
                    undoStack.push(action);
                    // A new action clears the redo stack
                    redoStack.clear();
                    System.out.println("Action '" + action + "' performed.");
                    break;

                case 2:
                    // --- UNDO ---
                    if (undoStack.isEmpty()) {
                        System.out.println("No action to undo.");
                    } else {
                        String undoneAction = undoStack.pop();
                        redoStack.push(undoneAction);
                        System.out.println("Undid action: '" + undoneAction + "'");
                    }
                    break;

                case 3:
                    // --- REDO ---
                    if (redoStack.isEmpty()) {
                        System.out.println("No action to redo.");
                    } else {
                        String redoneAction = redoStack.pop();
                        undoStack.push(redoneAction);
                        System.out.println("Redid action: '" + redoneAction + "'");
                    }
                    break;

                case 4:
                    // --- DISPLAY LAST ACTION ---
                    if (undoStack.isEmpty()) {
                        System.out.println("No last action.");
                    } else {
                        // peek() looks at the top item without removing it
                        System.out.println("Last action is " + undoStack.peek());
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please choose between 1 and 5.");
            }
        }
    }
