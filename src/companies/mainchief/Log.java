package companies.mainchief;

import java.util.Arrays;
import java.util.Date;


public class Log {
    private String loggerName;

    public static Log getLogger(String loggerName) {
        return new Log(loggerName);
    }

    public Log(String loggerName) {
        this.loggerName = loggerName;
    }



    public  void log(String msg) {
       String timeString = new Date().toLocaleString();
       System.out.println(String.join("  ", Arrays.asList(timeString, loggerName, msg)));
    }

}
