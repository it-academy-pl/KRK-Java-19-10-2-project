package pl.itacademy.schedule;

import java.util.List;
import java.util.Objects;

public class Schedule {
    private List<Lesson> lessons;
    private boolean successful;

    public Schedule(List<Lesson> lessons, boolean successful) {
        this.lessons = lessons;
        this.successful = successful;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return successful == schedule.successful &&
                Objects.equals(lessons, schedule.lessons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lessons, successful);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "lessons=" + lessons +
                ", successful=" + successful +
                '}';
    }
}
