package dukelab.js8ftri.ch5;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

public class Exercise01 {

    @Test
    public void test() {
        LocalDate expected = LocalDate.of(2014, 9, 13);
        LocalDate actual = LocalDate.ofYearDay(2014, 256);

        Assert.assertEquals(expected, actual);
    }

}
