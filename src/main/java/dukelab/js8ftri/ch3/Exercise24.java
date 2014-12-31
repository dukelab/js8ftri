package dukelab.js8ftri.ch3;

import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;

import org.junit.Assert;
import org.junit.Test;

public class Exercise24 {

    public static class Pair<T> {
        private final T first;
        private final T second;
        public Pair(T first, T second) {
            this.first = first;
            this.second = second;
        }

        public <U> Pair<U> flatMap(BiFunction<? super T, ? super T, Pair<U>> mapper) {
            return mapper.apply(first, second);
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((first == null) ? 0 : first.hashCode());
            result = prime * result
                    + ((second == null) ? 0 : second.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Pair<?> other = (Pair<?>) obj;
            if (first == null) {
                if (other.first != null)
                    return false;
            } else if (!first.equals(other.first))
                return false;
            if (second == null) {
                if (other.second != null)
                    return false;
            } else if (!second.equals(other.second))
                return false;
            return true;
        }
    }

    @Test
    public void test() throws InterruptedException, ExecutionException {
        Pair<String> pair = new Pair<>("abc", "ef");
        Pair<Integer> actual = pair.flatMap((f, s) -> new Pair<>(f.length(), s.length()));
        Pair<Integer> expected = new Pair<>(3, 2);

        Assert.assertEquals(expected, actual);
    }

}
