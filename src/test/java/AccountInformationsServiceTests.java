import domain.*;
import org.junit.jupiter.api.Test;
import service.AccountInformationsService;
import service.DateNowService;
import service.DateService;
import service.StatementConsolePrinterService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountInformationsServiceTests {

    private final DateService dateService = new DateNowService();
    private final Account account = new Account();
    private final Account receiverAccount = new Account();

    @Test
    void should_return_only_deposit_statements() {
        account.makeWithdraw(dateService, 2000.00);
        account.makeDeposit(dateService, 1000.00);
        account.makeDeposit(dateService, 10.00);
        account.makeTransfer(dateService, 3000.00, receiverAccount);

        List<StatementType> filterOnDeposit = new ArrayList<>();
        filterOnDeposit.add(StatementType.Deposit);

        List<Statement> mockedStatements = new ArrayList<>();
        mockedStatements.add(account.getStatements().get(1));
        mockedStatements.add(account.getStatements().get(2));

        List<Statement> onlyDepositStatements = AccountInformationsService.getStatementsFilteredByType(account, filterOnDeposit);

        assertEquals(mockedStatements.get(0), onlyDepositStatements.get(0));
        assertEquals(mockedStatements.get(1), onlyDepositStatements.get(1));
        assertEquals(mockedStatements.size(), onlyDepositStatements.size());

    }

    @Test
    void should_return_only_specified_date_statements() {

        DateService mockedDateService = mock(DateNowService.class);
        when(mockedDateService.getDate()).thenReturn(LocalDate.of(2019, 9, 1)
                , LocalDate.of(2019, 9, 2)
                , LocalDate.of(2019, 9, 2)
                , LocalDate.of(2019, 9, 3));

        account.makeDeposit(mockedDateService, 1000.00);
        account.makeWithdraw(mockedDateService, 1000.00);
        account.makeDeposit(mockedDateService, 500.00);
        account.makeTransfer(mockedDateService, 300.00, receiverAccount);

        LocalDate filterDate = LocalDate.of(2019, 9, 2);

        List<Statement> onlySpecifiedDateStatements = AccountInformationsService.getStatementsFilteredByDate(account, filterDate);

        assertEquals(2, onlySpecifiedDateStatements.size());
        assertEquals(account.getStatements().get(1), onlySpecifiedDateStatements.get(0));
        assertEquals(account.getStatements().get(2), onlySpecifiedDateStatements.get(1));

    }

    @Test
    void should_return_account_statement_representation() {

        DateService mockedDateService = mock(DateNowService.class);
        when(mockedDateService.getDate()).thenReturn(LocalDate.of(2019, 9, 1)
                , LocalDate.of(2019, 9, 2)
                , LocalDate.of(2019, 9, 2)
                , LocalDate.of(2019, 9, 3));

        account.makeDeposit(mockedDateService, 1000.00);
        account.makeWithdraw(mockedDateService, 1000.00);
        account.makeDeposit(mockedDateService, 500.00);
        account.makeTransfer(mockedDateService, 300.00, receiverAccount);

        String accountStatementRepresentation = AccountInformationsService.formatAccountStatement(account, new StatementConsolePrinterService());

        assertEquals("+------------------------------------------------+\n" +
                "|      date|         type| credit|  debit|balance|\n" +
                "+------------------------------------------------+\n" +
                "|2019-09-01|      Deposit|1000,00|       |   0,00|\n" +
                "|2019-09-02|   Withdrawal|       |1000,00|1000,00|\n" +
                "|2019-09-02|      Deposit| 500,00|       |   0,00|\n" +
                "|2019-09-03|TransferDebit|       | 300,00| 500,00|\n" +
                "+------------------------------------------------+\n", accountStatementRepresentation);
    }
}

