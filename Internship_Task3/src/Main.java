import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Your Account Balance: ");
        double initialBalance = scan.nextDouble();

        BankAccount userAccount1 = new BankAccount(initialBalance);
        ATM_Machine ATM = new ATM_Machine(userAccount1);

        ATM.runAtm();

    }
}
