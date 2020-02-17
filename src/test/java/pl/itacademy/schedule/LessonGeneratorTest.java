package pl.itacademy.schedule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class LessonGeneratorTest {
    private LessonGenerator lessonGenerator;

    @BeforeEach
    public void setup() {
        lessonGenerator = new LessonGenerator(new DefaultHolidaysChecker());
    }

    @Test
    public void generateSchedule_successfullyGeneratesProperSchedule() {
        LessonParameters lessonParameters = new LessonParameters.Builder(
                LocalTime.of(17, 0),
                LocalTime.of(18, 30),
                3,
                Set.of(MONDAY, WEDNESDAY),
                LocalDate.of(2020, 2, 1))
                .fileName("schedule.xlsx")
                .showHelp(false)
                .build();

        LocalTime beginTime = LocalTime.of(17, 0);
        LocalTime endTime = LocalTime.of(18, 30);
        Lesson lesson1 = new Lesson(LocalDate.of(2020, 2, 3), beginTime, endTime);
        Lesson lesson2 = new Lesson(LocalDate.of(2020, 2, 5), beginTime, endTime);

        Schedule expectedSchedule = new Schedule(List.of(lesson1, lesson2), true);
        Schedule resultSchedule = lessonGenerator.generateSchedule(lessonParameters);

        assertThat(resultSchedule, equalTo(expectedSchedule));
    }

    @Test
    public void generateSchedule_unsuccessfulSchedule() {
        LessonParameters lessonParameters = new LessonParameters.Builder(
                LocalTime.of(17, 0),
                LocalTime.of(18, 30),
                4,
                Set.of(MONDAY, WEDNESDAY),
                LocalDate.of(2020, 2, 1))
                .fileName("schedule.xlsx")
                .showHelp(false)
                .build();

        LocalTime beginTime = LocalTime.of(17, 0);
        LocalTime endTime = LocalTime.of(18, 30);
        Lesson lesson1 = new Lesson(LocalDate.of(2020, 2, 3), beginTime, endTime);
        Lesson lesson2 = new Lesson(LocalDate.of(2020, 2, 5), beginTime, endTime);
        Lesson lesson3 = new Lesson(LocalDate.of(2020, 2, 10), beginTime, LocalTime.of(18, 0));

        Schedule expectedSchedule = new Schedule(List.of(lesson1, lesson2, lesson3), false);
        Schedule resultSchedule = lessonGenerator.generateSchedule(lessonParameters);

        assertThat(resultSchedule, equalTo(expectedSchedule));
    }

    @Test
    public void generateSchedule_withHolidays_successfullyGeneratesProperSchedule() {
        lessonGenerator = new LessonGenerator(new MockHolidaysChecker());

        LessonParameters lessonParameters = new LessonParameters.Builder(
                LocalTime.of(17, 0),
                LocalTime.of(18, 30),
                3,
                Set.of(MONDAY, WEDNESDAY),
                LocalDate.of(2020, 1, 1))
                .fileName("schedule.xlsx")
                .showHelp(false)
                .build();

        LocalTime beginTime = LocalTime.of(17, 0);
        LocalTime endTime = LocalTime.of(18, 30);
        Lesson lesson1 = new Lesson(LocalDate.of(2020, 1, 8), beginTime, endTime);
        Lesson lesson2 = new Lesson(LocalDate.of(2020, 1, 13), beginTime, endTime);

        Schedule expectedSchedule = new Schedule(List.of(lesson1, lesson2), true);
        Schedule resultSchedule = lessonGenerator.generateSchedule(lessonParameters);

        assertThat(resultSchedule, equalTo(expectedSchedule));
    }

    private static class MockHolidaysChecker implements HolidaysChecker {

        @Override
        public List<LocalDate> getHolidays(int year) {
            return List.of(LocalDate.of(year, 1, 1),
                    LocalDate.of(year, 1, 6));
        }
    }
}