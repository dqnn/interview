package hatecode;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : InsertInterval
 * Creator : duqiang
 * Date : Oct, 2017
 * Description : 57. Insert Interval
 */
public class InsertInterval {
    
    
    /**
     * 57. Insert Interval
     * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

     * Example 1:
     Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

     Example 2:
     Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

     This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].

     time : O(n)
     space : O(n)
     
     * @param intervals
     * @param newInterval
     * @return
     * Facebook interview followup: how to improve if called multiple times
     *  so we can try to use TreeMap.subTree() subMap(from, false, to, false);
     */
    public List<Interval> insert(List<Interval> ins, Interval newIn) {
        List<Interval> res = new ArrayList<>();
        //edge case
        if (ins == null || newIn == null) {
            return res;
        }
        Interval newInv = newIn;
        int i = 0, len = ins.size();
        //first we process the interval which is before the newIn
        while (i < len && newInv.start > ins.get(i).end ) {
            res.add(ins.get(i++));
        }
        // a---------------b
        //    c----------------d 
        // to make sure above two segment have overlap, it has to satisfy: 
        // d >= a && c <= b
        // here process the overlap ones, ins.get(i).end > newIn.start && newIn.end >= ins.get(i).start
        //newsIn.start <= ins.get(i).end
        while (i < len && newInv.end >= ins.get(i).start) {
            newInv.start = Math.min(ins.get(i).start, newInv.start);
            newInv.end = Math.max(ins.get(i).end, newInv.end);
            i++;
        }
     // add the union of intervals we got
        res.add(newInv);
        
        //add the rest intervals
        while (i < len) {
            res.add(ins.get(i++));
        }
        return res;
    }
    // simpler way of solutions
    public List<Interval> insert2(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<Interval>();
        //edge case
        if (intervals == null || newInterval == null) {
            return result;
        }
  
        for(Interval interval: intervals){
            // here is a good example how can we write if /else in complicated situations
            // new start bigger than interval in original list, so we just add it
            if(interval.end < newInterval.start){
                result.add(interval);
            // here means internal end > newIn.start && interval.start > newIn.end
            // so means interval start bigger then newIn.end, should be like after merge done
            }else if(interval.start > newInterval.end){
                result.add(newInterval);
                // we use interval to replace newInterval
                newInterval = interval;
            }else if(interval.end >= newInterval.start || interval.start <= newInterval.end){
                newInterval = new Interval(Math.min(interval.start, newInterval.start), Math.max(newInterval.end, interval.end));
            }
        }
  
        result.add(newInterval); 
        return result;
    }
}
