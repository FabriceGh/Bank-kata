package service;

import domain.Account;
import domain.Statement;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import static service.StatementPrinterLineService.tokenizeLine;
import static service.StatementPrinterLineService.formatLine;

public class StatementConsolePrinterService implements StatementPrinterService{


    private String separator ;
    private String horizontalDelimiter ;
    private String cornerDelimiter ;

    public StatementConsolePrinterService(){
        this.separator = "|";
        this.horizontalDelimiter = "-";
        this.cornerDelimiter = "+";
    }

    @Override
    public String format(List<String> tableData) {
        StringBuilder formattedTable = new StringBuilder();

        List<Integer> tableColumnsWidth = computeTableRowWidth(tableData);
        String[] tokenizedLine = tokenizeLine(tableData.get(0), this.separator);

        int tabWidth = tableColumnsWidth.stream().reduce(0, Integer::sum);
        int separatorWidth = tabWidth-2+tableColumnsWidth.size()+1;

        formattedTable.append(tableSeparator(separatorWidth));
        formattedTable.append(formatLine(tableColumnsWidth, tokenizedLine, this.separator));
        formattedTable.append(tableSeparator(separatorWidth));

        for (String line : tableData.subList(1, tableData.size())) {
            tokenizedLine = tokenizeLine(line, this.separator);
            formattedTable.append(formatLine(tableColumnsWidth, tokenizedLine, this.separator));
        }

        formattedTable.append(tableSeparator(separatorWidth));

        return formattedTable.toString();
    }

    public String tableSeparator(int lineWidth) {
        String separator = this.cornerDelimiter
                + new String(new char[lineWidth]).replace("\0", this.horizontalDelimiter)
                + this.cornerDelimiter
                + "\n";
        return separator;
    }

    public static List<Integer> computeColumnWidthForALine(String[] lineTokens) {

        List<Integer> lineTokenLength = Arrays.stream(lineTokens)
                .map(String::length)
                .collect(Collectors.toList());

        return lineTokenLength;
    }

    public List<Integer> computeTableRowWidth(List<String> tableData) {

        List<Integer> tableColumnWidth;
        List<Integer> currentLineColumnsWidth;
        int currentColumnWidth;

        String[] tokenizedLine = tokenizeLine(tableData.get(0), this.separator);
        tableColumnWidth = computeColumnWidthForALine(tokenizedLine);

        for (int lineTabIndex = 0; lineTabIndex < tableData.size(); lineTabIndex++) {
            tokenizedLine = tokenizeLine(tableData.get(lineTabIndex), this.separator);
            currentLineColumnsWidth = computeColumnWidthForALine(tokenizedLine);
            for (int columnIndexInLine = 0; columnIndexInLine < currentLineColumnsWidth.size(); columnIndexInLine++) {
                currentColumnWidth = currentLineColumnsWidth.get(columnIndexInLine);
                if (currentColumnWidth > tableColumnWidth.get(columnIndexInLine)) {
                    tableColumnWidth.set(columnIndexInLine, currentColumnWidth);
                }
            }
        }

        return tableColumnWidth;

    }







}
