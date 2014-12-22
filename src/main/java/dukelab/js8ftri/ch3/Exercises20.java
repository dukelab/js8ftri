package dukelab.js8ftri.ch3;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

public class Exercises20 {

    @Test
    public void test() {
        List<String> expected = Arrays.asList("a", "b", "c");
        List<String> actual = map(Arrays.asList("A", "B", "C"), String::toLowerCase);

        Assert.assertEquals(expected, actual);
    }

    public static <T, U> List<U> map(List<T> list, Function<T, U> func) {
        return list.stream().map(func).collect(Collectors.toList());
    }

}
