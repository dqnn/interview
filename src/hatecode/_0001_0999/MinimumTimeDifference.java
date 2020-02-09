package hatecode._0001_0999;

import java.util.*;
public class MinimumTimeDifference {
/*
539. Minimum Time Difference
Given a list of 24-hour clock time points in "Hour:Minutes" format, find the minimum minutes difference between any two time points in the list.
Example 1:
Input: ["23:59","00:00"]
Output: 1
*/
    public int findMinDifference(List<String> t) {
        if (t == null || t.size() < 1) return 0;
        boolean[] bucket = new boolean[24 * 60];
        
        for(String all : t) {
            String[] time = all.split(":");
            int h = Integer.valueOf(time[0]);
            int m = Integer.valueOf(time[1]);
            
            if (bucket[h * 60 + m]) return 0;
            bucket[h * 60 + m] = true;
        }
        
        //prev will record previous value which is hour * 60 + minute
        //first is record the min minutes value, last is max minutes value
        //Math.min(min, (24 * 60 - last + first)) is to mean make sure all 
        //compare is within one cycle
        int prev = 0, min = Integer.MAX_VALUE;
        int first = Integer.MAX_VALUE, last = Integer.MIN_VALUE;
        for(int i = 0; i< 24 * 60; i++) {
            if (bucket[i]) {
                if (first != Integer.MAX_VALUE) {
                    min = Math.min(min, i - prev);
                }
                first = Math.min(first, i);
                last = Math.max(last, i);
                prev = i;
            }
        }
        min = Math.min(min, (24 * 60 - last + first));
        
        return min;
    }
    
    //this one is better because it is 
    //1 reduce the loop, we get the min and max minutes, reduced the data size
    //2 for the cycle, we divide by 2 cases, 
    //a. is cycle overlap, 
    //b. within one cycle, we minus, 
    //and iterate on previous i 
    public int findMinDifference_Better(List<String> timePoints) {
        boolean[] mark = new boolean[24 * 60];
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(String time : timePoints){
            String[] t = time.split(":");
            int h = Integer.parseInt(t[0]);
            int m = Integer.parseInt(t[1]);
            if(mark[h * 60 + m]){
                return 0;
            }
            min = Math.min(min, h * 60 + m);
            max = Math.max(max, h * 60 + m);
            mark[h * 60 + m] = true;
        }
        int minDiff = Integer.MAX_VALUE, prev = 0;
        for(int i = min; i <= max; i++){   //set the range from min to max as an optimization.
            if(mark[i]){
                if(i == min){   
                    //the min and max is the special case, it looks like :
                    //0---min----max-----1440, so we have two directions to calculate the distance
                    minDiff = Math.min(minDiff, Math.min(max - min, 1440 - max + min));
                }else{
                    //normal case: just one direction.
                    minDiff = Math.min(minDiff, i - prev);
                }
                prev = i;
            }
        }
        return minDiff;
    }
}