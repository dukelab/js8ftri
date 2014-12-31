package dukelab.js8ftri.ch5;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.junit.Assert;
import org.junit.Test;


public class Exercise05 {

    @Test
    public void test() {
        // is not my birthday... fake
        LocalDate myBirthday = LocalDate.of(1999, 12, 31);
        long actual = myBirthday.until(LocalDate.of(2014, 12, 31), ChronoUnit.DAYS);

        // 2000, 2004, 2008, 2012 are leap years.
        long expected = (365 * 11) + (366 * 4);

        Assert.assertEquals(expected, actual);
    }

}
