import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ContactList {

    public static void main(String[] args) {
        // A Map where Key=Name (String) and Value=Phone Number (String)
        Map<String, String> contactList = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Contact List Menu ---");
            System.out.println("1. Add Contact (Create)");
            System.out.println("2. View Contact (Read)");
            System.out.println("3. Update Contact (Update)");
            System.out.println("4. Delete Contact (Delete)");
            System.out.println("5. View All Contacts");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            String name, phone;

            switch (choice) {
                case 1:
                    // --- CREATE ---
                    System.out.print("Enter name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    phone = scanner.nextLine();
                    if (contactList.containsKey(name)) {
                        System.out.println("Contact already exists. Use 'Update' to change.");
                    } else {
                        contactList.put(name, phone);
                        System.out.println("Contact added successfully.");
                    }
                    break;

                case 2:
                    // --- READ ---
                    System.out.print("Enter name to view: ");
                    name = scanner.nextLine();
                    if (contactList.containsKey(name)) {
                        phone = contactList.get(name);
                        System.out.println("Name: " + name + ", Phone: " + phone);
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;

                case 3:
                    // --- UPDATE ---
                    System.out.print("Enter name to update: ");
                    name = scanner.nextLine();
                    if (contactList.containsKey(name)) {
                        System.out.print("Enter new phone number: ");
                        phone = scanner.nextLine();
                        contactList.put(name, phone); // put() overwrites the value for an existing key
                        System.out.println("Contact updated successfully.");
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;

                case 4:
                    // --- DELETE ---
                    System.out.print("Enter name to delete: ");
                    name = scanner.nextLine();
                    if (contactList.containsKey(name)) {
                        contactList.remove(name);
                        System.out.println("Contact deleted successfully.");
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;

                case 5:
                    // --- VIEW ALL ---
                    if (contactList.isEmpty()) {
                        System.out.println("Contact list is empty.");
                    } else {
                        System.out.println("--- All Contacts ---");
                        // Iterate over the map's entrySet
                        for (Map.Entry<String, String> entry : contactList.entrySet()) {
                            System.out.println("Name: " + entry.getKey() + ", Phone: " + entry.getValue());
                        }
                    }
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please choose between 1 and 6.");
            }
        }
    }
