package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MaximumSubarray
 * Creator : professorX
 * Date : Aug, 2018
 * Description : TODO
 */
public class _053MaximumSubarray {
    /**
     * 53. Maximum Subarray
     *
     * Find the contiguous subarray within an array (containing at least one number) 
     * which has the largest sum.

     For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
     the contiguous subarray [4,-1,2,1] has the largest sum = 6.
     
     Follow up: if i want the max length of this value

     * @param nums
     * @return
     */

    // thinking process: O(n)/O(n);
    //dp[i] means max sum of sun array from {nums[0]-->nums[i]}
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // we just need to make sure dp[i] is bigger than dp[i - 1]
            dp[i] = nums[i] + (dp[i - 1] < 0 ? 0 : dp[i - 1]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // time : O(n) space : O(1);
    //interview friendly, so every time, we get max number by two ways:
    //1 current number 
    //2. A[i]  + previous sum
    public int maxSubArray2(int[] nums) {
        int res = nums[0];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // this is easier, so comare SUM(0-i) and nums[i] each time. 
            sum = Math.max(nums[i], sum + nums[i]); //tricky
            res = Math.max(res, sum);
        }
        return res;
    }
}
