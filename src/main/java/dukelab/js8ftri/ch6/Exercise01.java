package dukelab.js8ftri.ch6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAccumulator;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

public class Exercise01 {

    @Test
    public void test() throws InterruptedException {
        AtomicReference<String> longest = new AtomicReference<>();
        LongAccumulator accumulator = new LongAccumulator(Math::max, 0);
        prepareWords().parallelStream()
            .forEach(observed -> {
                System.out.println("Current working thread : " + Thread.currentThread() + ", observed : " + observed);
                longest.updateAndGet(current -> {
                    String result = observed.length() > accumulator.intValue() ? observed : current;
                    accumulator.accumulate(observed.length());
                    return result;
                });
            });

        System.out.println("LongestWord : " + longest.get());
    }

    private List<String> prepareWords() {
        List<String> words = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            words.add(RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(5, 50)));
        }
        return words;
    }

}
