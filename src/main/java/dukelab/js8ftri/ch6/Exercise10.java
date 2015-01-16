package dukelab.js8ftri.ch6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.junit.Test;


public class Exercise10 {

    @Test
    public void test() {
        CompletableFuture.supplyAsync(() -> JOptionPane.showInputDialog("Input URL"))
            .thenApply(url -> {
                List<String> links = new ArrayList<>();
                Pattern p = Pattern.compile("<a href=\"([^\"]+)\">", Pattern.DOTALL);
                HttpURLConnection hconn = null;
                try {
                    hconn = (HttpURLConnection) new URL(url).openConnection();
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(hconn.getInputStream()))) {
                        while (true) {
                            String line = br.readLine();
                            if (line == null) {
                                break;
                            }
                            Matcher matcher = p.matcher(line);
                            if (matcher.find()) {
                                links.add(matcher.group(1));
                            }
                        }
                    }
                    hconn.setDefaultUseCaches(false);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    hconn.disconnect();
                }
                return links;
            })
            .thenAccept(links -> System.out.println(links));
        ForkJoinPool.commonPool(). awaitQuiescence( 10, TimeUnit.SECONDS);
    }

}
