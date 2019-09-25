import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTests {

    @Test
    void should_create_empty_account() {
        Account account = new Account();

        assertEquals(0.0, account.getAmount());
    }
}
