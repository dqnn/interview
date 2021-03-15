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
    
    //thinking process: the problem is to say: given an integre array A
    //and a threshold, need to figure out the smallest divisor which the sum
    //of the result A[i]/divisor cannot exceed threshold
    
    // 1 + (i - 1) / m means always find the upper integer of i
    //we should BS to find the minimal result because all positive integers, 
    //if we choose small one then result will exceed threshold, then move l means we want
    //a bigger divisor
    public static int smallestDivisor(int[] A, int threshold) {
        
        int l = 1, r = 1 + Arrays.stream(A).max().getAsInt();
        while (l < r) {
            int m = l + (r -l)/2, sum = 0;
            
            for (int i : A) sum += 1 + (i - 1) / m;
            
            if (sum > threshold) l = m + 1;
            else r = m;
        }
        
        return l;
    }
    
    public static void main(String[] args) {
        //should be 5
        System.out.println(smallestDivisor(new int[] {1,2,5,9}, 6));
        //should be 44
        System.out.println(smallestDivisor(new int[] {44,22,33,11,1}, 5));
    }
}