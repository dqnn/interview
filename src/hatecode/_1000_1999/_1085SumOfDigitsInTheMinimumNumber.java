package hatecode._1000_1999;

import java.util.*;
public class _1085SumOfDigitsInTheMinimumNumber {
/*
1085. Sum of Digits in the Minimum Number
Given an array A of positive integers, let S be the sum of the digits of the minimal element of A.

Return 0 if S is odd, otherwise return 1.

Example 1:

Input: [34,23,1,24,75,33,54,8]
Output: 0
*/
    public int sumOfDigits(int[] A) {
        if(A == null || A.length < 1) return 0;
        
        int min = Arrays.stream(A).min().getAsInt();
        int res = 0;
        while(min > 0) {
            res += min % 10;
            min = min / 10;
        }
        return (res % 2 == 1) ? 0 : 1;
    }
}