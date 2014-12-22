package dukelab.js8ftri.ch3;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

import org.junit.Assert;
import org.junit.Test;

public class Exercises21 {

    @Test
    public void test() throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newCachedThreadPool();
        Future<String> future = es.submit(() -> "Hello?");
        Future<String> retFuture = map(future, t -> t + ", Duke");
        String actual = retFuture.get();
        String expected = "Hello?, Duke";

        Assert.assertEquals(expected, actual);
    }

    public static <T, U> Future<U> map(Future<T> future, Function <T, U> mapper) {
        return new Future<U>() {

            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return future.cancel(mayInterruptIfRunning);
            }

            @Override
            public boolean isCancelled() {
                return future.isCancelled();
            }

            @Override
            public boolean isDone() {
                return future.isDone();
            }

            @Override
            public U get() throws InterruptedException, ExecutionException {
                T t = future.get();
                U ret = mapper.apply(t);
                return ret;
            }

            @Override
            public U get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                T t = future.get(timeout, unit);
                U ret = mapper.apply(t);
                return ret;
            }

        };
    }

}
