package hatecode;

import java.util.Arrays;

public class ValidTriangleNumber {
/*
611. Valid Triangle Number

Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
Example 1:
Input: [2,2,3,4]
Output: 3
Explanation:
Valid combinations are: 
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
Note:
The length of the given array won't exceed 1000.
The integers in the given array are in the range of [0, 1000].
*/
    //O(n^2), space: O(nlgn) sort will take this space
    //thinking process: find all possible triangles in a array
    //two pointers, how we move the pointers is a little tricky. 
    public int triangleNumber(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        
        Arrays.sort(nums);
        int a = 0, b = a + 1, c = b + 1;
        int res = 0;
        for(int n = 0; n < nums.length;n++) {
            if (nums[n] == 0) continue;
            for(int left = n+1, right= n+2; left< nums.length;) {
                if (right < nums.length && nums[n] + nums[left] > nums[right]) {
                    right++;
                } else {
                    res += (right - 1) - (left+1) + 1;
                    left++;
                }
            }
        }
        return res;
    }
}