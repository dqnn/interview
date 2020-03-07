package hatecode._0001_0999;
import java.util.*;

public class _581ShortestUnsortedContinuousSubarray {
/*
581. Shortest Unsorted Continuous Subarray
Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
*/
    public int findUnsortedSubarray(int[] nums) {
        //edge case
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        
        int len = nums.length;
        int[] numsSorted = Arrays.copyOf(nums, len);
        Arrays.sort(numsSorted);
        
        int l = 0, r = len - 1;
        
        while ((l <= len - 1) && nums[l] == numsSorted[l]) l++;
        while (r >= 0 && nums[r] == numsSorted[r]) r --;
        
        return r > l ? r -l + 1 : 0;
        
    }
}