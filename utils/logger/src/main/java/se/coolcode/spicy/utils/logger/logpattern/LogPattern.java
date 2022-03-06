package se.coolcode.spicy.utils.logger.logpattern;

import java.util.Map;

import se.coolcode.spicy.utils.logger.LogEvent;

public interface LogPattern {

    StringBuilder append(StringBuilder builder, LogEvent logEvent);
    void append(Map<String, String> map, LogEvent logEvent);
    
}
