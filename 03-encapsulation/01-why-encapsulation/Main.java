class BankAccount {
    public double balance;

}
public class Main {

    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        account.balance = 1000;

        System.out.println("Initial balance:" + account.balance);

        account.balance = -5000;

        System.out.println("Updated balance:" + account.balance);
    }
}