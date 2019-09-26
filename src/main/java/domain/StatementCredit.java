package domain;

import java.text.DecimalFormat;
import java.util.Optional;

public class StatementCredit extends Statement {

    public StatementCredit(StatementType type, double balance, double amount) {
        super(type, balance, amount);
    }

    @Override
    public String getStringRepresentation(){

        DecimalFormat twoDigitsDecimalFormat = new DecimalFormat("#.00");
        String separator = "|";

        StringBuilder statementStringBuilder = new StringBuilder();
        statementStringBuilder.append(separator);
        statementStringBuilder.append(super.getDate().toString() + separator);
        statementStringBuilder.append(super.getType().toString() + separator);
        statementStringBuilder.append(twoDigitsDecimalFormat.format(super.getAmount()) + separator);
        statementStringBuilder.append(separator);
        statementStringBuilder.append(twoDigitsDecimalFormat.format(super.getBalance()) + separator);
        return statementStringBuilder.toString();

    }

}
