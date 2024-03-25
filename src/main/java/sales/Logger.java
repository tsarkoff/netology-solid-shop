package sales;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Logger<T> {
    private static Logger logger = null;

    private Logger() {
    }

    public static Logger getInstance() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    public void logf(String msg) {
        System.out.printf("\u001B[32m" + "[%15s] => %s" + "\u001B[0m", LocalDateTime.now(), msg);
    }
    public void log(String msg) {
        System.out.printf("\u001B[32m" + "[%15s] => %s%n" + "\u001B[0m", LocalDateTime.now(), msg);
    }

    public void log(T o) {
        System.out.println(o.toString());
    }

//    public void log(List<T> list) {
//        System.out.println(Arrays.toString(list.toArray()));
//    }
}
