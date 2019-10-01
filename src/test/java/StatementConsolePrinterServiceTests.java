import domain.Statement;
import domain.StatementCredit;
import domain.StatementDebit;
import domain.StatementType;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import service.DateNowService;
import service.DateService;
import service.StatementConsolePrinterService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StatementConsolePrinterServiceTests {

    private final StatementConsolePrinterService consolePrinterService = new StatementConsolePrinterService();
    private final DateService dateService = new DateNowService();

    @Mock
    DateService mockedDateService = mock(DateNowService.class);

    @Test
    void should_return_5_columns(){
        String[] tokenizedLine = new String[5];
        tokenizedLine[0] = "date";
        tokenizedLine[1] = "operation";
        tokenizedLine[2] = "credit";
        tokenizedLine[3] = "debit";
        tokenizedLine[4] = "balance";
        List<Integer> columnsInLine = consolePrinterService.computeColumnWidthForALine(tokenizedLine);
        assertEquals(5, Objects.requireNonNull(columnsInLine).size());
    }

    @Test
    void should_return_correct_columns_lengths_for_one_line(){
        List<String> tableData = new ArrayList<>();
        tableData.add("|date|operation|credit|debit|balance|");

        List<Integer> columnsInLine = consolePrinterService.computeTableRowWidth(tableData);

        assertEquals(4, columnsInLine.get(0));
        assertEquals(9, columnsInLine.get(1));
        assertEquals(6, columnsInLine.get(2));
        assertEquals(5, columnsInLine.get(3));
        assertEquals(7, columnsInLine.get(4));
    }

    @Test
    void should_return_well_sized_table_separator() {
        String separator = consolePrinterService.tableSeparator(10);

        assertEquals("+----------+\n", separator);
    }


    @Test
    void should_return_correct_table_max_columns_lengths(){
        List<String> lines = new ArrayList<>();
        lines.add("|date|operation|credit|debit|balance|");
        lines.add("|random|column|content||test|");

        List<Integer> maxColumnSizes = consolePrinterService.computeTableRowWidth(lines);

        assertEquals(6, maxColumnSizes.get(0));
        assertEquals(9, maxColumnSizes.get(1));
        assertEquals(7, maxColumnSizes.get(2));
        assertEquals(5, maxColumnSizes.get(3));
        assertEquals(7, maxColumnSizes.get(4));
    }

    @Test
    void should_return_well_formatted_table(){

        when(mockedDateService.getDate()).thenReturn(LocalDate.of(2019,9,1));

        List<Statement> statements = new ArrayList<>();
        statements.add(new StatementCredit(mockedDateService, StatementType.Deposit, 1000.00, 200.00));
        statements.add(new StatementDebit(mockedDateService, StatementType.Withdrawal, 1200.00, 500.00));
        statements.add(new StatementDebit(mockedDateService, StatementType.TransferDebit, 1700.00, 200.00));

        List<String> statementRepresentation = new ArrayList<>();
        statementRepresentation.add("|date|operation|credit|debit|balance|");

        statementRepresentation.addAll(statements.stream()
                                                .map(Statement::getStringRepresentation)
                                                .collect(Collectors.toList())
        );

        String formattedTable = consolePrinterService.format(statementRepresentation);

        assertEquals(   "+----------------------------------------------+\n" +
                                "|      date|    operation|credit| debit|balance|\n" +
                                "+----------------------------------------------+\n" +
                                "|2019-09-01|      Deposit|200,00|      |1000,00|\n" +
                                "|2019-09-01|   Withdrawal|      |500,00|1200,00|\n" +
                                "|2019-09-01|TransferDebit|      |200,00|1700,00|\n" +
                                "+----------------------------------------------+\n", formattedTable);
    }

    @Test
    void should_return_well_formatted_table_with_empty_column(){

        when(mockedDateService.getDate()).thenReturn(LocalDate.of(2019,9,5));

        List<Statement> statements = new ArrayList<>();
        statements.add(new StatementCredit(mockedDateService, StatementType.Deposit, 1000.00, 200.00));

        List<String> statementRepresentation = new ArrayList<>();
        statementRepresentation.add("|date|operation|credit|debit|balance|");

        statementRepresentation.addAll(statements.stream()
                .map(Statement::getStringRepresentation)
                .collect(Collectors.toList())
        );

        String formattedTable = consolePrinterService.format(statementRepresentation);


        assertEquals(  "+-----------------------------------------+\n" +
                                "|      date|operation|credit|debit|balance|\n" +
                                "+-----------------------------------------+\n" +
                                "|2019-09-05|  Deposit|200,00|     |1000,00|\n" +
                                "+-----------------------------------------+\n", formattedTable);
    }


}
