class BankAccount {
    double balance;

    void deposit(double amount) {
        balance = balance + amount;
    }

    void withdraw(double amount) {
        balance = balance - amount;
    }

    void printBalance() {
        System.out.println("Balance: " + balance);
    }
}

public class Main {
    public static void main(String[] args) {

        BankAccount account = new BankAccount();

        account.balance = 1000;

        account.printBalance();

        account.deposit(500);
        account.printBalance();

        account.withdraw(200);
        account.printBalance();
    }
}