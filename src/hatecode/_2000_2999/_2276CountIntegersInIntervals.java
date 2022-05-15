package hatecode._2000_2999;


import java.util.*;
public class _2276CountIntegersInIntervals {
/*
2276. Count Integers in Intervals
Given an empty set of intervals, implement a data structure that can:

Add an interval to the set of intervals.
Count the number of integers that are present in at least one interval.
Implement the CountIntervals class:

CountIntervals() Initializes the object with an empty set of intervals.
void add(int left, int right) Adds the interval [left, right] to the set of intervals.
int count() Returns the number of integers that are present in at least one interval.
Note that an interval [left, right] denotes all the integers x where left <= x <= right.

 

Example 1:

Input
["CountIntervals", "add", "add", "count", "add", "count"]
[[], [2, 3], [7, 10], [], [5, 8], []]
Output
[null, null, null, 6, null, 8]
*/

    
    //thinking process:  O(xlgn)/O(n)
    
    //the problem is to say: to implement a class, the class could insert intervals, 
    //the interval is like [3, 5], if we insert [4,6] then we should merge them and store
    //[3, 6], another is to return how many integers in this intervals inclusively, like here will be 
    //4.
    
    //we should always start from examples,like here, [3,5], then we need to add([4,6]), firstly we need to find out 
    //which intervals we need to merge from, this is more like a search problem, we can leverage binary search,
    //and se also need to know when should stop.
    
    //         l'-----------r'
    // l____r                   l1_____r1
    
    // we can think above graph is the lifetime of the merging process of two intervals.
    //how we can fast identify the interval in storage, we should use TreeMap, l as key while r as value,
    //we can use map.lowerEntry(l+1) to identify the closest interval from the left, we have two cases, one is null, another is we can get something,
    
    // so how to get next, we can map.higherEntry(left), the most closet to l.
    // how to stop, when current entry.getValue() < l1, then we should stop, because there is nothing in common.
    
    //there is one edge case when we cannot get anything for first, so we need to have a flag first to workaround on the edge case.
    
    int size = 0;
    TreeMap<Integer, Integer> map = new TreeMap<>();
    public _2276CountIntegersInIntervals() {
    }
    
    public void add(int l, int r) {
        boolean first = true;
        for(var it = map.lowerEntry(l + 1); first || it != null && it.getKey() <= r; it = map.higherEntry(l) ) {
            first = false;
            if (it == null || it.getValue() < l) {
                continue;
            }
            
            int curL = it.getKey(), curR = it.getValue();
            
            l = Math.min(l, curL);
            r = Math.max(r, curR);
            map.remove(curL);
            //remove the numbers inside this interval
            size = size - (curR - curL + 1);
        }
        
        map.put(l, r);
        size += r - l + 1;
    }
    
    public int count() {
        return size;
    }
    
    
class CountIntervals_AsList {
    
    List<int []> list;
    int count;

    public CountIntervals_AsList() {
        list = new ArrayList<>();
        count = 0;
    }
    
    public void add(int left, int right) {
        List<int []> nextList = new ArrayList<>();
        
        int [] a;
        boolean added = false;
        int l = left;
        int r = right;
        
        for (int i = 0; i < list.size(); ++i){
            a = list.get(i);
            
            if (a[1] < left - 1)
                nextList.add(a);
            else if (a[0] > right + 1){
                if (!added){
                    nextList.add(new int [] { l , r });
                    added = true;
                }
                
                nextList.add(a);
            }else {
                l = Math.min(a[0], l);
                r = Math.max(r , a[1]);
            }
        }
        
        if (!added) nextList.add(new int [] { l , r });
        
        count = 0;
        list = nextList;
        for (int [] arr : list){
            count += arr[1] - arr[0] + 1;
        }
    }
    
    public int count() {
        return count;
    }
}
}

/**
 * Your CountIntervals object will be instantiated and called as such:
 * CountIntervals obj = new CountIntervals();
 * obj.add(left,right);
 * int param_2 = obj.count();
 */