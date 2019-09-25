import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTests {

    private final  Account account = new Account();

    @Test
    void should_create_empty_account() {

        assertEquals(0.0, account.getAmount());

    }

    @Test
    void should_create_1000_deposit() {

        double old_amount = account.getAmount();
        account.makeDeposit(1000.00);
        assertEquals(old_amount + 1000.00, account.getAmount());

    }

    @Test
    void should_create_1000_withdrawal() {

        double old_amount = account.getAmount();
        account.makeWithdraw(1000.00);
        assertEquals(old_amount - 1000.00, account.getAmount());

    }

}
