package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : UglyNumberII
 * Date : Sep, 2018
 * Description : 264. Ugly Number II
 */
public class _264UglyNumberII {
    /**
     *
Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 

Example:

Input: n = 10
Output: 12
Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
Note:  

1 is typically treated as an ugly number.
n does not exceed 1690.
     *
     * @param n
     * @return
     */
    //thinking ptocess: Math problem, so we want to know a number is 2,3,5 factors, given n, we want to know n-th number
    //so we start from 1, and we use that number to multiple 2,3,5, every loop on i->(1,n) we use Math.min to get smallest number
    
    //we use 3 pointers to say index2, index3, index5 so if nums[i] equals to the number multiple 2, 3 or 5, then we 
    //move pointer to next integer
    public int nthUglyNumber(int n) {
        int[] nums = new int[n];
        int index2 = 0, index3 = 0, index5 = 0;
        nums[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            nums[i] = Math.min(nums[index2] * 2, Math.min(nums[index3] * 3, nums[index5] * 5));
            if (nums[i] == nums[index2] * 2) index2++;
            if (nums[i] == nums[index3] * 3) index3++;
            if (nums[i] == nums[index5] * 5) index5++;
        }
        return nums[n - 1];
    }
}
