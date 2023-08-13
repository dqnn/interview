package hatecode._2000_2999;


import java.util.*;
import java.util.stream.IntStream;

public class _2815MaxPairSumInAnArray {
    
/*
2815. Max Pair Sum in an Array

You are given a 0-indexed integer array nums. You have to find the maximum sum of a pair of numbers from nums such that the maximum digit in both numbers are equal.

Return the maximum sum or -1 if no such pair exists.

 

Example 1:

Input: nums = [51,71,17,24,42]
Output: 88
Explanation: 
For i = 1 and j = 2, nums[i] and nums[j] have equal maximum digits with a pair sum of 71 + 17 = 88. 
For i = 3 and j = 4, nums[i] and nums[j] have equal maximum digits with a pair sum of 24 + 42 = 66.
It can be shown that there are no other pairs with equal maximum digits, so the answer is 88.
Example 2:

Input: nums = [1,2,3,4]
Output: -1
*/

   /*
    * thinking process: O(nlgm)/O(1), m is the avergae count of digits in elements in A, m = logA[i] base as 10


      the problem is to say: given one integer array A, find the max sum of two numbers in array, the two number have same max 
      digit 
      for example [51,71,17,24,42],  71,17 --> 88, and 71 and 17 have max digit as 7

      
    */
    public int maxSum(int[] A) {
        if (A == null || A.length < 1) return 0;
        
        //maxA[i] means maxA[6] means suppose we visit A[i], from 0->i, the A[1]..A[i], the max value which its digit contains 
        //a '6' as its max digit

        /*
         * [51,71,17,24,42] 
         * --- [-1,-1,-1,-1,42,51,-1,71,-1,-1]
         */
        int[] maxA = new int[10];
        Arrays.fill(maxA, -1);
        
        int res = Integer.MIN_VALUE;
        for(int a : A) {
            int max = getMax(a) -'0';
            if(maxA[max] != -1) {
                res = Math.max(res, maxA[max] + a);
            }
            maxA[max] = Math.max(maxA[max], a);
        }
        
        return res == Integer.MIN_VALUE? -1: res;
    }
    
    
    public int maxSum_BF(int[] A) {
        if (A == null || A.length < 1) return 0;
        
        int res = Integer.MIN_VALUE;
        for(int i = 0; i< A.length; i++) {
            for(int j = i+1; j < A.length; j++) {
                int max =A[i] +A[j];
                if(max > res && getMax(A[i]) == getMax(A[j])) {
                    res = max;
                }
            }
        }
        
        return res == Integer.MIN_VALUE ? -1: res;
    } 
    
    private char getMax(int a) {
        String temp = String.valueOf(a);
        Character[] chs = new Character[temp.length()];
        IntStream.range(0, chs.length).forEach(e->chs[e] = temp.charAt(e));
        Arrays.sort(chs, (i,j)->(Integer.compare(j, i)));
        
        return chs[0];
    }
}