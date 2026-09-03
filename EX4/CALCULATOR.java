package EX5;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// 1. Define a functional interface for the arithmetic operations.
// A functional interface has exactly one abstract method.
@FunctionalInterface
interface ArithmeticOperation
{
    double calculate(double a, double b);
}

public class Calculator
{

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        // 2. Create a Map to store operator symbols and their corresponding lambda expressions.
        Map<String, ArithmeticOperation> operations = new HashMap<>();

        // 3. Define the basic arithmetic operations using lambda expressions.
        // These lambdas implement the 'calculate' method of the ArithmeticOperation interface.
        operations.put("+", (a, b) -> a + b);
        operations.put("-", (a, b) -> a - b);
        operations.put("*", (a, b) -> a * b);
        operations.put("/", (a, b) -> {
            if (b == 0)
            {
                // Handle division by zero
                throw new ArithmeticException("Error: Cannot divide by zero.");
            }
            return a / b;
        });

        System.out.println("🧮 Simple Arithmetic Calculator 🧮");
        System.out.println("Enter calculations in the format: number operator number (e.g., 10 * 5)");
        System.out.println("Type 'exit' to quit.");

        while (true)
        {
            try
            {
                System.out.print("> ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("exit"))
                {
                    break;
                }

                // Split the input string into parts: number1, operator, number2
                String[] parts = input.split(" ");
                if (parts.length != 3)
                {
                    System.out.println("Invalid format. Please use: number operator number");
                    continue;
                }

                double num1 = Double.parseDouble(parts[0]);
                String operator = parts[1];
                double num2 = Double.parseDouble(parts[2]);

                // 4. Look up the operation in the map and execute it.
                if (operations.containsKey(operator))
                {
                    // Retrieve the correct lambda expression from the map
                    ArithmeticOperation operation = operations.get(operator);
                    // Execute the lambda's 'calculate' method
                    double result = operation.calculate(num1, num2);
                    System.out.println("Result: " + result);
                }
                else
                {
                    System.out.println("Error: Unknown operator '" + operator + "'. Use +, -, *, /");
                }

            }
            catch (NumberFormatException e)
            {
                System.out.println("Error: Invalid number format.");
            }
            catch (ArithmeticException e)
            {
                System.out.println(e.getMessage());
            }
            catch (Exception e)
            {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }

        scanner.close();
        System.out.println("Calculator closed.");
    }
}
