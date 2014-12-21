package dukelab.js8ftri.ch3;

import java.util.function.Consumer;

public class Exercises17 {

    public static void main(String[] args) {
        doInParallelAsync(
            () -> {
                for (int i = 0; i < 100; i++) {
                    System.out.println("First" + i);
                }
            },
            () -> {
                for (int i = 0; i < 100; i++) {
                    System.out.println("Second" + i);
                    if (i == 50) {
                        throw new IllegalArgumentException("We can't accept " + i + ".");
                    }
                }
            },
            t -> {
                System.out.println("Error! : " + t.getMessage());
            }
        );
    }

    public static void doInParallelAsync(Runnable first, Runnable second, Consumer<Throwable> handler) {
        for (Runnable r : new Runnable[] { first, second }) {
            new Thread() {
                public void run() {
                    try {
                        r.run();
                    } catch (Throwable t) {
                        handler.accept(t);
                    }
                };
            }.start();
        }
    }

}
