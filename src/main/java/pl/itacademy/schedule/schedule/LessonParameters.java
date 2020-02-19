package pl.itacademy.schedule.schedule;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;

public class LessonParameters {
    private LocalTime beginTime;
    private LocalTime endTime;
    private int numberOfHour;
    private String fileName;
    private boolean showHelp;
    private Set<DayOfWeek> lessonDays;
    private LocalDate startDate;

    private LessonParameters(LocalTime beginTime, LocalTime endTime, int numberOfHour, String fileName, boolean showHelp, Set<DayOfWeek> lessonDays, LocalDate startDate) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.numberOfHour = numberOfHour;
        this.fileName = fileName;
        this.showHelp = showHelp;
        this.lessonDays = lessonDays;
        this.startDate = startDate;
    }

    public LocalTime getBeginTime() {
        return beginTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getNumberOfHour() {
        return numberOfHour;
    }

    public String getFileName() {
        return fileName;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public Set<DayOfWeek> getLessonDays() {
        return lessonDays;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonParameters that = (LessonParameters) o;
        return numberOfHour == that.numberOfHour &&
                showHelp == that.showHelp &&
                Objects.equals(beginTime, that.beginTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(fileName, that.fileName) &&
                Objects.equals(lessonDays, that.lessonDays) &&
                Objects.equals(startDate, that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beginTime, endTime, numberOfHour, fileName, showHelp, lessonDays, startDate);
    }

    @Override
    public String toString() {
        return "LessonParameter{" +
                "beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", numberOfHour=" + numberOfHour +
                ", fileName='" + fileName + '\'' +
                ", showHelp=" + showHelp +
                ", lessonDays=" + lessonDays +
                ", startDate=" + startDate +
                '}';
    }

    public static class Builder {
        private LocalTime beginTime;
        private LocalTime endTime;
        private int numberOfHour;
        private String fileName;
        private boolean showHelp;
        private Set<DayOfWeek> lessonDays;
        private LocalDate startDate;

        public Builder(LocalTime beginTime, LocalTime endTime, int numberOfHour, Set<DayOfWeek> lessonDays, LocalDate startDate) {
            this.beginTime = beginTime;
            this.endTime = endTime;
            this.numberOfHour = numberOfHour;
            this.lessonDays = lessonDays;
            this.startDate = startDate;
        }

        public Builder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public Builder showHelp(boolean showHelp) {
            this.showHelp = showHelp;
            return this;
        }

        public LessonParameters build() {
            return new LessonParameters(beginTime, endTime, numberOfHour, fileName, showHelp, lessonDays, startDate);
        }
    }
}
