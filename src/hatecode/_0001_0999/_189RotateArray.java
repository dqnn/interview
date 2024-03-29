package hatecode._0001_0999;

import java.util.Arrays;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : RotateArray
 * Creator : professorX
 * Date : Sep, 2018
 * Description : 189. Rotate Array
 */
public class _189RotateArray {
    /**
     * Rotate an array of n elements to the right by k steps.

     For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] 
     is rotated to [5,6,7,1,2,3,4].

     Note:
     Try to come up as many solutions as you can, there are at least 3 different ways to solve this 
     problem.

     * @param nums
     * @param k
     */
    //time : O(n)  space : O(n), this use extra space to store the array
    public void rotate(int[] nums, int k) {
        int[] temp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            // i + k % len means we move elements after k move to front
            temp[(i + k) % nums.length] = nums[i];
        }
        //temp already the corrct answer, just copy here
       System.arraycopy(temp, 0, nums, 0, nums.length);
    }

    //time : O(n)  space : O(1)

    /**
     Original List                   : 1 2 3 4 5 6 7
     After reversing all numbers     : 7 6 5 4 3 2 1
     After reversing first k numbers : 5 6 7 4 3 2 1
     After revering last n-k numbers : 5 6 7 1 2 3 4 --> Result
     * @param nums
     * @param k
     */
    
    //interview friendly
    
    //so one way rotate the array is store somewhere but space complexity will increase
    //considering in place exchange
    public void rotate2(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
    
    // time exceeded
    public void rotate4(int[] nums, int k) {
        //edge case
        if (nums == null || nums.length < 1) {
            return;
        }
        
        int len = nums.length;
        int p = k;
        while (p > 0) {
            int temp = nums[len - 1];
            for(int i = len - 1; i >= 1; i--) {
                nums[i] = nums[i - 1];
            }
            nums[0] = temp;
            p--;
        };
    }
}
