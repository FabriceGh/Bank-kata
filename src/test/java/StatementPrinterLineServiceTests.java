import org.junit.jupiter.api.Test;
import service.StatementPrinterLineService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatementPrinterLineServiceTests {

    @Test
    void should_return_tokenized_line(){

        String lineData = "|column1|column2|column3";
        String[] tokenizedLineData;

        tokenizedLineData = StatementPrinterLineService.tokenizeLine(lineData);

        assertEquals("column1", tokenizedLineData[0]);
        assertEquals("column2", tokenizedLineData[1]);
        assertEquals("column3", tokenizedLineData[2]);


    }
}
