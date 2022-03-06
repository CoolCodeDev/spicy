package se.coolcode.spicy.utils.logger;

import java.time.LocalDateTime;

public class LogEvent {
    public final LocalDateTime created;
    public final LogLevel logLevel;
    public final Class<?> type;
    public final String message;

    LogEvent(LogLevel logLevel, Class<?> type, String message) {
        this.created = LocalDateTime.now();
        this.logLevel = logLevel;
        this.type = type;
        this.message = message;
    }

}
