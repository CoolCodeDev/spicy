package se.coolcode.spicy.utils.logger.logpattern;

import java.util.Map;

import se.coolcode.spicy.utils.logger.LogEvent;

public class LogLevelLogPattern implements LogPattern {
    private static final String key = "loglevel";
    
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
        return logEvent.logLevel.name();
    }
    
}
