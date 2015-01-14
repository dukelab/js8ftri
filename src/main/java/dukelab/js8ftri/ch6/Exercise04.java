package dukelab.js8ftri.ch6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

public class Exercise04 {

    @Test
    public void test() throws InterruptedException {
        _test("max", Math::max, 0);
        _test("min", Math::min, 100);
    }

    private void _test(String label, LongBinaryOperator accumulatorFunction, long initialValue) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        LongAccumulator la = new LongAccumulator(accumulatorFunction, initialValue);
        List<CompletableFuture<?>> list = new ArrayList<>();
        IntStream.range(0, 10)
            .forEach(i -> {
                CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
                    Stream.generate(Exercise04::nextLong)
                        .limit(100)
                        .forEach(v -> la.accumulate(v));
                }, pool);
                list.add(cf);
            });
        CompletableFuture.allOf(list.toArray(new CompletableFuture<?>[0]))
            .thenAccept((v) -> {
                System.out.println(label + " : " + la.get());
            });
        pool.awaitTermination(1, TimeUnit.SECONDS);
    }

    private static long nextLong() {
        return RandomUtils.nextLong(50, 90);
    }

}
