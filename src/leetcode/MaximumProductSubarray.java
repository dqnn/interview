package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MaximumProductSubarray
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : TODO
 */
public class MaximumProductSubarray {
    /**
     * 152. Maximum Product Subarray
     * For example, given the array [2,3,-2,4],
     the contiguous subarray [2,3] has the largest product = 6.

     time : O(n)
     space : O(1)
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = nums[0];
        int min = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp = max;
            max = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);
            min = Math.min(Math.min(min * nums[i], temp * nums[i]), nums[i]);
            res = Math.max(res, max);
        }
        return res;
    }
    
    public int maxProduct2(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        // store the result that is the max we have found so far
        int res = nums[0];
        // imax/imin stores the max/min product of
        // subarray that ends with the current number A[i]
        for(int i = 1, imax = res, imin =res; i< nums.length; i++) {
             // AT this point, nums[i] < 0 means imax will become small, so we exchange them. 
            // remember the max product
            if (nums[i] < 0) {
                int temp = imax;
                imax = imin;
                imin = temp;
            }
             // max/min product for the current number is either the current number itself
        // or the max/min by the previous number times the current one
            
            imax = Math.max(nums[i], imax * nums[i]);
            imin = Math.min(nums[i], imin * nums[i]);
            // the newly computed max value is a candidate for our global result
            res = Math.max(res, imax);
        }
        
        return res;
    }
}
