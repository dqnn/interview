package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ProductofArrayExceptSelf
 * Creator : duqiang
 * Date : Sep, 2017
 * Description : TODO
 */
public class ProductofArrayExceptSelf {
    /**
     * 238. Product of Array Except Self
     * For example, given [1,2,3,4], return [24,12,8,6].

     time : O(n);
     space : O(n);
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }
    
 // this is pretty brilliant solution that two visits solve this problem
    public int[] productExceptSelf2(int[] nums) {
        //edge case
        if (nums == null || nums.length < 1) {
            return null;
        }
        
        int len = nums.length - 1;
        int[] res = new int[len + 1];
        for (int i = 0, temp = 1; i <= len; i++) {
            res[i] = temp;
            temp = temp * nums[i];
        }
        
        for (int i = len, temp = 1; i >= 0; i--) {
            res[i] = res[i] * temp;
            temp = temp * nums[i];
        }
        
        return res;
    }
    
    //how about DP? 
}
