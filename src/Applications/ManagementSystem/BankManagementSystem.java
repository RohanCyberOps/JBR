import java.util.*;

class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;
    private List<String> transactionHistory;

    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        addTransaction("Account created with initial balance: $" + initialBalance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            addTransaction("Deposited: $" + amount);
            System.out.println("Deposit successful!");
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            addTransaction("Withdrew: $" + amount);
            System.out.println("Withdrawal successful!");
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds!");
        }
    }

    public void transfer(BankAccount recipient, double amount) {
        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
            recipient.balance += amount;
            this.addTransaction("Transferred: $" + amount + " to " + recipient.accountNumber);
            recipient.addTransaction("Received: $" + amount + " from " + this.accountNumber);
            System.out.println("Transfer successful!");
        } else {
            System.out.println("Invalid transfer amount or insufficient funds!");
        }
    }

    private void addTransaction(String transaction) {
        String timestamp = new Date().toString();
        transactionHistory.add(timestamp + " - " + transaction);
    }

    public void displayInfo() {
        System.out.println("\nAccount Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Balance: $" + balance);
    }

    public void displayTransactionHistory() {
        System.out.println("\n=== Transaction History for " + accountNumber + " ===");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    // Getters
    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolder() { return accountHolder; }
    public double getBalance() { return balance; }
}

public class BankManagementSystem {
    private static List<BankAccount> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Bank Management System ===");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. View Account Info");
            System.out.println("6. View Transaction History");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: createAccount(); break;
                case 2: deposit(); break;
                case 3: withdraw(); break;
                case 4: transfer(); break;
                case 5: viewAccountInfo(); break;
                case 6: viewTransactionHistory(); break;
                case 7: System.out.println("Thank you for banking with us!"); return;
                default: System.out.println("Invalid option!");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter account number: ");
        String accNumber = scanner.nextLine();
        System.out.print("Enter account holder name: ");
        String holderName = scanner.nextLine();
        System.out.print("Enter initial deposit: ");
        double initialDeposit = scanner.nextDouble();
        scanner.nextLine();

        accounts.add(new BankAccount(accNumber, holderName, initialDeposit));
        System.out.println("Account created successfully!");
    }

    private static void deposit() {
        BankAccount account = getAccount();
        if (account != null) {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            account.deposit(amount);
        }
    }

    private static void withdraw() {
        BankAccount account = getAccount();
        if (account != null) {
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            account.withdraw(amount);
        }
    }

    private static void transfer() {
        System.out.print("Enter your account number: ");
        String senderAcc = scanner.nextLine();
        BankAccount sender = findAccount(senderAcc);

        if (sender == null) {
            System.out.println("Sender account not found!");
            return;
        }

        System.out.print("Enter recipient account number: ");
        String recipientAcc = scanner.nextLine();
        BankAccount recipient = findAccount(recipientAcc);

        if (recipient == null) {
            System.out.println("Recipient account not found!");
            return;
        }

        System.out.print("Enter transfer amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        sender.transfer(recipient, amount);
    }

    private static void viewAccountInfo() {
        BankAccount account = getAccount();
        if (account != null) {
            account.displayInfo();
        }
    }

    private static void viewTransactionHistory() {
        BankAccount account = getAccount();
        if (account != null) {
            account.displayTransactionHistory();
        }
    }

    private static BankAccount getAccount() {
        System.out.print("Enter account number: ");
        String accNumber = scanner.nextLine();
        BankAccount account = findAccount(accNumber);
        if (account == null) {
            System.out.println("Account not found!");
        }
        return account;
    }

    private static BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
}