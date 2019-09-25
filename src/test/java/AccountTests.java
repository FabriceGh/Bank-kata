import domain.Account;
import domain.OperationType;
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

        assertEquals(0, account.getOperations().size());

    }

    @Test
    void should_create_1000_deposit_with_associated_operation() {

        double oldAmount = account.getBalance();
        account.makeDeposit(1000.00);
        assertEquals(oldAmount + 1000.00, account.getBalance());

        assertEquals(OperationType.Deposit, account.getOperations().lastElement().getType());
        assertEquals(oldAmount, account.getOperations().lastElement().getBalance());
        assertEquals(1000.00, account.getOperations().lastElement().getAmount());
        assertNotNull(account.getOperations().lastElement().getDate());
    }


    @Test
    void should_create_1000_withdrawal_with_associated_operation() {

        double oldAmount = account.getBalance();
        account.makeWithdraw(1000.00);
        assertEquals(oldAmount - 1000.00, account.getBalance());

        assertEquals(OperationType.Withdrawal, account.getOperations().lastElement().getType());
        assertEquals(oldAmount, account.getOperations().lastElement().getBalance());
        assertEquals(1000.00, account.getOperations().lastElement().getAmount());
        assertNotNull(account.getOperations().lastElement().getDate());

    }

    @Test
    void should_create_500_transfer_with_associated_operations() {
        double oldAmount = account.getBalance();
        double oldReceiverAmount = receiverAccount.getBalance();

        account.makeTransfer(500.00, receiverAccount);
        assertEquals(oldAmount - 500.00, account.getBalance());

        assertEquals(oldReceiverAmount + 500.00, receiverAccount.getBalance());
        assertEquals(OperationType.TransferDebit, account.getOperations().lastElement().getType());
        assertEquals(oldAmount, account.getOperations().lastElement().getBalance());
        assertEquals(500.00, account.getOperations().lastElement().getAmount());
        assertNotNull(account.getOperations().lastElement().getDate());

        assertEquals(OperationType.TransferCredit, receiverAccount.getOperations().lastElement().getType());
        assertEquals(oldReceiverAmount, receiverAccount.getOperations().lastElement().getBalance());
        assertEquals(500.00, receiverAccount.getOperations().lastElement().getAmount());
        assertNotNull(receiverAccount.getOperations().lastElement().getDate());
    }
}
