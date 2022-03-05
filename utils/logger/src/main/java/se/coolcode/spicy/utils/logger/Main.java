package se.coolcode.spicy.utils.logger;

import java.time.format.DateTimeFormatter;

public class Main {
    
    public static void main(String[] args) {
        LoggerConfiguration
        .dateTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        .logLevel()
        .type()
        .message()
        .toConsole()
        .toFile("log.txt");

        Logger logger = LoggerFactory.getLogger(Main.class);

        logger.info("Hello");
        logger.info("Hello");
        logger.info("Hello");
        logger.info("Hello");
        logger.info("Hello");
        logger.info("Hello");
        logger.info("Hello");
    }
}
