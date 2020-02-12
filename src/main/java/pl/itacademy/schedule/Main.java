package pl.itacademy.schedule;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        PropertiesReader propertiesReader = PropertiesReader.getInstance();
        System.out.println(propertiesReader.readProperty("date.format"));

        ExcelGenerator excelGenerator = new ExcelGenerator();
        LocalTime beginTime = LocalTime.of(17, 0);
        LocalTime endTime = LocalTime.of(18, 30);
        Lesson lesson1 = new Lesson(LocalDate.of(2020, 1, 1), beginTime, endTime);
        Lesson lesson2 = new Lesson(LocalDate.of(2020, 1, 2), beginTime, endTime);
        Lesson lesson3 = new Lesson(LocalDate.of(2020, 1, 3), beginTime, endTime);
        Schedule schedule = new Schedule(List.of(lesson1, lesson2, lesson3), true);
        Workbook workbook = excelGenerator.createExcel(schedule);

        FileOutputStream fos = new FileOutputStream("test.xlsx");
        workbook.write(fos);
        workbook.close();
        fos.close();

        CalendarificHolidaysChecker holidaysChecker = new CalendarificHolidaysChecker();
        holidaysChecker.getHolidays(2020);
    }
}
