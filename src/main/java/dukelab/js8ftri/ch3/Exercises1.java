package dukelab.js8ftri.ch3;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Exercises1 {

    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getPackage().getName());

    public static void main(String[] args) throws SecurityException, IOException {
        try (InputStream is = MethodHandles.lookup().lookupClass().getResourceAsStream("logging.properties")) {
            LogManager.getLogManager().readConfiguration(is);
        }

        int i1 = 10;
        String str1 = "This is str1.";
        logIf(Level.FINEST, () -> i1 == 10, () -> "str1 = " + str1);

        logger.setLevel(Level.FINEST);
        int i2 = 10;
        String str2 = "This is str2.";
        logIf(Level.FINEST, () -> i2 == 10, () -> "str2 = " + str2);
    }

    private static void logIf(Level level, BooleanSupplier check, Supplier<String> message) {
        if (logger.isLoggable(Level.FINEST) && check.getAsBoolean()) {
            logger.log(level, message.get());
        }
    }

}
