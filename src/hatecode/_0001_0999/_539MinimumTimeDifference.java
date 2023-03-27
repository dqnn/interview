package hatecode._0001_0999;

import java.util.*;
public class _539MinimumTimeDifference {
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
    /*
     * 0 -----min-----max---24*60
     * 
     * min and max are dynamic, so have to store the pointer to min, max, prev to calculate the 
     * result
     * 
     * at last, we compare res to max-min, because it may diff 1m 
     */
    public int findMinDifference2(List<String> A) {
        if (A == null || A.size() < 1) return 0;
        
        int n = A.size();
        boolean[] mark = new boolean[24*60];
        for(String s : A) {
            String[] str = s.split(":");
            int h = Integer.valueOf(str[0]);
            int m = Integer.valueOf(str[1]);
            int key = h * 60 + m;
            if (mark[key]) return 0;
            mark[key] = true;
        }
        
        int prev = -1, min= Integer.MAX_VALUE, max = Integer.MIN_VALUE, res = Integer.MAX_VALUE;
        for(int i = 0; i<mark.length; i++) {
            if (mark[i]) {
                if(prev == -1) {
                    min = i;
                } else {
                    res = Math.min(res, i - prev);
                }
                max = i;
                prev = i;
            }
        }
        
        res = Math.min(res, 23*60 + 60 - (max -min)); 
        return res;
        
    }
    }
}