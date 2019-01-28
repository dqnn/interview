package hatecode;
import java.util.*;
public class RangeModule {
/*
715. Range Module
A Range Module is a module that tracks ranges of numbers. Your task is to design and implement the following interfaces in an efficient manner.

addRange(int left, int right) Adds the half-open interval [left, right), tracking every real number in that interval. Adding an interval that partially overlaps with currently tracked numbers should add any numbers in the interval [left, right) that are not already tracked.
queryRange(int left, int right) Returns true if and only if every real number in the interval [left, right) is currently being tracked.
removeRange(int left, int right) Stops tracking every real number currently being tracked in the interval [left, right).
Example 1:
addRange(10, 20): null
removeRange(14, 16): null
queryRange(10, 14): true (Every number in [10, 14) is being tracked)
queryRange(13, 15): false (Numbers like 14, 14.03, 14.17 in [13, 15) are not being tracked)
queryRange(16, 17): true (The number 16 in [16, 17) is still being tracked, despite the r
*/
    class Interval {
        int left;
        int right;

        public Interval(int left, int right){
            this.left = left;
            this.right = right;
        }
    }
    //thinking process: 
    //the problem is to management the intervals, add, query, and remove(), the parameters are the same
    //for intervals, we already have done merge, and here is the mostly about merge, like add. 
    //query is to know which interval in the system or not, remove is just remove
    
    //so how to management the start and end is important, this use TreeSet, treeSet is to have 
    //tailSet >=(input), higher(), means >input, 
    TreeSet<Interval> ranges;
    public RangeModule() {
        ranges = new TreeSet<>((a,b)->(a.right == b.right ? a.left - b.left : a.right - b.right));
    }

    public void addRange(int left, int right) {
        //return intervals' iterator begin with left
        Iterator<Interval> itr = ranges.tailSet(new Interval(0, left - 1)).iterator();
        //we start to merge [left, right] to with other interval begin >= left
        while (itr.hasNext()) {
            Interval iv = itr.next();
            if (right < iv.left) break;
            left = Math.min(left, iv.left);
            right = Math.max(right, iv.right);
            itr.remove();
        }
        ranges.add(new Interval(left, right));
    }

    public boolean queryRange(int left, int right) {
        //return Interval which right is bigger than left, strictly greater than left. 
        //begin with left + 1
        Interval iv = ranges.higher(new Interval(0, left));
        return (iv != null && iv.left <= left && right <= iv.right);
    }
    //remove range, so we go through all ranges between left and right, and remove we can remove
    public void removeRange(int left, int right) {
        Iterator<Interval> itr = ranges.tailSet(new Interval(0, left)).iterator();
        ArrayList<Interval> todo = new ArrayList<>();
        while (itr.hasNext()) {
            Interval iv = itr.next();
            if (right < iv.left) break;
            if (iv.left < left) todo.add(new Interval(iv.left, left));
            if (right < iv.right) todo.add(new Interval(right, iv.right));
            itr.remove();
        }
        for (Interval iv: todo) ranges.add(iv);
    }
}
//this is TreeMap version to solve the problem
class RangeModule2 {
    TreeMap<Integer, Integer> map;
    public RangeModule2() {
        map = new TreeMap<>();
    }
    
    public void addRange(int left, int right) {
        if (right <= left) return;
        Integer start = map.floorKey(left);
        Integer end = map.floorKey(right);
        if (start == null && end == null) {
            map.put(left, right);
        } else if (start != null && map.get(start) >= left) {
            map.put(start, Math.max(map.get(end), Math.max(map.get(start), right)));
        } else {
            map.put(left, Math.max(map.get(end), right));
        }
        // clean up intermediate intervals
        Map<Integer, Integer> subMap = map.subMap(left, false, right, true);
        Set<Integer> set = new HashSet<>(subMap.keySet());
        map.keySet().removeAll(set);
    }
    
    public boolean queryRange(int left, int right) {
        Integer start = map.floorKey(left);
        if (start == null) return false;
        return map.get(start) >= right;
    }
    
    public void removeRange(int left, int right) {
        if (right <= left) return;
        Integer start = map.floorKey(left);
        Integer end = map.floorKey(right);
        if (end != null && map.get(end) > right) {
            map.put(right, map.get(end));
        }
        if (start != null && map.get(start) > left) {
            map.put(start, left);
        }
        // clean up intermediate intervals
        Map<Integer, Integer> subMap = map.subMap(left, true, right, false);
        Set<Integer> set = new HashSet(subMap.keySet());
        map.keySet().removeAll(set);
        
    }
}