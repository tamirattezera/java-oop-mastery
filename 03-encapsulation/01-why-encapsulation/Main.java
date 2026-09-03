class BankAccount {

    public double balance;
}

public class Main {

    public static void main(String[] args) {

        BankAccount account = new BankAccount();

        account.balance = 1000;

        System.out.println("Initial balance: " + account.balance);

        withdraw(account);

        System.out.println("After withdrawal: " + account.balance);

        randomSystemUpdate(account);

        System.out.println("Final balance: " + account.balance);
    }

    public static void withdraw(BankAccount account) {
        account.balance = account.balance - 200;
    }

    public static void randomSystemUpdate(BankAccount account) {
        account.balance = -999999;
    }
}