package manager;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogsManager {

    static Logger logger;

    public static void initLogManager() {
        logger = Logger.getLogger("MyLog");
        FileHandler fh;
        try {

            // This block configure the logger with handler and formatter
            fh = new FileHandler("logs.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.setUseParentHandlers(false);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static Logger getLog() {
        if (logger == null)
            System.out.println("logs failure");
        return logger;
    }
}
