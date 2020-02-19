package pl.itacademy.schedule;

import org.apache.commons.cli.ParseException;
import org.apache.poi.ss.usermodel.Workbook;
import pl.itacademy.schedule.excel.ExcelGenerator;
import pl.itacademy.schedule.holiday.HolidaysChecker;
import pl.itacademy.schedule.holiday.HolidaysCheckerFactory;
import pl.itacademy.schedule.schedule.LessonGenerator;
import pl.itacademy.schedule.schedule.LessonParameters;
import pl.itacademy.schedule.schedule.Schedule;
import pl.itacademy.schedule.util.InputParametersReader;
import pl.itacademy.schedule.util.PropertiesReader;

import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        InputParametersReader read = new InputParametersReader();

        LessonParameters lessonParameters = null;
        try {
            lessonParameters = read.readParameters(args);
        } catch (ParseException e) {
            read.showHelp();
            return;
        }

        HolidaysCheckerFactory holidaysCheckerFactory = new HolidaysCheckerFactory();
        PropertiesReader propertiesReader = PropertiesReader.getInstance();
        HolidaysChecker holidayChecker = holidaysCheckerFactory.createHolidayChecker(propertiesReader.readProperty("holiday.checker.type"));
        LessonGenerator lessonGenerator = new LessonGenerator(holidayChecker);

        Schedule schedule = lessonGenerator.generateSchedule(lessonParameters);

        ExcelGenerator excelGenerator = new ExcelGenerator();

        Workbook excel = excelGenerator.createExcel(schedule);

        FileOutputStream fos = new FileOutputStream(lessonParameters.getFileName());
        excel.write(fos);
        fos.close();
        excel.close();
    }
}
