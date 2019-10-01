import org.junit.jupiter.api.Test;
import service.StatementPrinterLineService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static service.StatementPrinterLineService.tokenizeLine;
import static service.StatementPrinterLineService.formatLine;

public class StatementPrinterLineServiceTests {

    @Test
    void should_return_tokenized_line(){

        String lineData = "|column1|column2|column3";
        String[] tokenizedLineData;

        tokenizedLineData = tokenizeLine(lineData, "|");

        assertEquals("column1", tokenizedLineData[0]);
        assertEquals("column2", tokenizedLineData[1]);
        assertEquals("column3", tokenizedLineData[2]);


    }

    @Test
    void should_return_formatted_line() {
        List<Integer> tableColumnWidth = new ArrayList<Integer>();
        tableColumnWidth.add(9);
        tableColumnWidth.add(10);
        tableColumnWidth.add(12);

        String[] tokenizedLineData = new String[3];
        tokenizedLineData[0] = "column1";
        tokenizedLineData[1] = "column2";
        tokenizedLineData[2] = "column3";

        String formattedLine = formatLine(tableColumnWidth, tokenizedLineData, "|");

        assertEquals("|  column1|   column2|     column3|\n", formattedLine);
    }
}
