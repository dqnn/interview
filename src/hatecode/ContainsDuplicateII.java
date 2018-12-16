package hatecode;

import java.util.HashMap;
import java.util.Map;

/**
 * Project Name : Leetcode Package Name : leetcode File Name :
 * ContainsDuplicateII Creator : duqiang Date : Aug, 2018 Description : 219.
 * 
 * 
 * Contains Duplicate II Given an array of integers and an integer k, find out
 * whether there are two distinct indices i and j in the array such that nums[i]
 * = nums[j] and the absolute difference between i and j is at most k.
 * 
 * Example 1:
 * 
 * Input: nums = [1,2,3,1], k = 3 Output: true Example 2:
 * 
 * Input: nums = [1,0,1,1], k = 1 Output: true Example 3:
 * 
 * Input: nums = [1,2,3,1,2,3], k = 2 Output: false
 */
public class ContainsDuplicateII {
    //time : O(n) space : O(n)
    // so suppose we have duplicate number, first it was not in map,
    // latter we found it and since num[i] == num[j], so we can get previous index
    // easily
    //
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if ((i - map.get(nums[i])) <= k) {
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;
    }

    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        int len = nums.length - 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= len; i++) {
            // map.put() will return previous value.
            Integer oldIndex = map.put(nums[i], i);
            if (oldIndex != null && i - oldIndex <= k) {
                return true;
            }
        }

        return false;
    }
}
