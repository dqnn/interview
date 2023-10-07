package hatecode._1000_1999;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class _1696JumpGameVI {
/*
1696. Jump Game VI
You are given a 0-indexed integer array nums and an integer k.

You are initially standing at index 0. In one move, 
you can jump at most k steps forward without going outside the boundaries of the array. 
That is, you can jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.

You want to reach the last index of the array (index n - 1). Your score is the sum of all nums[j] for each index j you visited in the array.

Return the maximum score you can get.

 

Example 1:

Input: nums = [1,-1,-2,4,-7,3], k = 2
Output: 7
*/
     /*
      thinking process: O(n * k)/O(n)  TLE

      so it is most common solution that we look back k steps to see how we can get max sum at 
      position i, 
    */
    public int maxResult_DP(int[] A, int k) {
        int n = A.length;
        
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MIN_VALUE);
        
        dp[0] = A[0];
        
        
        for(int i = 1; i< n; i++) {
            for(int j = Math.max(0, i - k); j < i; j++) {
                dp[i] = Math.max(dp[i], dp[j] + A[i]);
            }
        }
        
        return dp[n-1];
    }
    
    /*
     intreview friendly: 
     thinking process: O(n)/O(n + k), dp + dq

     so the basic idea: 
     dp[i], for position i, the max sum, so result should be dp[n-1]
     deque<Integer> dq: a monotonic queue stores indexes, their A[dq.peekFirst()]
 always the biggest value in the queue. 
     so starts from position 1, we firstly dp[i] = A[i] + dq[dq.peekFirst()]
     then we make sure the dq is desc, and if newly dp[i] is bigger than previously looking from back to front, 
     then we need pollLast from the dq.

     then we look from front, if its index is smaller than i -k, which means we do not need them, remove them.
     last is add i into dq
     
     examples 
     [1,-1,-2,4,-7,3], k = 2

     i   A[i]  dp[i]   dq
     0    1     1      [0]
     1    -1    0      [1, 0]
     2    -2    -1     [0,-1]
     3    4     4      [4]
     4    -7    -3     [4,-3]
     5    3    7        [7]
    */
    public static int maxResult_best(int[] A, int k) {
        int n = A.length;
        
        Deque<Integer> dq = new LinkedList<>();
        int[] dp = new int[n];
        dp[0] = A[0];
        dq.offerLast(0);
        
        for(int i = 1; i<n; i++) {
            dp[i] = A[i] + dp[dq.peekFirst()];
            while(!dq.isEmpty() && dp[dq.peekLast()] <= dp[i]) {
                dq.pollLast();
            }
            
            while(!dq.isEmpty() && dq.peekFirst() < i- k) {
                dq.pollFirst();
            }
            
            dq.offerLast(i);
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        System.out.println(maxResult_best(new int[]{1,-1,-2,4,-7,3}, 2));
    }
}