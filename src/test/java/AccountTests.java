import domain.Account;
import domain.StatementType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AccountTests {

    private final Account account = new Account();
    private final  Account receiverAccount = new Account();

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
        account.makeDeposit(1000.00);
        assertEquals(oldAmount + 1000.00, account.getBalance());

        assertEquals(StatementType.Deposit, account.getStatements().lastElement().getType());
        assertEquals(oldAmount, account.getStatements().lastElement().getBalance());
        assertEquals(1000.00, account.getStatements().lastElement().getAmount());
        assertNotNull(account.getStatements().lastElement().getDate());
    }


    @Test
    void should_create_1000_withdrawal_with_associated_operation() {

        double oldAmount = account.getBalance();
        account.makeWithdraw(1000.00);
        assertEquals(oldAmount - 1000.00, account.getBalance());

        assertEquals(StatementType.Withdrawal, account.getStatements().lastElement().getType());
        assertEquals(oldAmount, account.getStatements().lastElement().getBalance());
        assertEquals(1000.00, account.getStatements().lastElement().getAmount());
        assertNotNull(account.getStatements().lastElement().getDate());

    }

    @Test
    void should_create_500_transfer_with_associated_operations() {
        double oldAmount = account.getBalance();
        double oldReceiverAmount = receiverAccount.getBalance();

        account.makeTransfer(500.00, receiverAccount);
        assertEquals(oldAmount - 500.00, account.getBalance());

        assertEquals(oldReceiverAmount + 500.00, receiverAccount.getBalance());
        assertEquals(StatementType.TransferDebit, account.getStatements().lastElement().getType());
        assertEquals(oldAmount, account.getStatements().lastElement().getBalance());
        assertEquals(500.00, account.getStatements().lastElement().getAmount());
        assertNotNull(account.getStatements().lastElement().getDate());

        assertEquals(StatementType.TransferCredit, receiverAccount.getStatements().lastElement().getType());
        assertEquals(oldReceiverAmount, receiverAccount.getStatements().lastElement().getBalance());
        assertEquals(500.00, receiverAccount.getStatements().lastElement().getAmount());
        assertNotNull(receiverAccount.getStatements().lastElement().getDate());
    }
}
