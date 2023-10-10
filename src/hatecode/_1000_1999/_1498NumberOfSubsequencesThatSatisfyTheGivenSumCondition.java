package hatecode._1000_1999;

import java.util.*;
public class _1498NumberOfSubsequencesThatSatisfyTheGivenSumCondition {
/*
1498. Number of Subsequences That Satisfy the Given Sum Condition
You are given an array of integers nums and an integer target.

Return the number of non-empty subsequences of nums such that the sum of the minimum and maximum element on it is less or equal to target. Since the answer may be too large, return it modulo 109 + 7.

 

Example 1:

Input: nums = [3,5,6,7], target = 9
Output: 4
Explanation: There are 4 subsequences that satisfy the condition.
[3] -> Min value + max value <= target (3 + 3 <= 9)
[3,5] -> (3 + 5 <= 9)
[3,5,6] -> (3 + 6 <= 9)
[3,6] -> (3 + 6 <= 9)
*/
    /*
     * thinking process: O(nlng)/O(n)
     * 
     * there are two keys in the problem;
     * 
     * 1. sort won't bother the results, because we only care subsequence min and max, so it equals to any elements set 
     * 2. [a, b,c,d,e] sorted, a + e <= target, then every sequence with a will be a valid sequence,  for example
     * [a], [a, b], [a,b,c], [a, c] it will be 2^4 = 16 sequences, a is always included 
     * 3. for java, it we use Power, it may have issues on overflow, so we have to preprocess 
     *  
     *  For example, [3,5,6,7], target = 9 is like:
[3]
[3,5]
[3,5,6]
[3,6]

A different order such as [5,7,6,3], target = 9 will be like:
[3]
[3,5]
[6,3,5]
[6,3]

    */
    //thinking process: O(nlgn)/O(n)
    //pow is used to handle 1e9 mod issues, if we use Math.pow(2, r-l), there will be overflow
    public int numSubseq(int[] A, int target) {
        Arrays.sort(A);
        
        int l = 0, r = A.length - 1;
        int mod = (int)1e9+7;
        int res = 0; 
        int[] pows = new int[A.length];
        pows[0] = 1;
        for (int i = 1 ; i < A.length ; ++i) pows[i] = pows[i - 1] * 2 % mod;
        //l = r need count
        while(l <= r) {
            if (A[l] + A[r] > target) r--;
            else {
                // first element will always included, so no + 1
                res = (res + pows[r-l]) % mod;
                l++;
            }
        }
        
        return res;
    }
}