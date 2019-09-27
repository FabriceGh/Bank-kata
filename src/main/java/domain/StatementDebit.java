package domain;

import service.DateService;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class StatementDebit extends Statement{

    public StatementDebit(DateService dateService, StatementType type, double balance, double amount) {
        super(dateService, type, balance, amount);
    }

    @Override
    public String getStringRepresentation(){

        DecimalFormatSymbols localeFormat = DecimalFormatSymbols.getInstance(Locale.FRANCE);
        DecimalFormat twoDigitsDecimalFormat = new DecimalFormat("#0.00", localeFormat);
        String separator = "|";

        StringBuilder statementStringBuilder = new StringBuilder();
        statementStringBuilder.append(separator);
        statementStringBuilder.append(super.getDate().toString() + separator);
        statementStringBuilder.append(super.getType().toString() + separator);
        statementStringBuilder.append(separator);
        statementStringBuilder.append(twoDigitsDecimalFormat.format(super.getAmount()) + separator);
        statementStringBuilder.append(twoDigitsDecimalFormat.format(super.getBalance()) + separator);
        return statementStringBuilder.toString();

    }
}
