package dukelab.js8ftri.ch6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Assert;
import org.junit.Test;

public class Exercise03 {

    @Test
    public void test1() {
        System.out.println("elapse1");
        IntStream.range(0, 5)
            .forEach(i -> {
                AtomicLong atomicLong = new AtomicLong();
                LongAdder longAdder = new LongAdder();
                System.out.println("AtomicLong : " + elapse1(atomicLong::incrementAndGet, atomicLong::get) + "ms");
                System.out.println("LongAdder : " + elapse1(longAdder::increment, longAdder::sum) + "ms");
            });

        System.out.println("elapse2");
        IntStream.range(0, 5)
            .forEach(i -> {
                AtomicLong atomicLong = new AtomicLong();
                LongAdder longAdder = new LongAdder();
                System.out.println("AtomicLong : " + elapse2(atomicLong::incrementAndGet, atomicLong::get) + "ms");
                System.out.println("LongAdder : " + elapse2(longAdder::increment, longAdder::sum) + "ms");
            });
    }

    private long elapse1(Runnable process, Supplier<Long> supplier) {
        ExecutorService pool = Executors.newFixedThreadPool(1_000);
        StopWatch sw = new StopWatch();
        sw.start();
        CountDownLatch cdl = new CountDownLatch(1_000);
        for (int i = 0; i < 1_000; i++) {
            pool.submit(() -> {
                for (int j = 0; j < 100_000; j++) {
                    process.run();
                }
                cdl.countDown();
            });
        }
        try {
            cdl.await();
        } catch (InterruptedException e) {
        }
        long actual = supplier.get();
        Assert.assertEquals(100_000_000, actual);
        sw.stop();
        pool.shutdown();
        return sw.getTime();
    }

    private long elapse2(Runnable process, Supplier<Long> supplier) {
        ExecutorService pool = Executors.newFixedThreadPool(1000);
        StopWatch sw = new StopWatch();
        sw.start();
        List<CompletableFuture<?>> list = new ArrayList<>();
        IntStream.range(0, 1_000).forEach(i -> {
            list.add(CompletableFuture.runAsync(() -> {
                IntStream.range(0, 100_000)
                    .forEach(j -> process.run());
            }, pool));
        });
        CompletableFuture.allOf(list.toArray(new CompletableFuture<?>[0]))
            .thenAccept((v) -> {
                long actual = supplier.get();
                Assert.assertEquals(100_000_000, actual);
            });
        sw.stop();
        return sw.getTime();
    }

}
