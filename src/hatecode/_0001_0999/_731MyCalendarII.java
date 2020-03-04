package hatecode._0001_0999;
import java.util.*;
public class _731MyCalendarII {
    /*
     * 731. My Calendar II Implement a MyCalendarTwo class to store your events. A
     * new event can be added if adding the event will not cause a triple booking.
     * 
     * Your class will have one method, book(int start, int end). Formally, this
     * represents a booking on the half open interval [start, end), the range of
     * real numbers x such that start <= x < end.
     * 
     * A triple booking happens when three events have some non-empty intersection
     * (ie., there is some time that is common to all 3 events.)
     * 
     * For each call to the method MyCalendar.book, return true if the event can be
     * added to the calendar successfully without causing a triple booking.
     * Otherwise, return false and do not add the event to the calendar.
     * 
     * Your class will be called like this:
     *  MyCalendar cal = new MyCalendar();
     * MyCalendar.book(start, end) 
     * Example 1: MyCalendar(); 
     * MyCalendar.book(10, 20); // returns true 
     * MyCalendar.book(50, 60); // returns true 
     * MyCalendar.book(10,40); // returns true 
     * MyCalendar.book(5, 15); // returns false
     * MyCalendar.book(5, 10); // returns true 
     * MyCalendar.book(25, 55); // returns true
     */
    
    private List<int[]> cals;
    private List<int[]> overlaps;

    public void MyCalendarTwo2() {
        this.cals = new ArrayList<>();
        this.overlaps = new ArrayList<>();
    }
    
    public boolean book2(int start, int end) {
        for(int[] lap : overlaps) {
            //we use the contraverse side to calculate this,so >=
            if (!(end <= lap[0] || start >= lap[1])) return false;
        }
        //figure out which 
        for(int[] cal : cals) {
            if (!(end <= cal[0] || start >= cal[1])) {
                overlaps.add(new int[]{Math.max(start, cal[0]), Math.min(end, cal[1])});
            }
        }
        cals.add(new int[]{start, end});
        return true;
    }

     private static class SegmentTreeNode {
        int             l, r;
        int             k, lazy;
        SegmentTreeNode left, right;

        SegmentTreeNode(int l, int r, int k) {
            this.l = l;
            this.r = r;
            this.k = k;
            this.lazy = 0;
            this.left = this.right = null;
        }
    }

    private int query(SegmentTreeNode node, int i, int j) {
        normalize(node);

        if (i > j || node == null || i > node.r || j < node.l)
            return 0;

        if (i <= node.l && node.r <= j)
            return node.k;

        return Math.max(query(node.left, i, j), query(node.right, i, j));
    }

    private void update(SegmentTreeNode node, int i, int j, int val) {
        normalize(node);

        if (i > j || node == null || i > node.r || j < node.l)
            return;

        if (i <= node.l && node.r <= j) {
            node.lazy = val;
            normalize(node);
            return;
        }

        update(node.left, i, j, val);
        update(node.right, i, j, val);

        node.k = Math.max(node.left.k, node.right.k);
    }

private void normalize(SegmentTreeNode node) {
    if (node.lazy > 0) node.k += node.lazy;
    
    if (node.l < node.r) {
        if (node.left == null || node.right == null) {
            int m = node.l + (node.r - node.l) / 2;
            node.left = new SegmentTreeNode(node.l, m, node.k);
            node.right = new SegmentTreeNode(m + 1, node.r, node.k);
        
        } else if (node.lazy > 0) {
            node.left.lazy += node.lazy;
            node.right.lazy += node.lazy;
        }
    }
    
    node.lazy = 0;
}

    SegmentTreeNode root;

    public _731MyCalendarII() {
        root = new SegmentTreeNode(0, 1_000_000_000, 0);
    }

    public boolean book(int start, int end) {
        int k = query(root, start, end - 1);
        if (k >= 2) return false;

        update(root, start, end - 1, 1);
        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */