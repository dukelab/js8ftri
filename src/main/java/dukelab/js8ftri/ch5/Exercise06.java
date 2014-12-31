package dukelab.js8ftri.ch5;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.junit.Test;

public class Exercise06 {

    @Test
    public void test() {
        LocalDate next13Friday = LocalDate.of(1900, 1, 13);
        LocalDate to = LocalDate.of(1999, 12, 31);
        while (!next13Friday.isAfter(to)) {
            if (next13Friday.getDayOfWeek() == DayOfWeek.FRIDAY) {
                System.out.println(next13Friday);
            }
            next13Friday = next13Friday.plusMonths(1);
        }
    }

}
