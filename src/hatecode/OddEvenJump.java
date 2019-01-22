package hatecode;
import java.util.*;
public class OddEvenJump {
    /*
     * 975. Odd Even Jump You are given an integer array A. From some starting
     * index, you can make a series of jumps. The (1st, 3rd, 5th, ...) jumps in the
     * series are called odd numbered jumps, and the (2nd, 4th, 6th, ...) jumps in
     * the series are called even numbered jumps.
     * 
     * You may from index i jump forward to index j (with i < j) in the following
     * way:
     * 
     * During odd numbered jumps (ie. jumps 1, 3, 5, ...), you jump to the index j
     * such that A[i] <= A[j] and A[j] is the smallest possible value. If there are
     * multiple such indexes j, you can only jump to the smallest such index j.
     * During even numbered jumps (ie. jumps 2, 4, 6, ...), you jump to the index j
     * such that A[i] >= A[j] and A[j] is the largest possible value. If there are
     * multiple such indexes j, you can only jump to the smallest such index j. (It
     * may be the case that for some index i, there are no legal jumps.) A starting
     * index is good if, starting from that index, you can reach the end of the
     * array (index A.length - 1) by jumping some number of times (possibly 0 or
     * more than once.)
     * 
     * Return the number of good starting indexes.
     */
    //thinking process, if we can get last element, then problem solved, but each 
    //element has fixed steps to last one, 
    //so if we read from front to end, then we know it is not good because because we 
    //duplicate compare from each start,so we read from behind, if we know position which 
    //could reach last then we do not need to go to last 
    
    //as the requirement, treeMap is perfect match here
    public int oddEvenJumps(int[] A) {
        if (A == null || A.length < 1) return 0;
        int n  = A.length, res = 1;
        boolean[] higher = new boolean[n], lower = new boolean[n];
        higher[n - 1] = lower[n - 1] = true;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(A[n - 1], n - 1);
        //from back, we can know how many 
        for (int i = n - 2; i >= 0; --i) {
            //ceilingKey >= A[i], floorKey <= A[i]
            Integer hi = map.ceilingKey(A[i]), lo = map.floorKey(A[i]);
            if (hi != null) higher[i] = lower[map.get(hi)];
            if (lo != null) lower[i] = higher[map.get(lo)];
            //since last one is true, if we can continue this from n-1 to 0 so it is good
            if (higher[i]) res++;
            map.put(A[i], i);
        }
        return res;
    }
/*
First let's create a boolean DP array.
dp[i][0] stands for you can arrive index n - 1 starting from index i at an odd step.
dp[i][1] stands for you can arrive index n - 1 starting from index i at an even step.
Initialization:
Index n - 1 is always a good start point, regardless it's odd or even step right now. Thus dp[n - 1][0] = dp[n - 1][1] = true.
DP formula:
dp[i][0] = dp[index_next_greater_number][1] - because next is even step
dp[i][1] = dp[index_next_smaller_number][0] - because next is odd step
Result:
Since first step is odd step, then result is count of dp[i][0] with value true.

To quickly find the next greater or smaller number and its index: traverse the array reversely and store data into a TreeMap using the number as Key and its index as Value.
*/
    public int oddEvenJumps_DP(int[] A) {
        int n = A.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        boolean[][] dp = new boolean[n][2];
        dp[n - 1][0] = true;
        dp[n - 1][1] = true;
        map.put(A[n - 1], n - 1);
        int res = 1;

        for (int i = n - 2; i >= 0; i--) {
            // Odd step
            Integer nextGreater = map.ceilingKey(A[i]);
            if (nextGreater != null) {
                dp[i][0] = dp[map.get(nextGreater)][1];
            }
            // Even step
            Integer nextSmaller = map.floorKey(A[i]);
            if (nextSmaller != null) {
                dp[i][1] = dp[map.get(nextSmaller)][0];
            }
            map.put(A[i], i);

            res += dp[i][0] ? 1 : 0;
        }

        return res;
    }
}