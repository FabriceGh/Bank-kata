import domain.Statement;
import domain.StatementCredit;
import domain.StatementDebit;
import domain.StatementType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatementTests {

    private final Statement depositStatement = new StatementCredit(StatementType.Deposit, 1500.00, 100.00);
    private final Statement withdrawalStatement = new StatementDebit(StatementType.Withdrawal, 2000.00, 200.00);
    private final Statement transferCreditStatement = new StatementDebit(StatementType.TransferCredit, 2500.00, 300.00);
    private final Statement transferDebitStatement = new StatementCredit(StatementType.TransferDebit, 1000.00, 400.00);

    @Test
    void should_return_deposit_statement_type () {
        assertEquals(StatementType.Deposit, depositStatement.getType());
    }

    @Test
    void should_return_withdrawal_statement_type () {
        assertEquals(StatementType.Withdrawal, withdrawalStatement.getType());
    }

    @Test
    void should_return_transfer_debit_statement_type () {
        assertEquals(StatementType.TransferDebit, transferDebitStatement.getType());
    }

    @Test
    void should_return_transfer_credit_statement_type () {
        assertEquals(StatementType.TransferCredit, transferCreditStatement.getType());
    }

    @Test
    void should_return_2000_balance () {
        assertEquals(2000.00, withdrawalStatement.getBalance());
    }

    @Test
    void should_return_100_amount () {
        assertEquals(100.00, depositStatement.getAmount());
    }


}
