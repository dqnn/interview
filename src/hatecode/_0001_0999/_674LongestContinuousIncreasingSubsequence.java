package hatecode._0001_0999;
public class _674LongestContinuousIncreasingSubsequence {
/*
674. Longest Continuous Increasing Subsequence
Given an unsorted array of integers nums, return the length of the longest continuous increasing subsequence (i.e. subarray). The subsequence must be strictly increasing.

A continuous increasing subsequence is defined by two indices l and r (l < r) such that it is [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] and for each l <= i < r, nums[i] < nums[i + 1].

 

Example 1:

Input: nums = [1,3,5,4,7]
Output: 3
Explanation: The longest continuous increasing subsequence is [1,3,5] with length 3.
Even though [1,3,5,7] is an increasing subsequence, it is not continuous as elements 5 and 7 are separated by element
4.
*/
    /*
     * thinking process: O(n)/O(1)
     * the problem is to say: given one integer array A, you need to find the max
     * length of increasing sub Array in A
     */
    public int findLengthOfLCIS(int[] A) {
        if (A == null || A.length < 1) return 0;
        
        int res = 1;
        int cur = res;
        for(int i =1; i <A.length; i++) {
            if (A[i] > A[i-1]) {
                cur++;
                
            } else {
                cur = 1;
            }
            
            res = Math.max(res, cur);
        }
        
        return res;
    }
}