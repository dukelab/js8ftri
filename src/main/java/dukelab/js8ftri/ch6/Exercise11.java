package dukelab.js8ftri.ch6;

import java.net.PasswordAuthentication;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;



public class Exercise11 {

    @SuppressWarnings("resource")
    @Test
    public void test() throws InterruptedException, ExecutionException {
        repeat(
            () -> {
                // It's ok not to close System.in.
                Scanner scanner = new Scanner(System.in);
                System.out.print("userName:");
                String userName = scanner.nextLine();

                System.out.print("password:");
                String password = scanner.nextLine();
                return new PasswordAuthentication(userName, password.toCharArray());
            },
            pa -> {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
                return "secret".equals(new String(pa.getPassword()));
            }
        ).thenAccept(pa -> System.out.println(pa.getUserName() + " logged in.")).get();
    }

    public static <T> CompletableFuture <T> repeat(Supplier<T> action, Predicate<T> until) {
        return CompletableFuture.supplyAsync(action)
            .thenApplyAsync(t -> {
                if (until.test(t)) {
                    return t;
                }
                return repeat(action, until).join();
            });
    }

}
