package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MinimumSizeSubarraySum
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : 209. Minimum Size Subarray Sum
 */
public class MinimumSizeSubarraySum {
    /**
Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous 
subarray of which the sum ≥ s. If there isn't one, return 0 instead.

Example: 

Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.
Follow up:
If you have figured out the O(n) solution, try coding another solution of which 
the time complexity is O(n log n). 

     time : O(n)
     space : O(1)

     * @param s
     * @param nums
     * @return
     */
    //O(n) because even 2 loops but inside while is not starting from 0 everytime, so it is constant time, 
    
    //thinking process, the templates for two pointers
    public int minSubArrayLen(int s, int[] nums) {
        // res is the length of subarray
        int res = Integer.MAX_VALUE;
        //left is sliding window left
        int left = 0, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // left is before i's position,
            while (left <= i && sum >= s) {
                // we move pointer left to i so res is the shortest length
                res = Math.min(res, i -left + 1);
                // remove from the sum
                sum -= nums[left++];
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
