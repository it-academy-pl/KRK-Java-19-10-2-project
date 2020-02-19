package pl.itacademy.schedule.holiday;

public class HolidaysCheckerFactory {
    public HolidaysChecker createHolidayChecker(String type) {
        if (type.equalsIgnoreCase("Default")) {
            return new DefaultHolidaysChecker();
        }
        if (type.equalsIgnoreCase("Calendarific")) {
            return new CalendarificHolidaysChecker();
        }

        throw new IllegalArgumentException("wrong Holiday Checker type");
    }

}
