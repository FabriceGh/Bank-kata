package service;

import java.util.regex.Pattern;

public class StatementPrinterLineService {

    private static String separator = "|";

    public static String[] tokenizeLine(String line) {

        if(line.startsWith(separator)){
            line = line.substring(1);
        }

        String[] lineTokens = line.split(Pattern.quote(separator));

        return lineTokens;
    }


}
