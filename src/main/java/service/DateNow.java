package service;

import java.time.LocalDate;

public class DateNow implements DateService {

    public LocalDate getDate() {
        return LocalDate.now();
    }

}
