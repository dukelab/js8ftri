package dukelab.js8ftri.ch5;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

public class Exercise08 {

    @Test
    public void test() {
        Instant now = Instant.now();
        List<ZoneOffset> actual = ZoneId.getAvailableZoneIds().stream()
            .map(id -> now.atZone(ZoneId.of(id)).getOffset())
            .distinct()
            .sorted()
            .collect(Collectors.toList());
        List<ZoneOffset> expected = Arrays.asList(
            ZoneOffset.ofHoursMinutes(14, 00),
            ZoneOffset.ofHoursMinutes(13, 45),
            ZoneOffset.ofHoursMinutes(13, 00),
            ZoneOffset.ofHoursMinutes(12, 00),
            ZoneOffset.ofHoursMinutes(11, 30),
            ZoneOffset.ofHoursMinutes(11, 00),
            ZoneOffset.ofHoursMinutes(10, 30),
            ZoneOffset.ofHoursMinutes(10, 00),
            ZoneOffset.ofHoursMinutes(9, 30),
            ZoneOffset.ofHoursMinutes(9, 00),
            ZoneOffset.ofHoursMinutes(8, 45),
            ZoneOffset.ofHoursMinutes(8, 00),
            ZoneOffset.ofHoursMinutes(7, 00),
            ZoneOffset.ofHoursMinutes(06, 30),
            ZoneOffset.ofHoursMinutes(06, 00),
            ZoneOffset.ofHoursMinutes(05, 45),
            ZoneOffset.ofHoursMinutes(05, 30),
            ZoneOffset.ofHoursMinutes(05, 00),
            ZoneOffset.ofHoursMinutes(04, 30),
            ZoneOffset.ofHoursMinutes(04, 00),
            ZoneOffset.ofHoursMinutes(03, 30),
            ZoneOffset.ofHoursMinutes(03, 00),
            ZoneOffset.ofHoursMinutes(02, 00),
            ZoneOffset.ofHoursMinutes(01, 00),
            ZoneOffset.ofHoursMinutes(0, 0),
            ZoneOffset.ofHoursMinutes(-1, -00),
            ZoneOffset.ofHoursMinutes(-2, -00),
            ZoneOffset.ofHoursMinutes(-3, -00),
            ZoneOffset.ofHoursMinutes(-3, -30),
            ZoneOffset.ofHoursMinutes(-4, -00),
            ZoneOffset.ofHoursMinutes(-4, -30),
            ZoneOffset.ofHoursMinutes(-5, -00),
            ZoneOffset.ofHoursMinutes(-6, -00),
            ZoneOffset.ofHoursMinutes(-7, -00),
            ZoneOffset.ofHoursMinutes(-8, -00),
            ZoneOffset.ofHoursMinutes(-9, -00),
            ZoneOffset.ofHoursMinutes(-9, -30),
            ZoneOffset.ofHoursMinutes(-10, -00),
            ZoneOffset.ofHoursMinutes(-11, -00),
            ZoneOffset.ofHoursMinutes(-12, -00)
        );

        Assert.assertEquals(expected, actual);
    }

}
