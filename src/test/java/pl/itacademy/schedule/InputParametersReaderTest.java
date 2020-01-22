package pl.itacademy.schedule;

import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

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
}