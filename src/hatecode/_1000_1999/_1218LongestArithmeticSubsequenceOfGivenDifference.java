package hatecode._1000_1999;

import java.util.*;
public class _1218LongestArithmeticSubsequenceOfGivenDifference {
/*
1218. Longest Arithmetic Subsequence of Given Difference
Given an integer array arr and an integer difference, return the length of the longest subsequence in arr which is an arithmetic sequence such that the difference between adjacent elements in the subsequence equals difference.

 

Example 1:

Input: arr = [1,2,3,4], difference = 1
Output: 4
*/
    //thinking process: O(n)/O(n)
    
    //the problem is to say, given one integer array and one integer diff, we need to find 
    //a sequence in this array, each element has same diff for its adjacent elements
    
    //we use a map to calculate how many elements before current elements, value is the count
    public int longestSubsequence(int[] A, int diff) {
        if (A == null || A.length < 1) return 0;
        
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for(int n : A) {
            int val = map.getOrDefault(n - diff, 0) + 1;
            map.put(n, val);
            res = Math.max(res, val);
        }
        return res;
    }
}