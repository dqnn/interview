package hatecode._0001_0999;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ThreeSumSmaller
 * Creator : professorX
 * Date : Sep, 2018
 * Description : 259. 3Sum Smaller
 */
public class _259ThreeSumSmaller {
    /**
Given an array of n integers nums and a target, find the number of index triplets i, j, k 
with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

Example:

Input: nums = [-2,0,1,3], and target = 2
Output: 2 
Explanation: Because there are two triplets which sums are less than 2:
             [-2,0,1]
             [-2,0,3]
Follow up: Could you solve it in O(n2) runtime?

     time : O(n^2);
     space : O(1);


     nums = [-2, 0, 1, 3], and target = 2.

     * @param nums
     * @param target
     * @return
     */
    //thinking process:
    
    // the problem is to state how many combinations that sum of 3 numbers in nums small than
    //target, 
    
    //we use a for and while loop to get the answer
    //
    public int threeSumSmaller(int[] nums, int target) {
        int res = 0;
        //we sort first then we can use two pointers to move
        Arrays.sort(nums);
        // i max =  nums.length - 3 becz left is i + 1, so left max is nums.length - 2
        //right will be last,so we don't need overlap
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            //left is i + 1, right is last one
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] < target) {
                    //we use right-left, since we find all numbers 
                    //between it is correct
                    res += right - left;
                    left++;
                } else right--;
            }
        }
        return res;
    }
}
