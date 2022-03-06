package se.coolcode.spicy.utils.logger;

import java.time.format.DateTimeFormatter;

public class Main {
    
    public static void main(String[] args) {
        LoggerConfiguration
        .eventTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        .logLevel()
        .logger()
        .message()
        .toConsole()
        .toFile("log.txt")
        .asJson();

        Logger logger = LoggerFactory.getLogger(Main.class);

        logger.info("Hello");
        logger.info("Hello");
        logger.info("Hello");
        logger.warn("Hello");
        logger.warn("Hello");
        logger.warn("Hello");
        logger.info("Hello");
    }
}
