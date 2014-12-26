package dukelab.js8ftri.ch5;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.function.Predicate;

import org.junit.Assert;
import org.junit.Test;

public class Exercise3 {

    @Test
    public void test() {
        LocalDate actual = LocalDate.of(2014, 12, 26).plusWeeks(1).with(next(w -> w.getDayOfWeek().getValue() < 6));
        LocalDate expected = LocalDate.of(2015, 1, 5);

        Assert.assertEquals(expected, actual);
    }

    private static TemporalAdjuster next(Predicate<LocalDate> p) {
        return TemporalAdjusters.ofDateAdjuster(d -> {
            LocalDate next = d.plusDays(1);
            while (!p.test(next)) {
                next = next.plusDays(1);
            }
            return next;
        });
    }

}
