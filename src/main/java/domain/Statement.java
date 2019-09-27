package domain;

import service.DateNow;
import service.DateService;

import java.time.LocalDate;


public abstract class Statement {

    private final LocalDate date;
    private final StatementType type;
    private final double balance;
    private final double amount;

    public Statement(DateService dateService, StatementType type, double balance, double amount){
        this.date = dateService.getDate();
        this.type = type;
        this.balance = balance;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public StatementType getType() {
        return type;
    }

    public double getBalance() {
        return balance;
    }

    public double getAmount() {
        return amount;
    }

    public abstract String getStringRepresentation();
}
