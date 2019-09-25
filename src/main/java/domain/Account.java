package domain;

import java.time.LocalDate;
import java.util.Vector;

public class Account {

    private double balance;
    private Vector<Operation> operations;

    public Account() {
        this.balance = 0.0;
        this.operations = new Vector<Operation>();
    }

    public double getBalance() {
        return this.balance;
    }

    public Vector<Operation> getOperations() {
        return operations;
    }

    public void makeDeposit(double amount) {

        this.operations.add(    new Operation(
                                    OperationType.Deposit,
                                    this.balance,
                                    amount)
                           );
        this.balance += amount;
    }

    public void makeWithdraw(double amount) {
        this.operations.add(    new Operation(
                                    OperationType.Withdrawal,
                                    this.balance,
                                    amount)
        );
        this.balance -= amount;
    }

    public void makeTransfer(double amount, Account creditedAccount) {
        this.operations.add(    new Operation(
                                    OperationType.TransferDebit,
                                    this.balance,
                                    amount)
        );
        creditedAccount.operations.add(    new Operation(
                                            OperationType.TransferCredit,
                                            creditedAccount.getBalance(),
                                            amount)
        );
        this.balance -= amount;
        creditedAccount.balance += amount;
    }

}
