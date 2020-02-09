package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name : Leetcode

 * Package Name : leetcode
 * File Name : FindAllDuplicatesinanArray
 * Creator : duqiang
 * Date : June, 2018
 * Description : TODO
 */
/*
 * LeetCode 442
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements that appear twice in this array.

Could you do it without extra space and in O(n) runtime?

Example:
Input:
[4,3,2,7,8,2,3,1]

Output:
[2,3]
 */

/*
 *  use index as indicator to store whether visited before or not
 *  O(n), O(1)
 */
class FindAllDuplicatesinArray {
    // so should be sensitive about the index,if it is within context of n, then we should consider 
    // take advantage of the value to be index. 
    public List<Integer> findDuplicates(int[] nums) {
        //edge case
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length < 2) {
            return res;
        }
        
        int len = nums.length;
        // try to use starting from 0 always and use the i+1. which is better than 1 since you
        // will ignore some elements
        for(int i = 0; i <= len - 1; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) {
                nums[index] = 0 - nums[index];
            } else {
                res.add(Math.abs(nums[i]));
            }
            
        }
        
        return res;
    }
}