package dukelab.js8ftri.ch5;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class Exercise12 {

    private final LocalTime localTime;
    private final ZoneId localZone;
    private final LocalTime appointmentTime;
    private final ZoneId appointmentZone;
    private final boolean expected;

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
       return Arrays.asList(new Object[][] {
           { LocalTime.of(14, 30), ZoneId.of("Asia/Tokyo"), LocalTime.of(1, 00), ZoneId.of("America/New_York"), true },
           { LocalTime.of(9, 50), ZoneId.of("Europe/Paris"), LocalTime.of(1, 40), ZoneId.of("America/Los_Angeles"), true },
           { LocalTime.of(16, 00), ZoneId.of("Asia/Tokyo"), LocalTime.of(0, 00), ZoneId.of("America/New_York"), false },
           { LocalTime.of(9, 50), ZoneId.of("Europe/Paris"), LocalTime.of(0, 40), ZoneId.of("America/Los_Angeles"), false },
           { LocalTime.of(9, 50), ZoneId.of("Europe/Paris"), LocalTime.of(2, 40), ZoneId.of("America/Los_Angeles"), false }
       });
    };

    public Exercise12(LocalTime localTime, ZoneId localZone, LocalTime appointmentTime, ZoneId appointmentZone,
                      boolean expected) {
        this.localTime = localTime;
        this.localZone = localZone;
        this.appointmentTime = appointmentTime;
        this.appointmentZone = appointmentZone;
        this.expected = expected;
    }

    @Test
    public void test() {
        Assert.assertEquals(expected, alert1());
        Assert.assertEquals(expected, alert2());
    }

    boolean alert1() {
        LocalDate date = LocalDate.now();
        ZonedDateTime appointment = ZonedDateTime.of(
            date,
            appointmentTime,
            appointmentZone
        ).toInstant().atZone(localZone);
        ZonedDateTime local = ZonedDateTime.of(date, localTime, localZone);
        // System.out.println(local + ", " + appointment);
        return local.getHour() + 1 == appointment.getHour();
    }

    // https://github.com/galperin/Solutions-for-exercises-from-Java-SE-8-for-the-Really-Impatient-by-Horstmann/blob/master/src/main/java/de/galperin/javase8/capitel5/C5E12.java
    boolean alert2() {
        ZonedDateTime appointment = ZonedDateTime.of(
            LocalDate.now(),
            appointmentTime,
            appointmentZone
        );
        long duration = Duration.between(
                ZonedDateTime.of(LocalDate.now(), localTime, localZone).toInstant(),
                appointment.withZoneSameInstant(localZone).toInstant())
                .toMinutes();
        return 0 < duration && duration < 60;
    }

}
