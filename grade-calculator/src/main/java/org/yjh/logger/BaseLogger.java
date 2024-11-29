package org.yjh.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class BaseLogger {
    private static final String ERROR_LOG_SAVE_PATH = "errorLog.txt";
    private static final Logger logger = Logger.getLogger("base logger");
    private static final BaseLogger INSTANCE = new BaseLogger();

    private FileHandler errorFileHandler = null;

    private BaseLogger() {

        try {
            errorFileHandler = new FileHandler(ERROR_LOG_SAVE_PATH, true);

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }

        errorFileHandler.setFormatter(new SimpleFormatter());

        logger.setLevel(Level.ALL);

        logger.addHandler(errorFileHandler);
    }

    public static BaseLogger getLogger() {
        return INSTANCE;
    }

    public void info(String message) {
        logger.info(message);
    }
}
