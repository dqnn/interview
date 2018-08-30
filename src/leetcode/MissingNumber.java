package leetcode;



/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MissingNumber
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : 268. Missing Number
 */
public class MissingNumber {
    /**
     * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

     For example,
     Given nums = [0, 1, 3] return 2.

     time : O(n)
     space : O(1)

     * @param nums
     * @return
     */

    // cool solution
    public int missingNumber(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            res ^= i ^ nums[i];
        }
        return res;
    }

    public int missingNumber2(int[] nums) {
        int expectedSum = (nums.length + 1) / 2 * nums.length;
        int actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }
        return expectedSum - actualSum;
    }
    
    // 
    public int missingNumber3(int[] nums) {
        //edge case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = 0;
        for(int i = 0; i < nums.length; i++) {
           res += i - nums[i]; 
        }
        
        res += nums.length;
        
        return res;
    }

}
