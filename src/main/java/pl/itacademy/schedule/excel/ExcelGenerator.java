package pl.itacademy.schedule.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pl.itacademy.schedule.schedule.Lesson;
import pl.itacademy.schedule.schedule.Schedule;

import java.util.List;

import static java.time.temporal.ChronoUnit.MINUTES;

public class ExcelGenerator {

    public Workbook createExcel(Schedule schedule) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        List<Lesson> lessons = schedule.getLessons();

        CellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setDataFormat(workbook.createDataFormat().getFormat("d.m.yyyy"));

        CellStyle timeStyle = workbook.createCellStyle();
        timeStyle.setDataFormat(workbook.createDataFormat().getFormat("HH:MM"));

        for (int i = 0; i < lessons.size(); i++) {
            Lesson lesson = lessons.get(i);
            Row row = getOrCreateRow(sheet, i);
            Cell dateCell = row.createCell(0);
            dateCell.setCellValue(lesson.getDate());
            dateCell.setCellStyle(dateStyle);

            Cell beginTimeCell = row.createCell(3);
            beginTimeCell.setCellValue(lesson.getDate().atTime(lesson.getBeginTime()));
            beginTimeCell.setCellStyle(timeStyle);

            Cell endTimeCell = row.createCell(4);
            endTimeCell.setCellValue(lesson.getDate().atTime(lesson.getEndTime()));
            endTimeCell.setCellStyle(timeStyle);

            Cell timeLesson = row.createCell(5);
            int index = i + 1;
            String formula = "IF(B" + index + "=\"done\",HOUR(E" + index + "-D" + index + ") + MINUTE(E" + index + "-D" + index + ")/60,\"\")";
            timeLesson.setCellFormula(formula);
        }

        Row hoursPlannedRow = getOrCreateRow(sheet, 1);
        Cell hoursPlanned = hoursPlannedRow.createCell(7);
        hoursPlanned.setCellValue("hours planned");
        Cell numHoursPlanned = hoursPlannedRow.createCell(8);
        numHoursPlanned.setCellValue(numberOfPlannedHours(schedule));

        Row hoursDoneRow = getOrCreateRow(sheet, 0);
        Cell hoursDone = hoursDoneRow.createCell(7);
        hoursDone.setCellValue("hours done");
        Cell numHoursDone = hoursDoneRow.createCell(8);
        numHoursDone.setCellFormula("SUM(F1:F57)");

        Row lessonPlanedRow = getOrCreateRow(sheet, 4);
        Cell lessonPlanned = lessonPlanedRow.createCell(7);
        lessonPlanned.setCellValue("lesson planned");
        Cell numLessonPlanned = lessonPlanedRow.createCell(8);
        numLessonPlanned.setCellValue(lessons.size());

        Row lessonDoneRow = getOrCreateRow(sheet, 3);
        Cell lessonDone = lessonDoneRow.createCell(7);
        lessonDone.setCellValue("lesson done");
        Cell numLessonDone = lessonDoneRow.createCell(8);
        numLessonDone.setCellFormula("COUNTIF(B1:B57,\"done\")");

        int index = sheet.getLastRowNum();
        Row statusRow = getOrCreateRow(sheet, index + 1);
        Cell status = statusRow.createCell(7);
        status.setCellValue("STATUS");
        Cell completedOrProgress = statusRow.createCell(8);
        completedOrProgress.setCellFormula("IF(I1=I2,\"COMPLETED\",\"IN PROGRESS\")");

        return workbook;
    }

    private Row getOrCreateRow(Sheet sheet, int index) {
        Row row = sheet.getRow(index);
        if (row == null) {
            row = sheet.createRow(index);
        }
        return row;
    }

    private double numberOfPlannedHours(Schedule schedule) {
        List<Lesson> lessons = schedule.getLessons();
        double hours = 0;
        for (Lesson lesson : lessons) {
            hours += (MINUTES.between(lesson.getBeginTime(), lesson.getEndTime()));
        }
        return hours / 60.0;
    }
}