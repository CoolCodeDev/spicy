package se.coolcode.spicy.utils.logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LoggerConfiguration {
    static enum LogLevel {INFO, WARN, ERROR}
    private static LoggerConfiguration INSTANCE = new LoggerConfiguration();
    static Set<OutputStream> output = new HashSet<>();
    static List<LogPattern> patterns = new ArrayList<>();

    public static LoggerConfiguration toConsole() {
        output.add(System.out);
        return INSTANCE;
    }

    public static LoggerConfiguration toFile(String fileName) {
        try {
            Path path = Paths.get(fileName);
            output.add(new FileOutputStream(path.toFile(), true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return INSTANCE;
    }
    
    public static LoggerConfiguration dateTime(DateTimeFormatter formatter) {
        patterns.add(new DateTimeLogPattern(formatter));
        return INSTANCE;
    }
    
    public LoggerConfiguration logLevel() {
        patterns.add(new LogLevelLogPattern());
        return INSTANCE;
    }

    public LoggerConfiguration type() {
        patterns.add(new TypeLogPattern());
        return INSTANCE;
    }
    
    public LoggerConfiguration message() {
       patterns.add(new MessageLogPattern());
        return INSTANCE;
    }

    static interface LogPattern {

        StringBuilder append(StringBuilder builder, LogLevel logLevel, Class<?> type, String message);

    }

    private static class DateTimeLogPattern implements LogPattern {
        private DateTimeFormatter formatter;

        public DateTimeLogPattern(DateTimeFormatter formatter) {
            this.formatter = formatter;
        }

        @Override
        public StringBuilder append(StringBuilder builder, LogLevel logLevel, Class<?> type, String message) {
            builder.append(LocalDateTime.now().format(formatter));
            return builder;
        }
    }

    private static class TypeLogPattern implements LogPattern {

        @Override
        public StringBuilder append(StringBuilder builder, LogLevel logLevel, Class<?> type, String message) {
            builder.append(type.getName());
            return builder;
        }

    }

    private static class LogLevelLogPattern implements LogPattern {

        @Override
        public StringBuilder append(StringBuilder builder, LogLevel logLevel, Class<?> type, String message) {
            builder.append(logLevel.name());
            return builder;
        }

    }

    private static class MessageLogPattern implements LogPattern {

        @Override
        public StringBuilder append(StringBuilder builder, LogLevel logLevel, Class<?> type, String message) {
            builder.append(message);
            return builder;
        }

    }

}
