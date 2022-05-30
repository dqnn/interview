package hatecode._0001_0999;

import java.util.HashMap;
import java.util.Map;

public class _523ContinuousSubarraySum {
/*
 * 523. Continuous Subarray Sum
Given a list of non-negative numbers and a target integer k, 
write a function to check if the array has a continuous subarray of size 
at least 2 that sums up to the multiple of k, that is, sums up to n*k 
where n is also an integer.

Example 1:
Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
Example 2:
Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
Note:
The length of the array won't exceed 10,000.
You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
 */
    /*
    Suppose sum_i represents the running sum starting from index 0 and ending at i,
once we find a mod that has been seen, say modk, we have:
current one: sum_i = m*k + modk
previous one: sum_j = n*k + modk
Thus,
sum_i - sum_j = (m - n) *k
    */
    //thinking process:
    //if say about sub array, always trying to think from prefix sum
    
    //interview friendly: 
    //
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length < 1) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        //to avoid sum = k * n, then sum = 0, we 
        //like [0,0], k =0, examples, 
        map.put(0, -1);
        int sum = 0;
        for(int i = 0; i< nums.length;i++) {
            sum += nums[i];
            // k can be 0, in that case, 0,-1 can help
            if (k!=0) sum %=k;
            if (map.containsKey(sum)){
                if (i - map.get(sum) > 1) return true;
            } else map.put(sum, i);
        }
        return false;
    }
}