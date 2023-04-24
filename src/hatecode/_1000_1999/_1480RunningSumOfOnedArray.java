package _1001_1999;

import java.util.*;

public class _1480RunningSumOfOnedArray {
    /*
    1480. Running Sum of 1d Array
    Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).

Return the running sum of nums.

 

Example 1:

Input: nums = [1,2,3,4]
Output: [1,3,6,10]
    */
    public int[] runningSum(int[] A) {
        int n = A.length;
        
        int[] res = new int[n];
        for(int i =0; i< n; i++) {
            res[i] = (i > 0 ? res[i-1] : 0) + A[i];
        }
        
        return res;
    }
}