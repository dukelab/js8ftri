package dukelab.js8ftri.ch3;

import java.util.concurrent.locks.ReentrantLock;

//
public class Exercises2 {

    public static void main(String[] args) {
        withLock(new ReentrantLock(), () -> { System.out.println("Hi, baby"); });
    }

    private static void withLock(ReentrantLock lock, Runnable r) {
        lock.lock();
        try {
            r.run();
        } finally {
            lock.unlock();
        }
    }

}
