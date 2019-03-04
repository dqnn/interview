package hatecode;
import java.util.*;
public class DeleteAndEarn {
/*
740. Delete and Earn
Given an array nums of integers, you can perform operations on the array.

In each operation, you pick any nums[i] and delete it to earn nums[i] points. After, you must delete every element equal to nums[i] - 1 or nums[i] + 1.

You start with 0 points. Return the maximum number of points you can earn by applying such operations.

Example 1:

Input: nums = [3, 4, 2]
Output: 6
*/
    //O(n)/O(1)
     public int deleteAndEarn_DP(int[] nums) {
        int n = 10001;
        int[] values = new int[n];
        for (int num : nums)
            values[num] += num;

        int take = 0, skip = 0;
        for (int i = 0; i < n; i++) {
            int takei = skip + values[i];
            int skipi = Math.max(skip, take);
            take = takei;
            skip = skipi;
        }
        return Math.max(take, skip);
    }
    //O(n)/O(1), interview friendly
   //O(n)/O(1)
     public int deleteAndEarn(int[] nums) {
         int n = nums.length;
         if(n == 0) return 0;
         
         int max = Arrays.stream(nums).max().getAsInt();
         int[] dp = new int[max+1];
         //this is bucket, store same number value sum
         
         int[] sum = new int[max+1];
         sum[0] = 0;
         for(int i = 0; i < n; i++) sum[nums[i]] += nums[i];

         dp[0] = 0; dp[1] = sum[1];
         for(int i = 2; i < max+1; i++) {
             dp[i] = Math.max(dp[i-2] + sum[i], dp[i-1]);
         }
         return dp[max];
     }
}