package pl.itacademy.schedule.holiday;

import com.jayway.jsonpath.JsonPath;
import pl.itacademy.schedule.util.PropertiesReader;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CalendarificHolidaysChecker implements HolidaysChecker {
    private final PropertiesReader propertiesReader = PropertiesReader.getInstance();

    @Override
    public List<LocalDate> getHolidays(int year) {
        try {
            Thread.sleep(1100);
        } catch (InterruptedException ignored) {
            //ignored
        }

        URI uri = UriBuilder.fromPath(propertiesReader.readProperty("calendarific.path"))
                .queryParam("api_key", propertiesReader.readProperty("calendarific.api.key"))
                .queryParam("country", propertiesReader.readProperty("calendarific.country.code"))
                .queryParam("year", year)
                .queryParam("type", "calendarific.holiday.type")
                .build();

        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(uri);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        String jsonResponse = invocationBuilder.get(String.class);

        String jsonPath = propertiesReader.readProperty("calendarific.json.path");

        List<String> dateTexts = JsonPath.read(jsonResponse, jsonPath);
        List<LocalDate> holidays = new ArrayList<>();
        for (String date : dateTexts) {
            holidays.add(LocalDate.parse(date, DateTimeFormatter.ISO_DATE));
        }
        return holidays;
    }
}
