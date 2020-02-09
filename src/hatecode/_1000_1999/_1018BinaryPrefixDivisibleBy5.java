package hatecode._1000_1999;

import java.util.*;
public class _1018BinaryPrefixDivisibleBy5 {
/*
1018. Binary Prefix Divisible By 5
Given an array A of 0s and 1s, consider N_i: the i-th subarray from A[0] to A[i] interpreted as a binary number (from most-significant-bit to least-significant-bit.)

Return a list of booleans answer, where answer[i] is true if and only if N_i is divisible by 5.

Example 1:

Input: [0,1,1]
Output: [true,false,false]
*/
    //thinking process:
    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> res = new ArrayList<>();
        if (A == null || A.length < 1) return res;
        long sum = 0;
        for(int i =0; i < A.length; i++) {
            sum = sum * 2;
            sum =  sum % 5  + A[i] % 5;
            res.add((sum) % 5  == 0 ? true :false);
            sum = sum % 5;
        }
        return res;
    }
}