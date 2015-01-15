package dukelab.js8ftri.ch6;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.junit.Test;



public class Exercise06 {

    @Test
    public void test() throws InterruptedException {
        ConcurrentMap<String, Set<File>> map = new ConcurrentHashMap<>();
        Arrays.asList(
            new File(this.getClass().getResource("file1.txt").getFile()),
            new File(this.getClass().getResource("file2.txt").getFile()),
            new File(this.getClass().getResource("file3.txt").getFile())
        ).parallelStream()
        .forEach(f -> {
            try {
                Files.readAllLines(f.toPath())
                    .stream()
                    .forEach(w -> {
                        // merge creates always new object, but computeIfAbsent creates only one time.
                        map.computeIfAbsent(w, k -> new HashSet<>()).add(f);
                    });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println(map);
    }

}
