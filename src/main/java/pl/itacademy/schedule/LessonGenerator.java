package pl.itacademy.schedule;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.time.temporal.ChronoUnit.MINUTES;

public class LessonGenerator {
    public Schedule generateSchedule(LessonParameters lessonParameters) {
        long completeMinutes = 0;
        long lessonLength = MINUTES.between(lessonParameters.getBeginTime(), lessonParameters.getEndTime());
        long requiredMinutes = lessonParameters.getNumberOfHour() * 60;
        LocalDate currentDate = lessonParameters.getStartDate();
        Set<DayOfWeek> lessonDays = lessonParameters.getLessonDays();
        List<Lesson> lessons = new ArrayList<>();
        do {
            while (!lessonDays.contains(currentDate.getDayOfWeek())) {
                currentDate = currentDate.plusDays(1);
            }
            Lesson lesson = new Lesson(currentDate, lessonParameters.getBeginTime(), lessonParameters.getEndTime());
            lessons.add(lesson);
            completeMinutes = completeMinutes + lessonLength;
        } while (requiredMinutes > completeMinutes);

        return new Schedule(lessons, true);
    }
}
