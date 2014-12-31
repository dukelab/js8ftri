package dukelab.js8ftri.ch5;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import org.junit.Assert;
import org.junit.Test;


public class Exercise04 {

    /**
     * JUnit version
     */
    @Test
    public void test() {
        String lineSeparator = System.getProperty("line.separator");
        String expected =
            " 1  2  3  4  5  6  7" + lineSeparator +
            " 8  9 10 11 12 13 14" + lineSeparator +
            "15 16 17 18 19 20 21" + lineSeparator +
            "22 23 24 25 26 27 28" + lineSeparator +
            "29 30 31";
        String actual = createCalendar(12, 2014);

        Assert.assertEquals(expected, actual);
    }

    /**
     * Stand alone version
     */
    public static void main(String[] args) {
        int month = Integer.parseInt(args[0]);
        int year = Integer.parseInt(args[1]);
        System.out.printf(" %d %d Calendar \n", month, year);
        System.out.println(createCalendar(month, year));
    }

    public static String createCalendar(int month, int year) {
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            printCalendar(pw, LocalDate.of(year, month, 1));
        }
        return sw.toString();
    }

    private static void printCalendar(PrintWriter pw, LocalDate date) {
        int dayOfMonth = date.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
        for (int i = 0; i < dayOfMonth; i++) {
            if (i == 0) {
                addIndents(pw, date);
            }
            pw.print(String.format("%2d", date.getDayOfMonth()));
            if (i != dayOfMonth - 1) {
                if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    pw.println();
                } else {
                    pw.print(" ");
                }
            }
            date = date.plusDays(1);
        }
    }

    private static void addIndents(PrintWriter pw, LocalDate date) {
        int countOfIndents = date.getDayOfWeek().getValue() - 1;
        for (int i = 0; i < countOfIndents; i++) {
            pw.print("   ");
        }
    }

}
