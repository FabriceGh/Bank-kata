package domain;

import service.DateNowService;
import service.DateService;
import service.StatementPrinter;

public class Runner {

    public static void main(String[] args) {

        DateService dateService = new DateNowService();
        Account testAccount = new Account();
        Account testAccount2 = new Account();
        testAccount.makeDeposit(dateService, 1000000.00);
        testAccount.makeWithdraw(dateService, 200.00);
        testAccount.makeWithdraw(dateService, 20.0);
        testAccount.makeWithdraw(dateService, 200.00);
        testAccount.makeTransfer(dateService, 200.00, testAccount2);


        String table = StatementPrinter.buildDisplayableTab(testAccount.getStatements(), "|");
        System.out.print(table);

        String table2 = StatementPrinter.buildDisplayableTab(testAccount2.getStatements(), "|");
        System.out.print(table2);

    }
}
