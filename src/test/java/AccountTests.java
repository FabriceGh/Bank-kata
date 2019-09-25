import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTests {

    private final  Account account = new Account();
    private final  Account receiverAccount = new Account();

    @Test
    void should_create_empty_account() {

        assertEquals(0.0, account.getAmount());

    }

    @Test
    void should_create_1000_deposit() {

        double oldAmount = account.getAmount();
        account.makeDeposit(1000.00);
        assertEquals(oldAmount + 1000.00, account.getAmount());

    }

    @Test
    void should_create_1000_withdrawal() {

        double oldAmount = account.getAmount();
        account.makeWithdraw(1000.00);
        assertEquals(oldAmount - 1000.00, account.getAmount());

    }

    @Test
    void should_create_500_transfer() {
        double oldAmount = account.getAmount();
        double oldReceiverAmount = receiverAccount.getAmount();
        account.makeTransfer(500.00, receiverAccount);

        assertEquals(oldAmount - 500.00, account.getAmount());
        assertEquals(oldReceiverAmount + 500.00, receiverAccount.getAmount());
    }
}
