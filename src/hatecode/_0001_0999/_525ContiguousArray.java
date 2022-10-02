package hatecode._0001_0999;

import java.util.*;
public class _525ContiguousArray {
/*
525. Contiguous Array
Given a binary array, find the maximum length of a 
contiguous subarray with equal number of 0 and 1.

Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
*/
   
    /*
     * interview friendly, thinking process : O(n)/O(n)
     * 
     * the problem is to say: 
     * 
     * given one array only have 0 and 1, need to find one subarray which has same count of 
     * 0 and 1, return the max length of the sub array.
     * 
     * two sum, when meeting 0 then use -1, so we are looking for one subarray which sum = 0.
     * 
     * Sj -Si = 0, so Sj = Si
     */
    public int findMaxLength(int[] A) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        int res = Integer.MIN_VALUE;
        for(int i = 0; i<A.length; i++) {
            sum += A[i] == 0 ? -1 : 1;
            
            if(map.containsKey(sum)) {
                res = Math.max(res, i - map.get(sum));
            } else map.put(sum, i);
        }
               
        return res == Integer.MIN_VALUE ? 0 : res;
    }
    /*
   dp[i][0] means from nums 0->i-1 0 count
   dp[i][1] means from nums 0->i-1 1 count
     */
    public int findMaxLength_DP(int[] nums) {
        int n = nums.length, res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int[][] dp = new int[n + 1][2];
        for (int i = 1; i < dp.length; i++) {
            if (nums[i - 1] == 0) {
                dp[i][0] = dp[i - 1][0] + 1;
                dp[i][1] = dp[i - 1][1];
            } else {
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = dp[i - 1][1] + 1;
            }
            //length = count0 * 2
            if (dp[i][0] == dp[i][1]) res = Math.max(res, dp[i][0] * 2);
            else {
                int diff = dp[i][1] - dp[i][0];
                if (map.containsKey(diff)) res = Math.max(res, 2 * (dp[i][0] - dp[map.get(diff)][0]));
                else map.put(diff, i);
            }
        }
        return res;
    }
}