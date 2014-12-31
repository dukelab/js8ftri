package dukelab.js8ftri.ch5;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

public class Exercise09 {

    @Test
    public void test() {
        Instant now = Instant.now();
        List<ZoneOffset> actual = ZoneId.getAvailableZoneIds().stream()
            .map(id -> now.atZone(ZoneId.of(id)).getOffset())
            .filter(z -> z.getTotalSeconds() % 3600 != 0)
            .distinct()
            .sorted()
            .collect(Collectors.toList());
        List<ZoneOffset> expected = Arrays.asList(
            ZoneOffset.ofHoursMinutes(13, 45),
            ZoneOffset.ofHoursMinutes(11, 30),
            ZoneOffset.ofHoursMinutes(10, 30),
            ZoneOffset.ofHoursMinutes(9, 30),
            ZoneOffset.ofHoursMinutes(8, 45),
            ZoneOffset.ofHoursMinutes(06, 30),
            ZoneOffset.ofHoursMinutes(05, 45),
            ZoneOffset.ofHoursMinutes(05, 30),
            ZoneOffset.ofHoursMinutes(04, 30),
            ZoneOffset.ofHoursMinutes(03, 30),
            ZoneOffset.ofHoursMinutes(-3, -30),
            ZoneOffset.ofHoursMinutes(-4, -30),
            ZoneOffset.ofHoursMinutes(-9, -30)
        );

        Assert.assertEquals(expected, actual);
    }

}
