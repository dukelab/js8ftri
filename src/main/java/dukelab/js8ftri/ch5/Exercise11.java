package dukelab.js8ftri.ch5;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Assert;
import org.junit.Test;

public class Exercise11 {

    @Test
    public void test() {
        Duration expected = Duration.ofHours(11).plusMinutes(35);
        Duration actual = calculateDuration(
            ZoneId.of("Europe/Paris"),
            LocalTime.of(14, 5),
            ZoneId.of("America/Los_Angeles"),
            LocalTime.of(16, 40)
        );

        Assert.assertEquals(expected, actual);
    }

    private static Duration calculateDuration(ZoneId departureZone, LocalTime departureTime,
                                              ZoneId arrivalZone, LocalTime arrivalTime) {
        LocalDate now = LocalDate.now();
        Instant departureInstant = ZonedDateTime.of(
            now,
            departureTime,
            departureZone
        ).toInstant();
        Instant arrivalInstant = ZonedDateTime.of(
            now,
            arrivalTime,
            arrivalZone
        ).toInstant();
        return Duration.between(departureInstant, arrivalInstant);
    }

}
