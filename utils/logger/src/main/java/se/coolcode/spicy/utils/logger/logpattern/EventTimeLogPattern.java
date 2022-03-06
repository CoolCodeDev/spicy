package se.coolcode.spicy.utils.logger.logpattern;

import java.time.format.DateTimeFormatter;
import java.util.Map;

import se.coolcode.spicy.utils.logger.LogEvent;

public class EventTimeLogPattern implements LogPattern {
    private static final String key = "eventTime";
    private final DateTimeFormatter formatter;
    
    public EventTimeLogPattern(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public StringBuilder append(StringBuilder builder, LogEvent logEvent) {
        builder.append(getValue(logEvent));
        return builder;
    }

    @Override
    public void append(Map<String, String> map, LogEvent logEvent) {
        map.put(key, getValue(logEvent));
    }

    private String getValue(LogEvent logEvent) {
        return logEvent.created.format(formatter);
    }
    
}
