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
 "0:start:2",
 "0:end:5",
 "1:start:6",
 "1:end:6",
 "0:end:7"]
Output:[7, 1]
 */
    /*
     *  0  1  2  3  4  5  6  7
     0  -------               ---
     0         -----------
     1                    ---       
     
     (())
     see above graph,
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
            Log curLog = fromString(log);
            if (curLog.isStart) {
                stack.push(curLog);
            } else {
                Log preStartLog = stack.pop();
                int time = curLog.timep- preStartLog.timep + 1; //endtime-starttime
                //like (()()), parent has 2 child, so it has add 2 childTime
                if(!stack.isEmpty()) stack.peek().childTime += time;
                //last function childTime is 0; and we have duplicate one
                //here is the key, need to update current object time, 
                
                //we have to += because one process can be start multiple times, like 
                //0:s:0, 0:e:2, 0:s:3, 0:e:5
                res[curLog.id] += time - preStartLog.childTime;
            }
        }
        return res;
    }
    private static Log fromString(String A) {
        String[] in = A.split(":");
        Log res = new Log();
        res.id = Integer.valueOf(in[0]);
        res.isStart = "start".equals(in[1]);
        res.timep = Integer.valueOf(in[2]);
        return res;
}
    
    static class Log{
        //process id
        int id;
        //time point integer,
        int timep;
        //time span spent on child processes,
        /*
         * for example, 
         * [ "0:start:0",
             "0:start:2",
             "0:end:5",
             "1:start:6",
             "1:end:6",
             "0:end:7"]
            
         * 
         */
        int childTime;
        //flag to see it is start or stop
        boolean isStart;
    }
    
    public static void main(String[] args) {
        System.out.println(Arrays.toString(exclusiveTime(1, List.of("0:start:0","0:start:2",
                "0:end:5","0:start:6","0:end:6","0:end:7"))));
        
    }
}