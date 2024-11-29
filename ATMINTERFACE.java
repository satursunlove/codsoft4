import java.util.InputMismatchException;
import java.util.Scanner;

// BankAccount Class
class BankAccount {
    private String accountHolder;
    private double balance;

    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    // Deposit method
    public String deposit(double amount) {
        if (amount <= 0) {
            return "Invalid amount. Please enter a positive number.";
        }
        balance += amount;
        return "Successfully deposited $" + String.format("%.2f", amount) + 
               ". New balance: $" + String.format("%.2f", balance) + ".";
    }

    // Withdraw method
    public String withdraw(double amount) {
        if (amount <= 0) {
            return "Invalid amount. Please enter a positive number.";
        }
        if (amount > balance) {
            return "Insufficient balance. Transaction failed.";
        }
        balance -= amount;
        return "Successfully withdrew $" + String.format("%.2f", amount) + 
               ". Remaining balance: $" + String.format("%.2f", balance) + ".";
    }

    // Check balance method
    public String checkBalance() {
        return "Current balance: $" + String.format("%.2f", balance);
    }
}

// ATM Class
class ATM {
    private BankAccount bankAccount;
    private Scanner scanner;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        this.scanner = new Scanner(System.in);
    }

    // Display the menu
    public void displayMenu() {
        System.out.println("\nWelcome to the ATM!");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    // Process user's choice
    public void processOption(int option) {
        switch (option) {
            case 1:
                System.out.print("Enter the amount to withdraw: ");
                try {
                    double withdrawAmount = scanner.nextDouble();
                    System.out.println(bankAccount.withdraw(withdrawAmount));
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid amount.");
                    scanner.nextLine(); // Clear invalid input
                }
                break;

            case 2:
                System.out.print("Enter the amount to deposit: ");
                try {
                    double depositAmount = scanner.nextDouble();
                    System.out.println(bankAccount.deposit(depositAmount));
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid amount.");
                    scanner.nextLine(); // Clear invalid input
                }
                break;

            case 3:
                System.out.println(bankAccount.checkBalance());
                break;

            case 4:
                System.out.println("Thank you for using the ATM. Goodbye!");
                break;

            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

    // Run the ATM interface
    public void run() {
        while (true) {
            displayMenu();
            System.out.print("Choose an option: ");
            try {
                int option = scanner.nextInt();
                if (option == 4) {
                    processOption(option);
                    break;
                }
                processOption(option);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
}

// Main Class
public class ATMINTERFACE {
    public static void main(String[] args) {
        // Create a bank account with an initial balance of $500
        BankAccount userAccount = new BankAccount("John Doe", 500);

        // Create an ATM connected to the user's bank account
        ATM atm = new ATM(userAccount);

        // Run the ATM interface
        atm.run();
    }
}
