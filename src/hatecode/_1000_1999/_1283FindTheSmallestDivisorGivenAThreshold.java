package hatecode._1000_1999;

import java.util.*;
public class _1283FindTheSmallestDivisorGivenAThreshold {
/*
1283. Find the Smallest Divisor Given a Threshold
Given an array of integers nums and an integer threshold, we will choose a positive integer divisor, divide all the array by it, and sum the division's result. Find the smallest divisor such that the result mentioned above is less than or equal to threshold.

Each result of the division is rounded to the nearest integer greater than or equal to that element. (For example: 7/3 = 3 and 10/2 = 5).

It is guaranteed that there will be an answer.

 

Example 1:

Input: nums = [1,2,5,9], threshold = 6
Output: 5
*/
    //thinking process: O(nlgn/O(1))
    
    //thinking process: 
    
    public int smallestDivisor(int[] A, int threshold) {
        
        int left = 1, right = 1 + Arrays.stream(A).max().getAsInt();
        while (left < right) {
            int m = (left + right) / 2, sum = 0;
            for (int i : A)
                sum += (i + m - 1) / m;
            if (sum > threshold)
                left = m + 1;
            else
                right = m;
        }
        return left;
    }
}