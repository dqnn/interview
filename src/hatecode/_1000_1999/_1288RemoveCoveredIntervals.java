package hatecode._1000_1999;

import java.util.Arrays;

public class _1288RemoveCoveredIntervals {
/*
1288. Remove Covered Intervals
Given an array intervals where intervals[i] = [li, ri] 
represent the interval [li, ri), remove all intervals that are 
covered by another interval in the list.

The interval [a, b) is covered by the interval [c, d) 
if and only if c <= a and b <= d.

Return the number of remaining intervals.

 

Example 1:

Input: intervals = [[1,4],[3,6],[2,8]]
Output: 2
*/
    /*
     * thinking process: O(nlgn)/O(1)
     * given list of intervals, [start, end], start <= end and integers, small ones
     * can be contained in big ones, return how many left.
     * 
     * sort by start asc, end by desc. then if end1 >=end2 this means 2nd interval
     * can be contained by first one. 
     * 
     * all intervals can be considered two points:
     * 1.  sort. 
     * 2.  3 cases between two intervals. 
     *      a. contained, 
     *      b. overlap 
     *      c. no overlap
     */
  
    public int removeCoveredIntervals(int[][] ins) {
        if (ins == null || ins.length < 1 || ins[0].length < 1) return 0;
        
        Arrays.sort(ins, (a, b)->(a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]));
        
        int res = 0, r = 0;
        for(int[] n : ins) {
            if(n[1] > r) {
                res++;
                r = n[1];
            }
        }
        return res;
    }
}