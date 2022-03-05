package se.coolcode.spicy.utils.logger;

public class LoggerFactory {

    public static Logger getLogger(Class<?> type) {
        return new Logger(type);
    }
    
}
