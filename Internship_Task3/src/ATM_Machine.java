import java.util.Scanner;

public class ATM_Machine {
    private BankAccount userAccount;

    public ATM_Machine(BankAccount Account)
    {
        this.userAccount = Account;
    }

    public void displayMenu()
    {
        System.out.println("\n=======================");
        System.out.println("1. Withdraw Amount ");
        System.out.println("2. Deposit Amount ");
        System.out.println("3. Check Balance ");
        System.out.println("4. Exit ");
        System.out.println("=======================");
    }

    public void DepositMoney()
    {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the amount to Deposit: ");
        double amount = scan.nextDouble();

        if (userAccount.deposit(amount))
        {
            System.out.println("Amount Deposited Successfully. Updated Balance :" + userAccount.checkBalance());
        }
        else
        {
            System.out.println("Invalid Amount for Deposit. ");
        }

    }

    public void WithdrawMoney()
    {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the amount to withdraw: ");
        double amount = scan.nextDouble();

        if (userAccount.withdraw(amount))
        {
            System.out.println("Amount Withdrawn Successfully. Updated Balance :" + userAccount.checkBalance());
        }
        else
        {
            System.out.println("Insufficient Balance. ");
        }

    }

    public void checkBalance()
    {
        System.out.printf("Current Balance: " + userAccount.checkBalance());
    }

    public void runAtm()
    {
        Scanner scan = new Scanner(System.in);

        while (true)
        {
            displayMenu();
            System.out.println("Enter Your Choice [1-4]: ");
            int choice = scan.nextInt();

            switch (choice)
            {
                case 1:
                    WithdrawMoney();
                    break;

                case 2:
                    DepositMoney();
                    break;

                case 3:
                    checkBalance();
                    break;

                case 4:
                    System.out.println("ThankYou For Using ATM. STAY HAPPY");
                    scan.close();
                    return;

                default:
                    System.out.println("Invalid Choice. Please Enter option between 1 and 4.");
            }
        }
    }
}
