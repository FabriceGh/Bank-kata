package service;

public class StatementPrinterFieldService {

    public static String fieldSpacePadded(String dataToPad, int columnWidth){

        int dataToPadLength = dataToPad.length();
        StringBuilder fieldStringBuilder = new StringBuilder();

        while (dataToPadLength < columnWidth){
            fieldStringBuilder.append(" ");
            dataToPadLength ++;
        }

        fieldStringBuilder.append(dataToPad);

        return fieldStringBuilder.toString();

    }
}
