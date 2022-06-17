package hatecode._0001_0999;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ContainsDuplicate
 * Creator : professorX
 * Date : Oct, 2017
 * Description : 217. Contains Duplicate
 * Given an array of integers, find if the array contains any duplicates.

Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.

Example 1:

Input: [1,2,3,1]
Output: true
Example 2:

Input: [1,2,3,4]
Output: false
Example 3:

Input: [1,1,1,3,3,4,3,2,4,2]
Output: true

 */
public class _217ContainsDuplicate {

    // time : O(n) space : O(n)
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            // set.add return false if previous exists
            if (!set.add(nums[i])) return true;
        }
        return false;
    }

    // time : O(nlogn) space : O(1)
    public boolean containsDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) return true;
        }
        return false;
    }

    public boolean containsDuplicate3(int[] nums) {
        // edge case
        if (nums == null || nums.length <= 1) {
            return false;
        }

        int len = nums.length - 1;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i <= len; i++) {
            set.add(nums[i]);
        }

        return len != (set.size() - 1);
    }
}
