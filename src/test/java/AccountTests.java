import domain.*;
import org.junit.jupiter.api.Test;
import service.DateNowService;
import service.DateService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class AccountTests {

    private final Account account = new Account();
    private final  Account receiverAccount = new Account();
    private final DateService dateService = new DateNowService();

    @Test
    void should_create_empty_account() {

        assertEquals(0.0, account.getBalance());

    }

    @Test
    void should_return_empty_operation_list() {

        assertEquals(0, account.getStatements().size());

    }

    @Test
    void should_create_1000_deposit_with_associated_operation() {

        double oldAmount = account.getBalance();
        account.makeDeposit(dateService, 1000.00);
        assertEquals(oldAmount + 1000.00, account.getBalance());

        List<Statement> statementsOfTestAccount = account.getStatements();
        assertEquals(StatementType.Deposit, statementsOfTestAccount.get(statementsOfTestAccount.size() - 1).getType());
        assertEquals(oldAmount, statementsOfTestAccount.get(statementsOfTestAccount.size() - 1).getBalance());
        assertEquals(1000.00, statementsOfTestAccount.get(statementsOfTestAccount.size() - 1).getAmount());
        assertNotNull(statementsOfTestAccount.get(statementsOfTestAccount.size() - 1).getDate());
    }


    @Test
    void should_create_1000_withdrawal_with_associated_operation() {

        double oldAmount = account.getBalance();
        account.makeWithdraw(dateService, 1000.00);
        assertEquals(oldAmount - 1000.00, account.getBalance());

        List<Statement> statementsOfTestAccount = account.getStatements();
        assertEquals(StatementType.Withdrawal, statementsOfTestAccount.get(statementsOfTestAccount.size() - 1).getType());
        assertEquals(oldAmount, statementsOfTestAccount.get(statementsOfTestAccount.size() - 1).getBalance());
        assertEquals(1000.00, statementsOfTestAccount.get(statementsOfTestAccount.size() - 1).getAmount());
        assertNotNull(statementsOfTestAccount.get(statementsOfTestAccount.size() - 1).getDate());

    }

    @Test
    void should_create_500_transfer_with_associated_operations() {
        double oldAmount = account.getBalance();
        double oldReceiverAmount = receiverAccount.getBalance();

        account.makeTransfer(dateService, 500.00, receiverAccount);
        assertEquals(oldAmount - 500.00, account.getBalance());

        List<Statement> statementsOfTestAccount = account.getStatements();
        List<Statement> statementsOfReceiverTestAccount = receiverAccount.getStatements();
        assertEquals(oldReceiverAmount + 500.00, receiverAccount.getBalance());
        assertEquals(StatementType.TransferDebit, statementsOfTestAccount.get(statementsOfTestAccount.size() - 1).getType());
        assertEquals(oldAmount, statementsOfTestAccount.get(statementsOfTestAccount.size() - 1).getBalance());
        assertEquals(500.00, statementsOfTestAccount.get(statementsOfTestAccount.size() - 1).getAmount());
        assertNotNull(statementsOfTestAccount.get(statementsOfTestAccount.size() - 1).getDate());

        assertEquals(StatementType.TransferCredit, statementsOfReceiverTestAccount.get(statementsOfReceiverTestAccount.size() - 1).getType());
        assertEquals(oldReceiverAmount, statementsOfReceiverTestAccount.get(statementsOfReceiverTestAccount.size() - 1).getBalance());
        assertEquals(500.00, statementsOfReceiverTestAccount.get(statementsOfReceiverTestAccount.size() - 1).getAmount());
        assertNotNull(statementsOfReceiverTestAccount.get(statementsOfReceiverTestAccount.size() - 1).getDate());
    }



}
