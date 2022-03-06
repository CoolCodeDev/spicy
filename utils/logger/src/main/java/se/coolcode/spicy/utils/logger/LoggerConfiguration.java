package se.coolcode.spicy.utils.logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import se.coolcode.spicy.utils.logger.logpattern.EventTimeLogPattern;
import se.coolcode.spicy.utils.logger.logpattern.LogLevelLogPattern;
import se.coolcode.spicy.utils.logger.logpattern.LogPattern;
import se.coolcode.spicy.utils.logger.logpattern.LoggerLogPattern;
import se.coolcode.spicy.utils.logger.logpattern.MessageLogPattern;
/**
 * gör det möjligt att välja json till fil o plain text till console
 * .toFile() //skriver till fil
 * .toFile(new JsonPrinter()) //skriver json till fil
 * .plainText() //skriver text till alla outputs som inte har valt printer, sätter defaultPrinter
 * 
 * output.ifMissingPrinter(defaultPrinter)
 * class Output {
 *      private Printer printer
 *      print(String log) {}
 *      
 * }
 */
public class LoggerConfiguration {
    private static LoggerConfiguration INSTANCE = new LoggerConfiguration();
    static Set<OutputStream> output = new HashSet<>();
    static List<LogPattern> patterns = new ArrayList<>();
    static boolean json = false;

    public static LoggerConfiguration init() {
        return INSTANCE;
    }

    public LoggerConfiguration toConsole() {
        output.add(System.out);
        return INSTANCE;
    }

    public LoggerConfiguration toFile(String fileName) {
        try {
            Path path = Paths.get(fileName);
            output.add(new FileOutputStream(path.toFile(), true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return INSTANCE;
    }

    public LoggerConfiguration asJson() {
        LoggerConfiguration.json = true;
        return INSTANCE;
    }
    
    public LoggerConfiguration eventTime(DateTimeFormatter formatter) {
        patterns.add(new EventTimeLogPattern(formatter));
        return INSTANCE;
    }
    
    public LoggerConfiguration logLevel() {
        patterns.add(new LogLevelLogPattern());
        return INSTANCE;
    }

    public LoggerConfiguration logger() {
        patterns.add(new LoggerLogPattern());
        return INSTANCE;
    }
    
    public LoggerConfiguration message() {
       patterns.add(new MessageLogPattern());
        return INSTANCE;
    }

}
