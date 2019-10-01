import org.junit.jupiter.api.Test;
import service.StatementPrinterFieldService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatementPrinterFieldServiceTests {



    @Test
    void should_return_padded_field(){
        String paddedField;
        paddedField = StatementPrinterFieldService.fieldSpacePadded("test", 10);

        assertEquals("      test", paddedField);

    }

}
