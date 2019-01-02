package hatecode;

import java.util.*;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LoggerRateLimiter
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : 359. Logger Rate Limiter
 */
public class LoggerRateLimiter {
    /**
     * Design a logger system that receive stream of messages along with its timestamps, 
     * each message should be printed if and only if it is not printed in the last 10 seconds.

Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the 
given timestamp, otherwise returns false.

It is possible that several messages arrive roughly at the same time.
Examples:

     Logger logger = new Logger();

     // logging string "foo" at timestamp 1
     logger.shouldPrintMessage(1, "foo"); returns true;

     // logging string "bar" at timestamp 2
     logger.shouldPrintMessage(2,"bar"); returns true;

     // logging string "foo" at timestamp 3
     logger.shouldPrintMessage(3,"foo"); returns false;

     // logging string "bar" at timestamp 8
     logger.shouldPrintMessage(8,"bar"); returns false;

     // logging string "foo" at timestamp 10
     logger.shouldPrintMessage(10,"foo"); returns false;

     // logging string "foo" at timestamp 11
     logger.shouldPrintMessage(11,"foo"); returns true;

     time : O(1)
     space : O(n)

     */
    /** Initialize your data structure here. */
    HashMap<String, Integer> map;

    public LoggerRateLimiter() {
        map = new HashMap<>();
    }

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
     If this method returns false, the message will not be printed.
     The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!map.containsKey(message) || timestamp - map.get(message) >= 10) {
            map.put(message, timestamp);
            return true;
        }
        return false;
    }
    
    
    //interview friendly,
    //1 is to reduce logs entry size and keep minimal size of logger
    class Log {
        int timestamp;
        String message;
        public Log(int aTimestamp, String aMessage) {
            timestamp = aTimestamp;
            message = aMessage;
        }
    }

    public class Logger {
        PriorityQueue<Log> recentLogs;
        Set<String> recentMessages;   
        
        /** Initialize your data structure here. */
        public Logger() {
            recentLogs = new PriorityQueue<>((a, b)->(a.timestamp -b.timestamp));
            recentMessages = new HashSet<String>();
        }
        
        /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
            If this method returns false, the message will not be printed.
            The timestamp is in seconds granularity. */
        public boolean shouldPrintMessage(int timestamp, String message) {
            while (recentLogs.size() > 0)   {
                Log log = recentLogs.peek();
                // discard the logs older than 10 minutes
                if (timestamp - log.timestamp >= 10) {
                    recentLogs.poll();
                    recentMessages.remove(log.message);
                } else break;
            }
            boolean res = !recentMessages.contains(message);
            if (res) {
                recentLogs.add(new Log(timestamp, message));
                recentMessages.add(message);
            }
            return res;
        }
    }
}
