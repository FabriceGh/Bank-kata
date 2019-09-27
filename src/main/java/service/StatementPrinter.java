package service;

import domain.Statement;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public abstract class StatementPrinter {

    public static String buildDisplayableTab(List<Statement> inputData, String separator){

        StringBuilder displayableTable = new StringBuilder();
        Vector<String> dataToDisplay = new Vector<>();

        dataToDisplay.add(getHeader());

        for(Statement currentStatement : inputData){
            dataToDisplay.add(currentStatement.getStringRepresentation());
        }

        Vector<Integer> tableColumnLength = maxColumnsLengthInSet(dataToDisplay, separator);

        dataToDisplay.remove(0);

        String horizontalSeparator = generateHorizontalSeparator(tableColumnLength);
        displayableTable.append(horizontalSeparator);
        displayableTable.append(lineFormatter(getHeader(), separator, tableColumnLength));
        displayableTable.append(horizontalSeparator);

        for(String lineOfData : dataToDisplay){
            lineOfData = lineFormatter(lineOfData, separator, tableColumnLength);
            displayableTable.append(lineOfData);
        }

        displayableTable.append(horizontalSeparator);

        return displayableTable.toString();

    }

    public static String getHeader() {
        return "|date|operation|credit|debit|balance|";
    }

    public static Vector<Integer> columnsLengthInLine(String content, String separator) {

        String lineContent = content;

        try {

            Vector<Integer> columnsLength = new Vector<>();

            if(!lineContent.contains(separator)) {
                throw new IllegalArgumentException("Separator not found");
            }

            if(lineContent.startsWith(separator)){
                lineContent = lineContent.substring(1, lineContent.length());
            }

            if(!lineContent.endsWith(separator)){
                lineContent = lineContent + separator;
            }

            while (lineContent.contains(separator)) {
                int nextSeparatorIndex = lineContent.indexOf(separator);
                columnsLength.add(nextSeparatorIndex);
                lineContent = lineContent.substring(nextSeparatorIndex+1, lineContent.length());
            }

            return columnsLength;

        } catch (IllegalStateException illegalArgument) {
            System.out.println("Separator not found in content");
            return null;
        }

    }

    public static Vector<Integer> maxColumnsLengthInSet(Vector<String> orderedDisplayContent, String separator) {

        Vector<Vector<Integer>> contentColumnsSize = new Vector<>();
        Vector<Integer> tableSize;

        for(String line : orderedDisplayContent) {
            contentColumnsSize.add(columnsLengthInLine(line, separator));
        }

        tableSize = contentColumnsSize.get(0);

        for(int lineTabIndex = 0 ; lineTabIndex < contentColumnsSize.size() ; lineTabIndex++){
            for (int columnIndexInLine = 0 ; columnIndexInLine < contentColumnsSize.get(lineTabIndex).size() ; columnIndexInLine++){
                Vector<Integer> currentLine = contentColumnsSize.get(lineTabIndex);
                if(currentLine.get(columnIndexInLine) > tableSize.get(columnIndexInLine)){
                    tableSize.set(columnIndexInLine, currentLine.get(columnIndexInLine));
                }
            }
        };

        return tableSize;
    }

    public static String columnSpacePadded(String dataToPad, int columnWidth){

        int dataToPadLength = dataToPad.length();
        StringBuilder fieldStringBuilder = new StringBuilder();

        while (dataToPadLength < columnWidth){
            fieldStringBuilder.append(" ");
            dataToPadLength ++;
        }

        fieldStringBuilder.append(dataToPad);

        return fieldStringBuilder.toString();

    }

    public static String lineFormatter(String lineData, String separator, List<Integer> tableColumnSize){

        StringBuilder lineStringBuilder = new StringBuilder();
        lineStringBuilder.append(separator);

        if(lineData.startsWith(separator)){
            lineData = lineData.substring(1, lineData.length());
        }

        if(!lineData.endsWith(separator)){
            lineData = lineData + separator;
        }

        for(int column = 0 ; column < tableColumnSize.size() ; column++){

            String columnData = lineData.substring(0, lineData.indexOf(separator));
            columnData = columnSpacePadded(columnData, tableColumnSize.get(column));
            lineStringBuilder.append(columnData);
            lineStringBuilder.append(separator);
            lineData = lineData.substring(lineData.indexOf(separator)+1);
        }

        lineStringBuilder.append("\n");

        return lineStringBuilder.toString();

    }

    public static String generateHorizontalSeparator(List<Integer> tableColumnSize){

        StringBuilder horizontalSeparatorBuilder = new StringBuilder();
        Stream tableColumnSizeInfos = tableColumnSize.stream();
        int totalTableWidth;

        totalTableWidth = (int) tableColumnSizeInfos.collect(Collectors.summingInt(Integer::intValue));
        //add separators (included border ones)
        totalTableWidth += tableColumnSize.size() + 1;
        //remove corners
        totalTableWidth -= 2;

        horizontalSeparatorBuilder.append("+");
        horizontalSeparatorBuilder.append(IntStream.range(0, totalTableWidth).mapToObj(curr -> "-").collect(Collectors.joining("")));
        horizontalSeparatorBuilder.append("+");
        horizontalSeparatorBuilder.append("\n");

        return horizontalSeparatorBuilder.toString();

    }


}
