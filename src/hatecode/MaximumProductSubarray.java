package hatecode;

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
    //interview friendly, so 
    public int maxProduct2(int[] A) {
        if (A == null || A.length < 1) {
            return 0;
        }
        // store the result that is the max we have found so far
        int res = A[0];
        // imax/imin stores the max/min product of
        // subarray that ends with the current number A[i]
        for(int i = 1, imax = res, imin =res; i< A.length; i++) {
             // AT this point, nums[i] < 0 means imax will become small, so we exchange them. 
            // remember the max product
            if (A[i] < 0) {
                int temp = imax;
                imax = imin;
                imin = temp;
            }
             // max/min product for the current number is either the current number itself
        // or the max/min by the previous number times the current one
            
            imax = Math.max(A[i], imax * A[i]);
            imin = Math.min(A[i], imin * A[i]);
            // the newly computed max value is a candidate for our global result
            res = Math.max(res, imax);
        }
        
        return res;
    }
    //this is transitioal DP
    public int maxProduct_DP(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int[] f = new int[A.length];
        int[] g = new int[A.length];
        f[0] = A[0];
        g[0] = A[0];
        int res = A[0];
        for (int i = 1; i < A.length; i++) {
            f[i] = Math.max(Math.max(f[i - 1] * A[i], g[i - 1] * A[i]), A[i]);
            g[i] = Math.min(Math.min(f[i - 1] * A[i], g[i - 1] * A[i]), A[i]);
            res = Math.max(res, f[i]);
        }
        return res;
      }
}
