package pl.itacademy.schedule.holiday;

import java.time.LocalDate;
import java.util.List;

public interface HolidaysChecker {
    List<LocalDate> getHolidays(int year);
}
