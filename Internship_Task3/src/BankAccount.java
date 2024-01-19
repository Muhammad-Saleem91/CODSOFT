public class BankAccount
{
    private double Balance;

    public BankAccount(double initialBalance)
    {
        this.Balance = initialBalance;
    }

    public boolean deposit(double depositing_amount)
    {
        if (depositing_amount > 0)
        {
            this.Balance += depositing_amount;
            return true;
        }

        else
        {
            return false;
        }
    }

    public boolean withdraw(double withdraw_amount)
    {
        if (withdraw_amount > 0 && withdraw_amount <= this.Balance)
        {
            this.Balance -= withdraw_amount;
            return true;
        }

        else
        {
            return false;
        }
    }

    public double checkBalance()
    {
        return this.Balance;
    }
}
