import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class DiceRoller {

    private static List<List<Integer>> rollHistory = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\nDice Rolling Simulator");
            System.out.println("1. Roll Dice");
            System.out.println("2. View Roll History");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine().trim();
            switch (input) {
                case "1":
                    rollDice();
                    break;
                case "2":
                    viewHistory();
                    break;
                case "3":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        System.out.println("\nThank you for using the Dice Rolling Simulator. Goodbye!");
        scanner.close();
    }

    private static void rollDice() {
        int numberOfDice = 0;
        while (numberOfDice < 1) {
            System.out.print("\nEnter the number of dice to roll (must be at least 1): ");
            try {
                numberOfDice = Integer.parseInt(scanner.nextLine());
                if (numberOfDice < 1) {
                    System.out.println("Please enter a number greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }

        List<Integer> currentRoll = new ArrayList<>();
        Random random = new Random();
        int total = 0;

        for (int i = 0; i < numberOfDice; i++) {
            int roll = random.nextInt(6) + 1; // Generates a number between 1 and 6
            currentRoll.add(roll);
            total += roll;
        }

        rollHistory.add(currentRoll);
        System.out.println("\nRolling " + numberOfDice + " dice...");
        System.out.println("Dice Values: " + currentRoll);
        System.out.println("Total: " + total);
    }

    private static void viewHistory() {
        if (rollHistory.isEmpty()) {
            System.out.println("\nNo rolls have been recorded yet.");
            return;
        }

        System.out.println("\nRoll History:");
        for (int i = 0; i < rollHistory.size(); i++) {
            List<Integer> roll = rollHistory.get(i);
            int sum = roll.stream().mapToInt(Integer::intValue).sum();
            System.out.printf("Roll #%d: %s = %d%n", (i + 1), roll, sum);
        }
    }
}