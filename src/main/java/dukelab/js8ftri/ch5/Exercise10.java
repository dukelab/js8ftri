package dukelab.js8ftri.ch5;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Assert;
import org.junit.Test;

public class Exercise10 {

    @Test
    public void test() {
        LocalTime expected = LocalTime.of(10, 55);
        LocalTime actual = calculateArrivalTime(
            ZoneId.of("America/Los_Angeles"),
            LocalTime.of(15, 5),
            ZoneId.of("Europe/Paris"),
            Duration.ofHours(10).plusMinutes(50)
        );

        Assert.assertEquals(expected, actual);
    }

    private static LocalTime calculateArrivalTime(ZoneId origin, LocalTime departureTime,
                                                  ZoneId destination, Duration duration) {
        ZonedDateTime depatureZdt = ZonedDateTime.of(
            LocalDate.now(),
            departureTime,
            origin
        );
        return depatureZdt.plus(duration).withZoneSameInstant(destination).toLocalTime();
    }

}
