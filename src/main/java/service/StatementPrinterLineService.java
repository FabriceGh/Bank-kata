package service;

import java.util.List;
import java.util.regex.Pattern;

public class StatementPrinterLineService {


    public static String[] tokenizeLine(String line, String separator) {

        if(line.startsWith(separator)){
            line = line.substring(1);
        }

        String[] lineTokens = line.split(Pattern.quote(separator));

        return lineTokens;
    }

    public static String formatLine(List<Integer> tableColumnsWidth, String[] tokenizedLine, String separator){

        StringBuilder formattedLine = new StringBuilder();

        formattedLine.append(separator);
        for (int currentColumn = 0; currentColumn < tokenizedLine.length; currentColumn++) {
            String paddedField = StatementPrinterFieldService.fieldSpacePadded(tokenizedLine[currentColumn], tableColumnsWidth.get(currentColumn));
            formattedLine.append(paddedField);
            formattedLine.append(separator);
        }
        formattedLine.append("\n");

        return formattedLine.toString();
    }



}
