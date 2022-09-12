package hatecode._1000_1999;

import java.util.*;
public class _1991FindTheMiddleIndexInArray {
/*
1991. Find the Middle Index in Array
Given a 0-indexed integer array nums, find the leftmost middleIndex (i.e., the smallest amongst all the possible ones).

A middleIndex is an index where nums[0] + nums[1] + ... + nums[middleIndex-1] == nums[middleIndex+1] + nums[middleIndex+2] + ... + nums[nums.length-1].

If middleIndex == 0, the left side sum is considered to be 0. Similarly, if middleIndex == nums.length - 1, the right side sum is considered to be 0.

Return the leftmost middleIndex that satisfies the condition, or -1 if there is no such index.

 

Example 1:

Input: nums = [2,3,-1,8,4]
Output: 3
*/
    /*
     * thinking process: O(n)/O(1);
     * 
     * the problem is to say, given one array, find one elment in the array,
     * sum(element left) = = sum (element right), element itself is not included
     * 
     */
    public int findMiddleIndex(int[] A) {
        if (A == null || A.length < 1) return -1;
        
        int sum = Arrays.stream(A).sum();
        int lsum = 0;
        for(int i = 0; i<A.length; i++) {
            if(lsum == sum - lsum - A[i]) return i;
            lsum += A[i];
        }
        
        return -1;
    }
}