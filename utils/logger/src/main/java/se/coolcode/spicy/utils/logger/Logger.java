package se.coolcode.spicy.utils.logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Logger {

    private Class<?> type;

    public Logger(Class<?> type) {
        this.type = type;
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void warn(String message) {
        log(LogLevel.WARN, message);
    }

    private void log(LogLevel logLevel, String message) {
        log(new LogEvent(logLevel, type, message));
    }

    private void log(LogEvent logEvent) {
        byte[] result = LoggerConfiguration.json ? toJson(logEvent) : toPlainText(logEvent);

        LoggerConfiguration.output.forEach(stream -> {
            try {
                stream.write(result);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    stream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    private byte[] toPlainText(LogEvent logEvent) {
        StringBuilder builder = new StringBuilder();
        LoggerConfiguration.patterns.forEach(pattern -> pattern.append(builder, logEvent).append(" "));
        return builder.append("\n").toString().getBytes();
    }

    private byte[] toJson(LogEvent logEvent) {
        Map<String, String> map = new HashMap<>();
        LoggerConfiguration.patterns.forEach(pattern -> pattern.append(map, logEvent));
        StringBuilder builder = new StringBuilder();
        builder.append("{ ");
        map.entrySet().forEach(entry -> {
            builder.append("\"").append(entry.getKey()).append("\": ");
            builder.append("\"").append(entry.getValue()).append("\", ");
        });
        builder.delete(builder.length() -2, builder.length() -1);
        builder.append("}\n");
        return builder.toString().getBytes();
    }
    
}
