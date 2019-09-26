package domain;

import java.util.Vector;

public class Account {

    private double balance;
    private Vector<Statement> statements;

    public Account() {
        this.balance = 0.0;
        this.statements = new Vector<Statement>();
    }

    public double getBalance() {
        return this.balance;
    }

    public Vector<Statement> getStatements() {
        return statements;
    }

    public void makeDeposit(double amount) {

        this.statements.add(    new StatementCredit(
                                    StatementType.Deposit,
                                    this.balance,
                                    amount)
                           );
        this.balance += amount;
    }

    public void makeWithdraw(double amount) {
        this.statements.add(    new StatementDebit(
                                    StatementType.Withdrawal,
                                    this.balance,
                                    amount)
        );
        this.balance -= amount;
    }

    public void makeTransfer(double amount, Account creditedAccount) {
        this.statements.add(    new StatementDebit(
                                    StatementType.TransferDebit,
                                    this.balance,
                                    amount)
        );
        creditedAccount.statements.add(    new StatementCredit(
                                            StatementType.TransferCredit,
                                            creditedAccount.getBalance(),
                                            amount)
        );
        this.balance -= amount;
        creditedAccount.balance += amount;
    }

}
