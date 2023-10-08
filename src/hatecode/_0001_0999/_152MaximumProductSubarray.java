package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MaximumProductSubarray
 * Date : Aug, 2018
 * Description : TODO
 */
public class _152MaximumProductSubarray {
    /**
     * 152. Maximum Product Subarray
     * For example, given the array [2,3,-2,4],
     the contiguous subarray [2,3] has the largest product = 6.

     time : O(n)
     space : O(1)
     * @param nums
     * @return
     */
    /*
     * interview friendly O(n)/O(1)
     * 
     * the problem is to say: given array A, return max product of a subarray, it may conains negaitive numbers 
     * 
     * compare to max sum of a subarry, this one change is it may become biggest number for two negative numbers 
     * 
     * so you have max min, res means for position i, the max, min value, res is for return value 
     * 
     * so at each position, we have 3 possibilities, 
     *  A[i], max * A[i], min* A[i]
     * 
     * max = max(max, max(max* A[i], min * A[i]))
     * min = min(min, min(max* A[i], min * A[i]))
     * 
     * 
     */
    public int maxProduct(int[] A) {
        if (A == null || A.length < 1) return 0;
        
        int n = A.length;
        
        int res = A[0];
        int max = A[0];
        int min = A[0];
        
        for(int i = 1; i<n; i++) {
            int tmax = max * A[i], tmin = min* A[i];
            max = Math.max(A[i], Math.max(tmax, tmin));
            min = Math.min(A[i], Math.min(tmax, tmin));
                                                                           
            res = Math.max(res, max);
        }
                                        
                                                                           
        return res;
        
        
    }
    //this is transitioal DP, this is easier to understand compare to O(1) space complexity solution 

    /*
     * 
     */
    public int maxProduct_DP(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int[] max = new int[A.length];
        int[] min = new int[A.length];
        max[0] = A[0];
        min[0] = A[0];
        int res = A[0];
        for (int i = 1; i < A.length; i++) {
            max[i] = Math.max(Math.max(max[i - 1] * A[i], min[i - 1] * A[i]), A[i]);
            min[i] = Math.min(Math.min(max[i - 1] * A[i], min[i - 1] * A[i]), A[i]);
            res = Math.max(res, max[i]);
        }
        return res;
      }
}
