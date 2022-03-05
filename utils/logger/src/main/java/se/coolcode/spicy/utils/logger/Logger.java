package se.coolcode.spicy.utils.logger;

import java.io.IOException;

import se.coolcode.spicy.utils.logger.LoggerConfiguration.LogLevel;

public class Logger {

    private Class<?> type;

    public Logger(Class<?> type) {
        this.type = type;
    }

    public void info(String message) {
        StringBuilder builder = new StringBuilder();
        LoggerConfiguration.patterns.forEach(pattern -> pattern.append(builder, LogLevel.INFO, type, message).append(" "));
        byte[] result = builder.append("\n").toString().getBytes();
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
    
}
