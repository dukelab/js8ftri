package dukelab.js8ftri.ch5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;


public class Exercise4 {

    public static void main(String[] args) {
        createCalendar("12", "2014");
    }

    public static void createCalendar(String strMonth, String strYear) {
        int month = Integer.parseInt(strMonth);
        int year = Integer.parseInt(strYear);

        LocalDate date = LocalDate.of(year, month, 1);
        int dayNumber = date.getDayOfWeek().getValue();
        for (int i = 0; i < dayNumber; i++) {
            System.out.print("   ");
        }
        for (int i = 0, len = DayOfWeek.SUNDAY.getValue() - dayNumber; i < len; i++) {
            System.out.printf("%2d ", date.getDayOfMonth());
            date = date.plusDays(1);
        }
        System.out.println();

        for (int i = 0, len = date.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth() - date.getDayOfMonth(); i <= len; i++) {
            System.out.printf("%2d ", date.getDayOfMonth());
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
                System.out.println();
            }
            date = date.plusDays(1);
        }
    }

}
