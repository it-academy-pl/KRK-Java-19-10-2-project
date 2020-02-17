package pl.itacademy.schedule;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class DefaultHolidaysChecker implements HolidaysChecker{
    @Override
    public List<LocalDate> getHolidays(int year) {
        return Collections.emptyList();
    }
}
