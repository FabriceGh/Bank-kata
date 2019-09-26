import domain.Statement;
import domain.StatementCredit;
import domain.StatementDebit;
import domain.StatementType;
import org.junit.jupiter.api.Test;
import service.StatementPrinter;

import java.util.List;
import java.util.Objects;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatementPrinterTests {

    @Test
    void should_return_5_columns(){
        Vector<Integer> columnsInLine = StatementPrinter.columnsLengthInLine("|date|operation|credit|debit|balance|", "|");
        assertEquals(5, Objects.requireNonNull(columnsInLine).size());
    }

    @Test
    void should_return_correct_columns_lengths(){
        Vector<Integer> columnsInLine = StatementPrinter.columnsLengthInLine("|date|operation|credit|debit|balance|", "|");

        assertEquals(4, columnsInLine.get(0));
        assertEquals(9, columnsInLine.get(1));
        assertEquals(6, columnsInLine.get(2));
        assertEquals(5, columnsInLine.get(3));
        assertEquals(7, columnsInLine.get(4));
    }

    @Test
    void should_return_correct_table_max_columns_lengths(){
        Vector<String> lines = new Vector<>();
        lines.add("|date|operation|credit|debit|balance|");
        lines.add("|random|column|content||test|");

        Vector<Integer> maxColumnSizes = StatementPrinter.maxColumnsLengthInSet(lines, "|");

        assertEquals(6, maxColumnSizes.get(0));
        assertEquals(9, maxColumnSizes.get(1));
        assertEquals(7, maxColumnSizes.get(2));
        assertEquals(5, maxColumnSizes.get(3));
        assertEquals(7, maxColumnSizes.get(4));
    }

    @Test
    void should_pad_data_to_30_char(){
        String dataToPad = "test";
        String paddedData = StatementPrinter.columnSpacePadder(dataToPad, 30);

        assertEquals("                          "+dataToPad, paddedData);
    }

    @Test
    void should_return_well_formatted_line(){
        Vector<String> lines = new Vector<>();
        lines.add("|date|operation|credit|debit|balance|");
        lines.add("|random|column|content||test|");

        Vector<Integer> maxColumnSizes = StatementPrinter.maxColumnsLengthInSet(lines, "|");

        String formattedLine = StatementPrinter.lineFormatter(lines.get(1), "|", maxColumnSizes);

        assertEquals("|random|   column|content|     |   test|\n", formattedLine);
    }

    @Test
    void should_return_well_sized_horizontal_separator(){
        List<Integer> simulatesListOfColumnWidth = new Vector<Integer>();
        simulatesListOfColumnWidth.add(8);
        simulatesListOfColumnWidth.add(5);
        simulatesListOfColumnWidth.add(6);

        String separator = StatementPrinter.generateHorizontalSeparator(simulatesListOfColumnWidth);

        assertEquals("+---------------------+\n", separator);
    }

    @Test
    void should_return_well_formatted_table(){
        Vector<Statement> statements = new Vector<>();
        statements.add(new StatementCredit(StatementType.Deposit, 1000.00, 200.00));
        statements.add(new StatementDebit(StatementType.Withdrawal, 1200.00, 500.00));
        statements.add(new StatementDebit(StatementType.TransferDebit, 1700.00, 200.00));

        String formattedTable = StatementPrinter.buildDisplayableTab(statements, "|");

        assertEquals(  "+----------------------------------------------+\n" +
                                "|      date|    operation|credit| debit|balance|\n" +
                                "+----------------------------------------------+\n" +
                                "|2019-09-26|      Deposit|200,00|      |1000,00|\n" +
                                "|2019-09-26|   Withdrawal|      |500,00|1200,00|\n" +
                                "|2019-09-26|TransferDebit|      |200,00|1700,00|\n" +
                                "+----------------------------------------------+\n", formattedTable);
    }


}