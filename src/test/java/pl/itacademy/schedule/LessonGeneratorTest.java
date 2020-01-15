package pl.itacademy.schedule;

import jdk.jfr.StackTrace;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static org.junit.jupiter.api.Assertions.*;

class LessonGeneratorTest {
    //TODO: make this test compilable

    @Test
    public void generateSchedule_successfullyGeneratesProperSchedule() {
        //TODO: finish test method
        LessonParameters lessonParameters = new LessonParameters.Builder(
                LocalTime.of(17, 0),
                LocalTime.of(18, 30),
                3,
                Set.of(MONDAY, WEDNESDAY),
                LocalDate.of(2020, 2, 1))
                .fileName("schedule.xlsx")
                .showHelp(false)
                .build();
    }
}