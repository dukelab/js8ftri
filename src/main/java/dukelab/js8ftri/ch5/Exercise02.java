package dukelab.js8ftri.ch5;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

public class Exercise02 {

    @Test
    public void testOneYear() {
        LocalDate expected = LocalDate.of(2001, 2, 28);
        LocalDate actual = LocalDate.of(2000, 2, 29).plusYears(1);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFourYears() {
        LocalDate expected = LocalDate.of(2004, 2, 29);
        LocalDate actual = LocalDate.of(2000, 2, 29).plusYears(4);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFourTimesOneYear() {
        LocalDate expected = LocalDate.of(2004, 2, 28);
        LocalDate actual = LocalDate.of(2000, 2, 29).plusYears(1).plusYears(1).plusYears(1).plusYears(1);

        Assert.assertEquals(expected, actual);
    }

}
