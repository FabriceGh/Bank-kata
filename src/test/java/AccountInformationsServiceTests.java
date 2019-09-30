import domain.*;
import org.junit.jupiter.api.Test;
import org.mockito.internal.configuration.injection.MockInjection;
import service.AccountInformations;
import service.DateNow;
import service.DateService;

import java.time.LocalDate;
import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountInformationsTests {

    private final DateService dateService = new DateNow();
    private final Account account = new Account();
    private final  Account receiverAccount = new Account();

    @Test
    void should_return_only_deposit_statements() {
        account.makeWithdraw(dateService, 2000.00);
        account.makeDeposit(dateService, 1000.00);
        account.makeDeposit(dateService, 10.00);
        account.makeTransfer(dateService, 3000.00, receiverAccount);

        List<StatementType> filterOnDeposit = new Vector<>();
        filterOnDeposit.add(StatementType.Deposit);

        List<Statement> mockedStatements = new Vector<>();
        mockedStatements.add(account.getStatements().get(1));
        mockedStatements.add(account.getStatements().get(2));

        List<Statement> onlyDepositStatements = AccountInformations.getStatementsFilteredByType(account, filterOnDeposit);

        assertEquals(mockedStatements.get(0), onlyDepositStatements.get(0));
        assertEquals(mockedStatements.get(1), onlyDepositStatements.get(1));
        assertEquals(mockedStatements.size(), onlyDepositStatements.size());

    }

    @Test
    void should_return_only_specified_date_statements() {

        DateService mockedDateService = mock(DateNow.class);
        when(mockedDateService.getDate()).thenReturn(LocalDate.of(2019,9,1)
                , LocalDate.of(2019,9,2)
                , LocalDate.of(2019,9,2)
                , LocalDate.of(2019,9,3));

        account.makeDeposit(mockedDateService, 1000.00);
        account.makeWithdraw(mockedDateService, 1000.00);
        account.makeDeposit(mockedDateService, 500.00);
        account.makeTransfer(mockedDateService, 300.00, receiverAccount);

        LocalDate filterDate = LocalDate.of(2019, 9, 2);

        List<Statement> onlySpecifiedDateStatements = AccountInformations.getStatementsFilteredByDate(account, filterDate);

        assertEquals(2, onlySpecifiedDateStatements.size());
        assertEquals(account.getStatements().get(1), onlySpecifiedDateStatements.get(0));
        assertEquals(account.getStatements().get(2), onlySpecifiedDateStatements.get(1));

    }
}
