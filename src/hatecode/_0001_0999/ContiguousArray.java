package hatecode._0001_0999;

import java.util.*;
public class ContiguousArray {
/*
525. Contiguous Array
Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
*/
    public int findMaxLength_BF(int[] nums) {
        int maxlen = 0;
        for (int start = 0; start < nums.length; start++) {
            int zeroes = 0, ones = 0;
            for (int end = start; end < nums.length; end++) {
                if (nums[end] == 0) {
                    zeroes++;
                } else {
                    ones++;
                }
                if (zeroes == ones) {
                    maxlen = Math.max(maxlen, end - start + 1);
                }
            }
        }
        return maxlen;
    }
    
    public int findMaxLength(int[] A) {
        if (A == null || A.length < 2) return 0;
        int n = A.length;
        for(int i = 0; i<n; i++) {
            if (A[i] == 0) A[i] = -1;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0, max = 0;
        for(int i = 0; i< n; i++) {
            sum += A[i];
            if (map.containsKey(sum)) {
                max = Math.max(max, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return max;
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