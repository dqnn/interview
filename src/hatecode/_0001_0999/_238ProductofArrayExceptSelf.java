package hatecode._0001_0999;

/**
 * File Name : ProductofArrayExceptSelf
 * Date : Sep, 2018
 * Description : TODO
 */
public class _238ProductofArrayExceptSelf {
    /**
     * 238. Product of Array Except Self
Given an array nums of n integers where n > 1,  return an array output such that output[i] is 
equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count 
as extra space for the purpose of space complexity analysis.)
     * For example, given [1,2,3,4], return [24,12,8,6].

     time : O(n);
     space : O(n);
     * @param nums
     * @return
     */
    //this is also interesting solution, so 
    //[1,2,3,4], res=[1,0,0,0], res from 1, then res[i] = res[i-1] * nums[i-1] which means
    // for res, every position it his left subarray product,
    //then second scan, we multiple the right part.
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        //res[i] = A[i-1]*...* A[0], res[0] = 1, res[1] = res[0] * A[1-1] 
        int[] res = new int[nums.length];
        
        res[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        int right = 1;
        //[1,2,3,4]-> [1, 1, 2, 6],so here we did not change last number, 
        //from len -2, we can see ,right = nums[i+1]...nums[len - 1]
        //right = all product from i + 1->len
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }
    
 /*
  * thinking process: O(n)/O(n)
  *  this is pretty brilliant solution that two visits solve this problem
  *  
  *  
  */
    public int[] productExceptSelf2(int[] nums) {
        //edge case
        if (nums == null || nums.length < 1) {
            return null;
        }
        
        int len = nums.length - 1;
        int[] res = new int[len + 1];
        //res[i] = nums[0] * nums[1]... nums[i - 1] 
        for (int i = 0, temp = 1; i <= len; i++) {
            res[i] = temp;
            temp = temp * nums[i];
        }
        // res[i] = res[i] * 1, so we  can multiple back
        for (int i = len, temp = 1; i >= 0; i--) {
            res[i] = res[i] * temp;
            temp = temp * nums[i];
        }
        
        return res;
    }
    
    //how about DP? 
}
