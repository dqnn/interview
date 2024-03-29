package hatecode._0001_0999;

import java.util.Arrays;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : PartitionEqualSubsetSum
 * Date : September, 2018
 * Description : 416. Partition Equal Subset Sum
 */
/*
Given a non-empty array containing only positive integers, 
find if the array can be partitioned into two subsets such that the sum of elements 
in both subsets is equal.

Note:
Each of the array element will not exceed 100.
The array size will not exceed 200.
Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.
 */
/*
This problem is essentially let us to find whether there are several numbers in a set 
which are able to sum to a specific value (in this problem, the value is sum/2).

Actually, this is a 0/1 knapsack problem, for each number, we can pick it or not. 
Let us assume dp[i][j] means whether the specific sum j can be gotten from the first 
i numbers. If we can pick such a series of numbers from 0-i whose sum is j, dp[i][j] is true, 
otherwise it is false.

Base case: dp[0][0] is true; (zero number consists of sum 0 is true)

Transition function: For each number, if we don't pick it, dp[i][j] = dp[i-1][j], 
which means if the first i-1 elements has made it to j, dp[i][j] would also make it to 
j (we can just ignore nums[i]). If we pick nums[i]. dp[i][j] = dp[i-1][j-nums[i]], 
which represents that j is composed of the current value nums[i] and the remaining composed 
of other previous numbers. Thus, the transition function is 
dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]]

talking is cheap:


it is same as coin change II
 */
public class _416PartitionEqualSubsetSum {
    public boolean canPartition(int[] A) {
        int sum = Arrays.stream(A).sum();
        if (sum % 2 != 0) return false;

        sum /= 2;
        boolean[] dp = new boolean[sum+1];
        dp[0] = true;
        //for each number, we pick or not pick, we need to know whether these number can form sum
        for (int a : A) {
            for (int i = sum; i > 0; i--) {
                if (i >= a) {
                    dp[i] = dp[i] || dp[i-a];
                }
            }
        }
        
        return dp[sum];
    }
    
    
    public boolean canPartition_TLE(int[] A) {
        int sum = 0;
        for(int i : A) sum += i;
        
        if (sum % 2 != 0) return false;
        
        return helper(A, 0, 0, sum/2);
        
    }
    
    
    private boolean helper(int[] A, int pos, int cur, int target) {
        if (pos >= A.length || cur > target) return false;
        if (cur == target) return true;
        for(int i = pos; i < A.length; i++) {
            if (helper(A, i+1, cur + A[i], target)) return true;
        }
        
        return false;
    }
}
