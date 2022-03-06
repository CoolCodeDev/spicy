package se.coolcode.spicy.utils.logger.logpattern;

import java.util.Map;

import se.coolcode.spicy.utils.logger.LogEvent;

public class MessageLogPattern implements LogPattern {
    private static final String key = "message";

    @Override
    public StringBuilder append(StringBuilder builder, LogEvent logEvent) {
        builder.append(logEvent.message);
        return builder;
    }

    @Override
    public void append(Map<String, String> map, LogEvent logEvent) {
        map.put(key, logEvent.message);
    }
    
}
