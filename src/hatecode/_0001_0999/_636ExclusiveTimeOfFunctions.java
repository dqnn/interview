package hatecode._0001_0999;

import java.util.*;

public class _636ExclusiveTimeOfFunctions {
/*
 636 Exclusive Time of Functions
Given the running logs of n functions that are executed in a nonpreemptive 
single threaded CPU, find the exclusive time of these functions.

Each function has a unique id, start from 0 to n-1. A function may be called 
recursively or by another function.

A log is a string has this format : function_id:start_or_end:timestamp. 
For example, "0:start:0" means function 0 starts from the very beginning 
of time 0. "0:end:0" means function 0 ends to the very end of time 0.

Exclusive time of a function is defined as the time spent within this function, 
the time spent by calling other functions should not be considered as this function's 
exclusive time. 
You should return the exclusive time of each function sorted by their 
function id.

Example 1:
Input:
n = 2
logs = 
["0:start:0",
 "1:start:2",
 "1:end:5",
 "0:end:6"]
Output:[3, 4]
 */
    //thinking process: we have logs id: start: time, 3 parts, so our mission is to 
    // use id as array index, since the log has clean sub problem space so we 
    //can use stack to store the previous result, and we can find that there is 
    //child time need to be excluded so need to update parent time attribute in
    //stack, here we use peek(). 
    
    //the key of the problem:
    //using a iterative way to solve a recursive question,the pattern is 
    // like ( ) question
    public static int[] exclusiveTime(int n, List<String> logs) {
        if (n < 1 || logs == null) {
            return new int[0];
        }
        Stack<Log> stack = new Stack<>();
        int[] res = new int[n];
        for(String log : logs) {
            Log logObj = fromString(log);
            if (logObj.isStart) {
                stack.push(logObj);
            } else {
                Log startLog = stack.pop();
                int time = logObj.time- startLog.time + 1; //endtime-starttime
                //like (()()), parent has 2 child, so it has add 2 childTime
                if(!stack.isEmpty()) stack.peek().childTime += time;
                //last function childTime is 0; and we have duplicate one
                //here is the key, need to update current object time, 
                
                //we have to += because one process can be start multiple times, like 
                //0:s:0, 0:e:2, 0:s:3, 0:e:5
                res[logObj.id] += time - startLog.childTime;
            }
        }
        return res;
    }
    private static Log fromString(String log) {
            String[] line = log.split(":");
            System.out.println(line);
            return new Log(Integer.valueOf(line[0]),
                          Integer.valueOf(line[2]),
                          0, line[1].equals("start"));
    }
    
    static class Log {
        int id;
        int time;
        int childTime;
        boolean isStart;
        public Log(int id, int time, int child, boolean isStart) {
            this.id = id;
            this.time = time;
            this.childTime = child;
            this.isStart = isStart;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(Arrays.toString(exclusiveTime(1, List.of("0:start:0","0:start:2",
                "0:end:5","0:start:6","0:end:6","0:end:7"))));
        
    }
}