package hatecode;
import java.util.*;
public class KthSmallestNumberInMultiplicationTable {
/*
668. Kth Smallest Number in Multiplication Table
Nearly every one have used the Multiplication Table. But could you find out the k-th smallest number quickly from the multiplication table?

Given the height m and the length n of a m * n Multiplication Table, and a positive integer k, you need to return the k-th smallest number in this table.

Example 1:
Input: m = 3, n = 3, k = 5
Output: 
Explanation: 
The Multiplication Table:
1	2	3
2	4	6
3	6	9

The 5-th smallest number is 3 (1, 2, 2, 3, 3).
*/
    class Tuple {
        int x, y;
        int val;
        Tuple(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
    //O(KNlogN)/O(N)
    public int findKthNumber_PQ(int m, int n, int k) {
        if (m <= 0 || n<=0 || k < 0 || k > m * n) return -1;
        
        PriorityQueue<Tuple> pq = new PriorityQueue<>((a, b)->(a.val - b.val));
        
        for(int i = 0; i < n;i++) {
            pq.offer(new Tuple(0, i, i+1));
        }
        
        for(int i = 0; i < k -1; i++) {
            Tuple t = pq.poll();
            if (t.x == m - 1) continue; 
            pq.offer(new Tuple(t.x + 1, t.y, t.val + t.y + 1));
        }
        
        return pq.poll().val;
    }
    //enough(x) is true if and only if there are k or more values in the multiplication table that are less than or equal to x
    public boolean enough(int x, int m, int n, int k) {
        int count = 0;
        for (int i = 1; i <= m; i++) {
            count += Math.min(x / i, n);
        }
        return count >= k;
    }

    public int findKthNumber(int m, int n, int k) {
        int lo = 1, hi = m * n;
        while (lo < hi) {
            int mi = lo + (hi - lo) / 2;
            if (!enough(mi, m, n, k)) lo = mi + 1;
            else hi = mi;
        }
        return lo;
    }
}