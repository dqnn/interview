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
     * 1. order does not matter for this problem becuase we only care about 
     * min and max, 
     * take A[i] as the minimum element in one subsequence, 
     * suppose the number of all other elements that is <= target - A[i] 
     * is k, then the total number subsequences these elements makes is `2^k`. 
     * it is obvious that we only need to find the number `k`,
     *  we don;t really need to know how those `k` elements sits together 
     *  with A[i].
     *  
     *  For example, [3,5,6,7], target = 9 is like:
[3]
[3,5]
[3,5,6]
[3,6]

A different order such as [7,6,3,5], target = 9 will be like:
[3]
[3,5]
[6,3,5]
[6,3]

    */
    //pow is used to handle 1e9
    public int numSubseq(int[] A, int target) {
        Arrays.sort(A);
        
        int l = 0, r = A.length - 1;
        int mod = (int)1e9+7;
        int res = 0; 
        int[] pows = new int[A.length];
        pows[0] = 1;
        for (int i = 1 ; i < A.length ; ++i)
            pows[i] = pows[i - 1] * 2 % mod;
        while(l <= r) {
            if (A[l] + A[r] > target) r--;
            else {
                res = (res + pows[r-l]) % mod;
                l++;
            }
        }
        
        return res;
    }
}