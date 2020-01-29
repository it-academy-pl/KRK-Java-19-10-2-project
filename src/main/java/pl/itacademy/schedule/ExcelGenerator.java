package pl.itacademy.schedule;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

//TODO: finish this class implementation
//TODO: use examples from project files
//TODO: generate all rows for each separate lesson
//TODO: set proper formats for date and time cells
//TODO: fill formulas for cells wit formulas
//TODO: think about how to avoid double row creation for cases, when status part of wokbook will be created
//TODO: last row with status should be next row after the last lesson, or next row after status section if we have small amount of lessons

public class ExcelGenerator {
    public Workbook createExcel(Schedule schedule) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        List<Lesson> lessons = schedule.getLessons();
        for (int i = 0; i < lessons.size(); i++) {
            Lesson lesson = lessons.get(i);
            Row row = sheet.createRow(i);
            Cell dateCell = row.createCell(0);
            dateCell.setCellValue(lesson.getDate());
        }
        return workbook;
    }
}
