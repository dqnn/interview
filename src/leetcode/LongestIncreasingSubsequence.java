package leetcode;

import java.util.Arrays;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LongestIncreasingSubsequence
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : 300. Longest Increasing Subsequence
 */
public class LongestIncreasingSubsequence {
    /**
     * Given an unsorted array of integers, find the length of longest increasing subsequence.

     For example,
     Given [10, 9, 2, 5, 3, 7, 101, 18],
     The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4.
     Note that there may be more than one LIS combination, it is only necessary for you to return the length.

     [10, 9, 2, 5, 3, 7, 101, 18]

     res == size

     10 res = 0 i = 0 j = 0
     9 res = 1 i = 0 j = 0 mid = 0
     2 res = 1 i = 0 j = 0 mid = 0
     5 res = 1 i = 1 j = 1 mid = 0
     3 res = 2
     7 res = 2 i = 2 j = 2 mid = 1
     101 res = 3 i = 2 j = 3 mid = 1
     18 res = 4 i = 3 j = 3 mid = 3


     i, j 相当于tails的起点，终点
     tails : [2,3,7,18]

     time : O(nlogn)
     space : O(n)

     * @param nums
     * @return
     */
    
    //// the idea  is to maintain a list, and for each element in n, we want to find a position in 
    // tail to insert, it is more like insert sort, 
    // the way to identify the element which should be longest increasing sequence is the position for 
    //res is the variabe to maintain the insert position for each element
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int[] tails = new int[nums.length];
        int res = 0;
        for (int num : nums) {
            int i = 0, j = res;
            // binary search here can help to find which position for num
            while (i != j) {
                int mid = (i + j) / 2;
                if (tails[mid] < num) {
                    i = mid + 1;
                } else {// please not here should be >=
                    j = mid;
                }
            }// while end so we find the position to be insert
            // we insert the elements in position i maybe replace
            tails[i] = num;
            // so if i == res, which means num is a correct in last longest sequence
            if (i == res) ++res;
        }
        return res;
    }
    
    // this is interview friendly, and follow up: 
    public int lengthOfLIS2(int[] n) {
        if (n == null || n.length < 1) {
            return 0;
        }
        int len = n.length;
        
        // dp[i] means if nums[i] as the last elements, the longest increasing sequence length
        int[] dp = new int[len];
        // at least 1 number
        Arrays.fill(dp, 1);
        
        // set max = 1, edge case like [2, 2] it will not go through the two loops,
        //also for [0] cases, we also need max to be 1 or else we have add if (len == 1) return 1;
        int max = 1;
        for(int i = 0; i < len; i++) {
            for(int j = 0; j < i; j++) {
                if (n[j] < n[i]) {
                    // means nums[i] could be nums[j] next element, that's why we need compare to n[j]
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    max = Math.max(dp[i], max);
                }
            }
        }
        return max;
    }
}
