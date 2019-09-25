import domain.Account;
import domain.Operation;
import domain.OperationType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationTests {

    private final Operation depositOperation = new Operation(OperationType.Deposit, 1500.00, 100.00);
    private final Operation withdrawalOperation = new Operation(OperationType.Withdrawal, 2000.00, 200.00);
    private final Operation transferCreditOperation = new Operation(OperationType.TransferCredit, 2500.00, 300.00);
    private final Operation transferDebitOperation = new Operation(OperationType.TransferDebit, 1000.00, 400.00);

    @Test
    void should_return_deposit_operation_type () {
        assertEquals(OperationType.Deposit, depositOperation.getType());
    }

    @Test
    void should_return_withdrawal_operation_type () {
        assertEquals(OperationType.Withdrawal, withdrawalOperation.getType());
    }

    @Test
    void should_return_transfer_debit_operation_type () {
        assertEquals(OperationType.TransferDebit, transferDebitOperation.getType());
    }

    @Test
    void should_return_transfer_credit_operation_type () {
        assertEquals(OperationType.TransferCredit, transferCreditOperation.getType());
    }

    @Test
    void should_return_2000_balance () {
        assertEquals(2000.00, withdrawalOperation.getBalance());
    }

    @Test
    void should_return_100_amount () {
        assertEquals(100.00, depositOperation.getAmount());
    }


}
