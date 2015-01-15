package dukelab.js8ftri.ch6;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Assert;
import org.junit.Test;


public class Exercise07 {

    @Test
    public void test() {
        long expected = 5L;
        ConcurrentHashMap <String, Long> map = new ConcurrentHashMap<>();
        map.put("key1", 1L);
        map.put("key2", 2L);
        map.put("key3", 3L);
        map.put("key4", 4L);
        map.put("key5", expected);

        int availableProcessors = Runtime.getRuntime().availableProcessors();
        Entry<String, Long> result = map.reduceEntries(availableProcessors, (x, y) -> x.getValue() > y.getValue() ? x : y);
        long actual = result.getValue();

        Assert.assertEquals(expected, actual);
    }

}
