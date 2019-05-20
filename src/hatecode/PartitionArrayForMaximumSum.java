package hatecode;

import java.util.*;
public class PartitionArrayForMaximumSum {
/*
1043. Partition Array for Maximum Sum
Given an integer array A, you partition the array into (contiguous) subarrays of length at most K.  After partitioning, each subarray has their values changed to become the maximum value of that subarray.

Return the largest sum of the given array after partitioning.

 

Example 1:

Input: A = [1,15,7,9,2,5,10], K = 3
Output: 84
*/
    //thinking process:
    
    //given an array and K, break the array into several sub array, but each sub array max len is K, each element
    //would become the max element in that sub array, return the max sum of the whole array
    
    //dp[i] means for sub array, 0->i, the max sum, 
    //so if we have one more elements, this element which can only impact from i,i-1,,,i-k + 1,
    //so dp[i] just needs to be the max of them. 
    //how can rolling to i, so we need two loops, one is for n, another is for K, 
    //in K loop, since we only impact previous K elements result, so we need i -K >=0, which is k = 
    //Math.min(i, k)
    public int maxSumAfterPartitioning_DP(int[] A, int K) {
        if (A == null || A.length < 1 || K < 1) return 0;
        
        int n = A.length;
        int[] dp = new int[n+1];
        for(int i =0; i <= n;i++) {
            int max = Integer.MIN_VALUE;
            for(int k =1; k <= Math.min(i, K);k++) {
                max = Math.max(max, A[i-k]);
                dp[i]= Math.max(dp[i], dp[i-k] + max * k);
            }
        }
        return dp[n];
    }
    //this is Memo solution, 
    //
    public int maxSumAfterPartitioning(int[] A, int K) {
        if(A==null || K == 0) {
            return 0;
        }
        Map<Integer, Integer> dp = new HashMap<>();
        return helper(A, K, 0, dp);
    }
    
    private int helper(int[] A, int K, int pos,  Map<Integer, Integer> dp ) {
        if(dp.containsKey(pos)) return dp.get(pos);

        if(pos == A.length) return 0;
        
        int max = Integer.MIN_VALUE;
        int res = Integer.MIN_VALUE;
        for (int i = pos; i <= K + pos - 1 && i < A.length; i++) {
            max = Math.max(A[i], max);
            int subM = helper(A, K, i + 1, dp);
            res = Math.max(res, subM + (i - pos + 1) * max);
        }
        dp.put(pos, res);
        return res;
    }
    //this is another helper version to use array to cache the results
    //
    private int helper(int[] A, int K, int idx,  int[] memo ) {
        if(idx == A.length) {
            return 0;
        }
        
        if(memo[idx] > 0) {
            return memo[idx];
        }
        int max = Integer.MIN_VALUE;
        int result = Integer.MIN_VALUE;
        for(int i=idx; i<= K+idx-1 && i<A.length; i++) {
            max = Math.max(A[i], max);
            int subR = helper(A, K, i+1, memo);
            result = Math.max(result , subR+ (i-idx+1)*max);
        }
        memo[idx] = result;
        return result;
    }
}