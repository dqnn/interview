package hatecode._1000_1999;

import java.util.Arrays;

public class _1288RemoveCoveredIntervals {
/*
1288. Remove Covered Intervals
Given an array intervals where intervals[i] = [li, ri] represent the interval [li, ri), remove all intervals that are covered by another interval in the list.

The interval [a, b) is covered by the interval [c, d) if and only if c <= a and b <= d.

Return the number of remaining intervals.

 

Example 1:

Input: intervals = [[1,4],[3,6],[2,8]]
Output: 2
*/
    /*
     * thinking process: O(nlgn)/O(1)
     * 
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