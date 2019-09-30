package service;

import domain.Account;
import domain.Statement;
import domain.StatementType;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class AccountInformationsService {

    public static List<Statement> getStatementsFilteredByType(Account account, List<StatementType> filterTypes){

        return account.getStatements().stream().filter(statement -> filterTypes.contains(statement.getType()))
                .collect(Collectors.toList());

    }

    public static List<Statement> getStatementsFilteredByDate(Account account, LocalDate dateFilter){

        return account.getStatements().stream().filter(statement -> statement.getDate().equals(dateFilter))
                .collect(Collectors.toList());

    }

}
