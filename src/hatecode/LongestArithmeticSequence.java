package hatecode;

import java.util.*;
public class LongestArithmeticSequence {
/*
1027. Longest Arithmetic Sequence
Given an array A of integers, return the length of the longest arithmetic subsequence in A.

Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k] with 0 <= i_1 < i_2 < ... < i_k <= A.length - 1, and that a sequence B is arithmetic if B[i+1] - B[i] are all the same value (for 0 <= i < B.length - 1).

Example 1:

Input: [3,6,9,12]
Output: 4

    
    */
    // O(n^2)/O(n), 
    //thinking process: given an array, find the longest Arithmetic sequence length
    //we used a map to store diff<->length of arithmetic sequence, so we use two pointers i and j, (j < i, i =1,2..n-1), 
    //map[i] means as A[i] the end of the sequence, the length, so each i we will have a map, for each j from 0, i-1, we would 
    //check each diff, for each j compare, we would get length as A[i],so for position i, it would have two possible options, 
    //one is previos compared length, another is compare to current j which is like dp[diff][i] = max(dp[diff][i], dp[diff][j] + 1)
    //[20,1,15,3,10,5,8], 
    public int longestArithSeqLength(int[] A) {

        int n = A.length;
        int res = 2;
        // key is diff, value is A[j] as last element Arithmetic sequence length
        Map<Integer, Integer>[] map = new HashMap[n];
        for (int i = 0; i < n; i++)
            map[i] = new HashMap<>();

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int diff = A[i] - A[j];
                int len = 2;
                if (map[j].containsKey(diff)) {
                    len = map[j].get(diff) + 1;
                }

                int cur = map[i].getOrDefault(diff, 0);
                map[i].put(diff, Math.max(len, cur));
                res = Math.max(res, map[i].get(diff));
            }
        }
        return res;
    }
}