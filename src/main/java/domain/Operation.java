package domain;

import java.time.LocalDate;

public class Operation {

    private final LocalDate date;
    private final OperationType type;
    private final double balance;
    private final double amount;

    public Operation(OperationType type, double balance, double amount){
        this.date = LocalDate.now();
        this.type = type;
        this.balance = balance;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public OperationType getType() {
        return type;
    }

    public double getBalance() {
        return balance;
    }

    public double getAmount() {
        return amount;
    }

}
