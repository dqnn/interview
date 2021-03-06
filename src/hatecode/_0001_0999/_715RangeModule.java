package hatecode._0001_0999;
import java.util.*;
public class _715RangeModule {
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
    public _715RangeModule() {
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
        Set<Integer> set = new HashSet<>(subMap.keySet());
        map.keySet().removeAll(set);
        
    }

    class RangeModuleWithSegmentTree {
        SegmentTreeNode root;

        public RangeModuleWithSegmentTree() {
            root = new SegmentTreeNode(0, 1_000_000_000);
        }

        public void addRange(int left, int right) {
            root.update(left, right, 1);
        }

        public boolean queryRange(int left, int right) {
            return right - left == root.query(left, right);
        }

        public void removeRange(int left, int right) {
            root.update(left, right, 0);
        }

        private class SegmentTreeNode {
            int start, end;
            SegmentTreeNode left, right;
            int sum;
            boolean lazy;
            int lazyValue;

            public SegmentTreeNode(int s, int e) {
                start = s;
                end = e;
                left = null;
                right = null;
                sum = 0;
                lazy = false;
                lazyValue = 0;
            }

            // this segment tree is a bit special, the smallest unit is [1-2] and [2-3] rather than [1-1], [2-2], and [3-3]
            public void update(int l, int r, int val) {
                if (end <= l || start >= r) return;
                
                if (l <= start && end <= r) {
                    lazy = true;
                    lazyValue = val;
                    sum = (end - start) * lazyValue;
                    return;
                }

                int mid = start + (end - start) / 2;
                if (left == null) left = new SegmentTreeNode(start, mid);
                if (right == null) right = new SegmentTreeNode(mid, end);

                if (lazy) {
                    left.update(start, end, lazyValue);
                    right.update(start, end, lazyValue);
                    lazy = false;
                }
                
                left.update(l, r, val);
                right.update(l, r, val);
                sum = left.sum + right.sum;
            }
            public int query(int l, int r) {
                if (end <= l || start >= r) return 0;

                if (l <= start && end <= r) {
                    return sum;
                }

                int mid = start + (end - start) / 2;
                if (left == null) left = new SegmentTreeNode(start, mid);
                if (right == null) right = new SegmentTreeNode(mid, end);

                if (lazy) {
                    left.update(start, end, lazyValue);
                    right.update(start, end, lazyValue);
                    lazy = false;
                }
                return left.query(l, r) + right.query(l, r);
            }
        }
    }
}