    package hatecode._0001_0999;
import java.util.*;
public class _975OddEvenJump {
    /*
     * 975. Odd Even Jump 
     * 
     * You are given an integer array A. From some starting
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
    
/*
 * Take [5,1,3,4,2] as example.

If we start at 2,
we can jump either higher first or lower first to the end,
because we are already at the end.
higher(2) = true
lower(2) = true

If we start at 4,
we can't jump higher, higher(4) = false
we can jump lower to 2, lower(4) = higher(2) = true

If we start at 3,
we can jump higher to 4, higher(3) = lower(4) = true
we can jump lower to 2, lower(3) = higher(2) = true

If we start at 1,
we can jump higher to 2, higher(1) = lower(2) = true
we can't jump lower, lower(1) = false

If we start at 5,
we can't jump higher, higher(5) = false
we can jump lower to 4, lower(5) = higher(4) = false

 */
  //thinking process,  
    //the problem is to say we want to jump from i->j, we have two options, 
    //even jump, find the smaller [i+1, len],2,4,6
    //odd jump, find the larger in [i+1, len] 1,3,5
    
    //if we can get last element, then problem solved, but each 
    //element has fixed steps to last one, 
    //so if we read from front to end, then we know it is not good because because we 
    //duplicate compare from each start,so we read from behind, if we know position which 
    //could reach last then we do not need to go to last 
    
    //as the requirement, treeMap is perfect match here
    //even jumped, means first jump, you have to choose the smallest but larger than A[i]
    //2nd jump you have to choose the biggest among the largest

    /*
     * example  [10,13,12,14,15]
     * high[4]=low[4] = true
     * 
     * we visit from 14--> 10, 
     * high[3] = low[4] = true
     * low[3] = false 
     * 
     * then visit 12 
     * high[2] = low[3] = false 
     * low[2] = fasle 
     * 
     * then visit 13 
     * high[1] = low[3] = fasle 
     * low[1] = high[2] = false 
     * 
     * then visit 10 
     * high[0] = low[2] = false 
     * low[0] = false 
     * 
     * 
     * for every node, we can only jump odd firstly, so we only choose high[i] = true
     */
    public int oddEvenJumps(int[] A) {
        if (A == null || A.length < 1) return 0;
        int n  = A.length, res = 1;
        /*
         high[i] = true means you can odd jump from position to last one, which means latter is bigger
         low[i] = true means you can even jump from position to last one
        */
        boolean[] higher = new boolean[n], lower = new boolean[n];
        higher[n - 1] = lower[n - 1] = true;
        
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(A[n - 1], n - 1);
        //from back, we can know how many 
        for (int i = n - 2; i >= 0; --i) {
            //ceilingKey >= A[i], floorKey <= A[i]
            Integer hi = map.ceilingKey(A[i]), 
                    lo = map.floorKey(A[i]);
            if (hi != null) higher[i] = lower[map.get(hi)];
            if (lo != null) lower[i] = higher[map.get(lo)];
            //since last one is true, if we can continue this from n-1 to 0 so it is good
            if (higher[i]) res++;
            map.put(A[i], i);
        }
        
        /* this is useing ceiling entry, which could aovid O(lgn) query
         for (int i = n - 2; i >= 0; --i) {
            Map.Entry hi = map.ceilingEntry(A[i]), lo = map.floorEntry(A[i]);
            if (hi != null) higher[i] = lower[(int)hi.getValue()];
            if (lo != null) lower[i] = higher[(int)lo.getValue()];
            if (higher[i]) res++;
            map.put(A[i], i);
        }
         */
        return res;
    }
/*
 * 简单来说就是，从数组的某个index出发，先跳到它后面的比它大的元素中最小的元素
 * （如果有相同元素则选择最靠左的一个），再跳到它后面的比它小的元素中最大的元素
 * （相同时仍然选择最靠左的一个），交替采取这两种跳法，直到跳不动了为止。
 * 问从多少个index出发可以最终跳到最后一个元素。
First let's create a boolean DP array.
note: first jump is up->0. down->1
dp[i][0] stands for you can arrive index n - 1 starting from index i at an up step.
dp[i][1] stands for you can arrive index n - 1 starting from index i at an down step.
Initialization:
Index n - 1 is always a good start point, regardless it's odd or even step right now. Thus dp[n - 1][0] = dp[n - 1][1] = true.
DP formula:
so 
dp[i][0] = dp[index_next_greater_number][1] - because next is odd step
dp[i][1] = dp[index_next_smaller_number][0] - because next is even step
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
            //first step we must use odd jump
            res += dp[i][0] ? 1 : 0;
        }

        return res;
    }
}