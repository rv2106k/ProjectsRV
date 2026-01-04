import java.util.Scanner;

public class ATMSimulation {

    // Variables to store user data
    private static double balance = 1000.0;  // Default balance
    private static String pin = "1234";       // Default PIN
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the ATM!");
        
        // User login process
        if (login()) {
            int option;
            do {
                // Display ATM menu
                displayMenu();
                option = scanner.nextInt();

                // Execute corresponding operation based on user's choice
                switch (option) {
                    case 1:
                        checkBalance();
                        break;
                    case 2:
                        withdrawMoney();
                        break;
                    case 3:
                        depositMoney();
                        break;
                    case 4:
                        changePin();
                        break;
                    case 5:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } while (option != 5);  // Repeat the menu until the user chooses to exit
        }
    }

    // Login method for user to input PIN
    public static boolean login() {
        int attempts = 3;
        while (attempts > 0) {
            System.out.print("Enter your PIN: ");
            String enteredPin = scanner.next();
            if (enteredPin.equals(pin)) {
                return true;
            } else {
                attempts--;
                System.out.println("Incorrect PIN. You have " + attempts + " attempts left.");
            }
        }
        System.out.println("Too many incorrect attempts. Your account is locked.");
        return false;
    }

    // Method to display the ATM menu
    public static void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw Money");
        System.out.println("3. Deposit Money");
        System.out.println("4. Change PIN");
        System.out.println("5. Exit");
        System.out.print("Please choose an option (1-5): ");
    }

    // Method to check account balance
    public static void checkBalance() {
        System.out.println("Your current balance is: $" + balance);
    }

    // Method to withdraw money
    public static void withdrawMoney() {
        System.out.print("Enter the amount you want to withdraw: $");
        double amount = scanner.nextDouble();
        if (amount > balance) {
            System.out.println("Insufficient balance. Please try again.");
        } else if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive number.");
        } else {
            balance -= amount;
            System.out.println("You have successfully withdrawn: $" + amount);
            System.out.println("Your new balance is: $" + balance);
        }
    }

    // Method to deposit money
    public static void depositMoney() {
        System.out.print("Enter the amount you want to deposit: $");
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive number.");
        } else {
            balance += amount;
            System.out.println("You have successfully deposited: $" + amount);
            System.out.println("Your new balance is: $" + balance);
        }
    }

    // Method to change the PIN
    public static void changePin() {
        System.out.print("Enter your current PIN: ");
        String currentPin = scanner.next();
        if (currentPin.equals(pin)) {
            System.out.print("Enter your new PIN: ");
            String newPin = scanner.next();
            pin = newPin;
            System.out.println("Your PIN has been successfully changed.");
        } else {
            System.out.println("Incorrect current PIN. PIN change failed.");
        }
    }
}

