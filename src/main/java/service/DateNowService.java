package service;

import java.time.LocalDate;

public class DateNowService implements DateService {

    public LocalDate getDate() {
        return LocalDate.now();
    }

}
