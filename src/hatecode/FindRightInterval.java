package hatecode;

import java.util.*;
public class FindRightInterval {
    /*
     * 436. Find Right Interval Given a set of intervals, for each of the interval
     * i, check if there exists an interval j whose start point is bigger than or
     * equal to the end point of the interval i, which can be called that j is on
     * the "right" of i.
     * 
     * For any interval i, you need to store the minimum interval j's index, which
     * means that the interval j has the minimum start point to build the "right"
     * relationship for interval i. If the interval j doesn't exist, store -1 for
     * the interval i. Finally, you need output the stored value of each interval as
     * an array.
     * 
     * Note:
     * 
     * You may assume the interval's end point is always bigger than its start
     * point. You may assume none of these intervals have the same start point.
     * 
     * 
     * Example 1:
     * 
     * Input: [ [1,4], [2,3], [3,4] ]
     * 
     * Output: [-1, 2, -1]
     * 
     */
  //thinking process:
    //given an array of interval, for each interval if we can find another interval starts from its end (inclusive),
    //then outout the index on that position.
    
    //treeMap is nature solution for this problem
    public int[] findRightInterval(int[][] intervals) {
        TreeMap<Integer, Integer> tree = new TreeMap<>();
        int res[] = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            tree.put(intervals[i][0], i);
        }
        for (int i = 0; i < intervals.length; i++) {
            //ceilingEntry is like min(>=intervals[i][1]), so 
            Map.Entry<Integer, Integer> pos = tree.ceilingEntry(intervals[i][1]);
            res[i] = pos == null ? -1 : pos.getValue();
        }
        return res;
    }
}