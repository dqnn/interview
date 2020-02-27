package hatecode._0001_0999;
import java.util.*;
public class _986IntervalListIntersectionslution {
    /*
     * 986. Interval List Intersections 
     * Given two lists of closed intervals, each
     * list of intervals is pairwise disjoint and in sorted order.
     * 
     * Return the intersection of these two interval lists.
     * 
     * (Formally, a closed interval [a, b] (with a <= b) denotes the set of real
     * numbers x with a <= x <= b. The intersection of two closed intervals is a set
     * of real numbers that is either empty, or can be represented as a closed
     * interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].)
     */
    
    
    public Interval[] intervalIntersection_Standard(Interval[] A, Interval[] B) {
        if (A == null || A.length == 0 || B == null || B.length == 0) {
            return new Interval[] {};
        }
        
        List<Interval> ans = new ArrayList<>();
        int i = 0, j = 0;

        while (i < A.length && j < B.length) {
            // Let's check if A[i] intersects B[j].
            // s - the startpoint of the intersection
            // e - the endpoint of the intersection
            int s = Math.max(A[i].start, B[j].start);
            int e = Math.min(A[i].end, B[j].end);
            if (s <= e) ans.add(new Interval(s, e));

            // Remove the interval with the smallest endpoint
            if (A[i].end < B[j].end) i++;
            else j++;
        }

        return ans.toArray(new Interval[ans.size()]);
    }
    
  //interview friendly, thinking process: 
    // so the problems is to say, given two sets of intervals, they have no overlap on itselfves so  
    //the overlap has to happen between two of them, like bianry add, linkedlist add
    public Interval[] intervalIntersection(Interval[] A, Interval[] B) {
        if (A == null || A.length == 0 || B == null || B.length == 0) {
            return new Interval[] {};
        }
        
        int m = A.length, n = B.length;
        int i = 0, j = 0;
        List<Interval> res = new ArrayList<>();
        while (i < m && j < n) {
            Interval a = A[i];
            Interval b = B[j];

            // find the overlap... if there is any...
            int startMax = Math.max(a.start, b.start);
            int endMin = Math.min(a.end, b.end);
            
            if (endMin >= startMax) {
                res.add(new Interval(startMax, endMin));
            }
            
            //update the pointer with smaller end value...
            if (a.end == endMin) { i++; }
            if (b.end == endMin) { j++; }
        }
        
        return res.toArray(new Interval[0]);
    }
}