package hatecode._0001_0999;

import java.util.*;
public class _977SquaresOfASortedArray {
/*
977. Squares of a Sorted Array
Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.

 

Example 1:

Input: [-4,-1,0,3,10]
Output: [0,1,9,16,100]

*/
    //thinking process: O(n)/O(1)
    /*
     * the problem is to say: given one sorted integer array,
     * return the array if each element is squared
     * 
     * we use two pointer, one is from left another is from right,
     * find the bigger and place the right most every time
     */
    public int[] sortedSquares(int[] A) {
        int n = A.length;
        int[] res = new int[n];
        int i = 0, j = n -1;
        for(int p = n-1; p >=0; p--) {
            if (Math.abs(A[i]) <= Math.abs(A[j])) {
                res[p] = A[j] * A[j];
                j--;
            } else {
                res[p] = A[i] * A[i];
                i++;
            }
        }
        
        return res;
    }
}