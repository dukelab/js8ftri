package dukelab.js8ftri.ch3;

import java.io.FileInputStream;
import java.util.function.Function;

public class Exercise18 {

    public static void main(String[] args) {
        System.out.println(unchecked(t -> t).apply("OK"));
        System.out.println(unchecked(t -> new FileInputStream("..")).apply("fff"));
    }

    public static <T, U> Function<T, U> unchecked(ThrowableFunction<T, U> f) {
        return (t) -> {
            try {
                return f.apply(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } catch (Throwable th) {
                throw th;
            }
        };
    }

    @FunctionalInterface
    public static interface ThrowableFunction<T, U> {
        U apply(T t) throws Exception;
    }

}
