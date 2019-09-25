

public class Account {

    private double amount;

    public Account() {
        this.amount = 0.0;
    }

    public double getAmount() {
        return this.amount;
    }

    public void makeDeposit(double amount) {
        this.amount += amount;
    }

    public void makeWithdraw(double amount) {
        this.amount -= amount;
    }

    public void makeTransfer(double amount, Account creditedAccount) {
        this.amount -= amount;
        creditedAccount.amount += amount;
    }

}
