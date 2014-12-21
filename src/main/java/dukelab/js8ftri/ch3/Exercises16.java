package dukelab.js8ftri.ch3;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class Exercises16 {

    public static void main(String[] args) {
        doInOrderAsync(() -> "hello", (s, t) -> {
            if (s != null) {
                System.out.println("Result : " + s);
                return;
            }
            System.out.println("error! : " + t.getMessage());
        });
        doInOrderAsync(() -> Integer.parseInt("hello"), (s, t) -> {
            if (s != null) {
                System.out.println("Result : " + s);
                return;
            }
            System.out.println("error! : " + t.getMessage());
        });
    }

    public static <T> void doInOrderAsync(Supplier<T> first, BiConsumer<T, Throwable> second) {
        Thread t = new Thread() {
            public void run() {
                try {
                    T t = first.get();
                    second.accept(t, null);
                } catch (Throwable e) {
                    second.accept(null, e);
                }
            };
        };
        t.start();
    }

}
