package pl.itacademy.schedule;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        PropertiesReader propertiesReader = PropertiesReader.getInstance();
        HolidaysCheckerFactory hcFactory = new HolidaysCheckerFactory();

        String holidayCheckerType = propertiesReader.readProperty("holiday.checker.type");
        HolidaysChecker holidaysChecker = hcFactory.createHolidayChecker(holidayCheckerType);

        holidaysChecker.getHolidays(2020);
    }
}
