package dukelab.js8ftri.ch5;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class Exercise7 {

    public static class TimeInterval {
        private final LocalTime from;
        private final LocalTime to;

        public TimeInterval(LocalTime from, LocalTime to) {
            this.from = from;
            this.to = to;
        }

        public boolean isOverlapped(TimeInterval other) {
            if (from.compareTo(other.to) > 0 ||
                    to.compareTo(other.from) < 0) {
                return false;
            }
            if (from.compareTo(other.from) <= 0 &&
                    to.compareTo(other.to) >= 0) {
                return true;
            }
            if (from.compareTo(other.from) >= 0 &&
                    to.compareTo(other.to) <= 0) {
                return true;
            }
            return false;
        }
    }

    private TimeInterval[] tis;
    private boolean expected;

    public Exercise7(TimeInterval[] tis, boolean expected) {
        this.tis = tis;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> primeNumbers() {
       return Arrays.asList(new Object[][] {
          {
              new TimeInterval[] {
                  new TimeInterval(LocalTime.of(10, 00), LocalTime.of(11, 00)),
                  new TimeInterval(LocalTime.of(9, 00), LocalTime.of(12, 00))
              }, true
          },
          {
              new TimeInterval[] {
                  new TimeInterval(LocalTime.of(9, 00), LocalTime.of(12, 00)),
                  new TimeInterval(LocalTime.of(10, 00), LocalTime.of(11, 00))
              }, true
          },
          {
              new TimeInterval[] {
                  new TimeInterval(LocalTime.of(10, 00), LocalTime.of(13, 00)),
                  new TimeInterval(LocalTime.of(10, 00), LocalTime.of(13, 00))
              }, true
          },
          {
              new TimeInterval[] {
                  new TimeInterval(LocalTime.of(12, 00), LocalTime.of(13, 00)),
                  new TimeInterval(LocalTime.of(10, 00), LocalTime.of(11, 00))
              }, false
          },
          {
              new TimeInterval[] {
                  new TimeInterval(LocalTime.of(12, 00), LocalTime.of(13, 00)),
                  new TimeInterval(LocalTime.of(10, 00), LocalTime.of(11, 00))
              }, false
          }
       });
    }

    @Test
    public void test1() {
        boolean actual = tis[0].isOverlapped(tis[1]);

        Assert.assertEquals(expected, actual);
    }

}
