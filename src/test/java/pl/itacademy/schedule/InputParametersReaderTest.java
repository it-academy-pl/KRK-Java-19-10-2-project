package pl.itacademy.schedule;

import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InputParametersReaderTest {

    private InputParametersReader parametersReader;

    @BeforeEach
    public void setUp() {
        parametersReader = new InputParametersReader();
    }

    @Test
    public void readParameters_containsNReturnsLessonParametersWithRequiredNumberOfHours() throws ParseException {
        String[] args = {"-n", "42"};
        LessonParameters result = parametersReader.readParameters(args);
        assertThat(result.getNumberOfHour(), equalTo(42));
    }

    @Test
    public void readParameters_containsFReturnsLessonParametersWithFileName() throws ParseException {
        String[] args = {"-f", "file_name"};
        LessonParameters result = parametersReader.readParameters(args);
        assertThat(result.getFileName(), equalTo("file_name"));
    }

    @Test
    public void readParameters_containsHReturnsLessonParametersWithHelp() throws ParseException {
        String[] args = {"-h"};
        LessonParameters result = parametersReader.readParameters(args);
        assertThat(result.isShowHelp(), equalTo(true));
    }

    @Test
    public void readParameters_containsDReturnsLessonParametersWithLessonDays() throws ParseException {
        String[] args = {"-d", "MONDAY_WEDNESDAY"};
        LessonParameters result = parametersReader.readParameters(args);
        assertThat(result.getLessonDays(), containsInAnyOrder(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY));
    }

    @Test
    public void readParameters_containsBReturnsLessonParametersWithLessonBeginTime() throws ParseException {
        String[] args = {"-b", "17:00"};
        LessonParameters result = parametersReader.readParameters(args);
        assertThat(result.getBeginTime(), equalTo(LocalTime.of(17, 0)));
    }

    @Test
    public void readParameters_containsEReturnsLessonParametersWithLessonEndTime() throws ParseException {
        String[] args = {"-e", "18:30"};
        LessonParameters result = parametersReader.readParameters(args);
        assertThat(result.getEndTime(), equalTo(LocalTime.of(18, 30)));
    }

    @Test
    public void readParameters_containsSReturnsLessonParametersWithStartDate() throws ParseException {
        String[] args = {"-s", "01.02.2020"};
        LessonParameters result = parametersReader.readParameters(args);
        assertEquals(result.getStartDate(), LocalDate.of(2020, 2, 1));
    }

}