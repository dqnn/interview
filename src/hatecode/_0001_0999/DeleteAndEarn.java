package hatecode._0001_0999;
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
/*
If we sort all the numbers into buckets indexed by these numbers, this is essentially asking you to repetitively take an bucket while giving up the 2 buckets next to it. (the range of these numbers is [1, 10000])

The optimal final result can be derived by keep updating 2 variables skip_i, take_i, 
which stands for:
skip_i : the best result for sub-problem of first (i+1) buckets from 0 to i, 
while you skip the ith bucket.
take_i : the best result for sub-problem of first (i+1) buckets from 0 to i, 
while you take the ith bucket.

DP formula:
take[i] = skip[i-1] + values[i];
skip[i] = Math.max(skip[i-1], take[i-1]);
take[i] can only be derived from: if you skipped the [i-1]th bucket, and you take bucket[i].
skip[i] through, can be derived from either take[i-1] or skip[i-1], whatever the bigger;
 */
     public int deleteAndEarn_DP(int[] nums) {
        int n = 10001;
        int[] sum = new int[n];
        Arrays.stream(nums).forEach(e->sum[e] += e);

        int take = 0, skip = 0;
        for (int i = 0; i < n; i++) {
            int takei = skip + sum[i];
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
         //dp[i] means 
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