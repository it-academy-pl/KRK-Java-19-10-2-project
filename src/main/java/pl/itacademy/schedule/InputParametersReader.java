package pl.itacademy.schedule;

import org.apache.commons.cli.*;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

public class InputParametersReader {
    public LessonParameters readParameters(String[] args) throws ParseException {
        Options options = new Options();

        options.addOption("n", true, "number of required hours");
        options.addOption("f", true, "file name");
        options.addOption("h", false, "show help");
        options.addOption("d", true, "lesson days");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        int numberOfHours = 0;
        if (cmd.hasOption("n")) {
            String value = cmd.getOptionValue("n");
            numberOfHours = Integer.parseInt(value);
        }
        String fileName = "";
        if (cmd.hasOption("f")) {
            fileName = cmd.getOptionValue("f");
        }

        boolean showHelp = cmd.hasOption("h");
        Set<DayOfWeek> lessonDays = new HashSet<>();
        if (cmd.hasOption("d")) {
            String value = cmd.getOptionValue("d");

        }

        return new LessonParameters.Builder(null, null, numberOfHours, Set.of(), null).fileName(fileName).showHelp(showHelp).build();

    }

}
