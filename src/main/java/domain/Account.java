package domain;

import service.DateService;

import java.util.ArrayList;
import java.util.List;


public class Account {

    private double balance;
    private List<Statement> statements;

    public Account() {
        this.balance = 0.0;
        this.statements = new ArrayList<Statement>();
    }

    public double getBalance() {
        return this.balance;
    }

    public List<Statement> getStatements() { return statements; }

    public void makeDeposit(DateService dateService, double amount) {

        this.statements.add(    new StatementCredit(
                                    dateService,
                                    StatementType.Deposit,
                                    this.balance,
                                    amount)
                           );
        this.balance += amount;
    }

    public void makeWithdraw(DateService dateService, double amount) {
        this.statements.add(    new StatementDebit(
                                    dateService,
                                    StatementType.Withdrawal,
                                    this.balance,
                                    amount)
        );
        this.balance -= amount;
    }

    public void makeTransfer(DateService dateService, double amount, Account creditedAccount) {
        this.statements.add(    new StatementDebit(
                                    dateService,
                                    StatementType.TransferDebit,
                                    this.balance,
                                    amount)
        );
        creditedAccount.statements.add(    new StatementCredit(
                                            dateService,
                                            StatementType.TransferCredit,
                                            creditedAccount.getBalance(),
                                            amount)
        );
        this.balance -= amount;
        creditedAccount.balance += amount;
    }

}
