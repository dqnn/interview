package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : DataStreamasDisjointIntervals
 * Creator : duqiang
 * Date : Jan, 2018
 * Description : 352. Data Stream as Disjoint Intervals
 * Given a data stream input of non-negative integers a1, a2, ..., an, ..., 
 * summarize the numbers seen so far as a list of disjoint intervals.

For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ...,
 then the summary will be:

[1, 1]
[1, 1], [3, 3]
[1, 1], [3, 3], [7, 7]
[1, 3], [7, 7]
[1, 3], [6, 7]
Follow up:
What if there are lots of merges and the number of disjoint intervals are small 
compared to the data stream's size?
 */
public class _352DataStreamasDisjointIntervals {

    /**

     treeMap : (0,3) (7,7)

     lowerKey = null
     higherKey = 1
     val = 0

     */

    TreeMap<Integer, Interval> treeMap;

    public _352DataStreamasDisjointIntervals() {
        treeMap = new TreeMap<>();
    }

    // time : O(logn)
    public void addNum(int val) {
        if (treeMap.containsKey(val)) return;
        Integer lowerKey = treeMap.lowerKey(val);
        Integer higherKey = treeMap.higherKey(val);
        if (lowerKey != null && higherKey != null && treeMap.get(lowerKey).end + 1 == val
                && val + 1 == treeMap.get(higherKey).start) {
            treeMap.get(lowerKey).end = treeMap.get(higherKey).end;
            treeMap.remove(higherKey);
        } else if (lowerKey != null && val <= treeMap.get(lowerKey).end + 1) {
            treeMap.get(lowerKey).end = Math.max(treeMap.get(lowerKey).end, val);
        } else if (higherKey != null && treeMap.get(higherKey).start - 1 == val) {
            treeMap.put(val, new Interval(val, treeMap.get(higherKey).end));
            treeMap.remove(higherKey);
        } else {
            treeMap.put(val, new Interval(val, val));
        }
    }

    public List<Interval> getIntervals() {
        return new ArrayList<>(treeMap.values());
    }
}
