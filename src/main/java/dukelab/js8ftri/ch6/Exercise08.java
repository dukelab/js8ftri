package dukelab.js8ftri.ch6;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;


public class Exercise08 {

    private static final int LENGTH_OF_ARRAY = 500_000;

    @Test
    public void test() {
        int[] src = new int[LENGTH_OF_ARRAY];
        IntStream.range(0, LENGTH_OF_ARRAY)
            .forEach(i -> src[i] = RandomUtils.nextInt(0, 1000000));

        elapse(src, a -> Arrays.sort(a));
        elapse(src, a -> Arrays.parallelSort(a));

        elapse(src, a -> Arrays.sort(a));
        elapse(src, a -> Arrays.parallelSort(a));

        elapse(src, a -> Arrays.sort(a));
        elapse(src, a -> Arrays.parallelSort(a));
    }

    private void elapse(int[] src, Consumer<int[]> con) {
        int[] a = Arrays.copyOf(src, src.length);
        StopWatch sw = new StopWatch();
        sw.start();

        con.accept(a);

        sw.stop();
        System.out.println(sw.getTime() + "ms");
    }

}
