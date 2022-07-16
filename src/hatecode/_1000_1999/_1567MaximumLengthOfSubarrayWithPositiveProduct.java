package hatecode._1000_1999;
public class _1567MaximumLengthOfSubarrayWithPositiveProduct {
/*
1567. Maximum Length of Subarray With Positive Product
Given an array of integers nums, find the maximum length of a subarray where the product of all its elements is positive.

A subarray of an array is a consecutive sequence of zero or more values taken out of that array.

Return the maximum length of a subarray with positive product.

 

Example 1:

Input: nums = [1,-2,-3,4]
Output: 4*/
    
    //thinking process: O(n)/O(1)
    
    /*
     *      1  2  3  -1  2  -3  3  4
     * pos  1  2  3  0   1  6   7  8
     * neg  0  0  0  4   5  2   3  4
     */
    
    //as above example, pos means from 0 - A[i] as last number,
    //the max length of the subarray
    public int getMaxLen(int[] A) {
        if (A == null || A.length < 1) return 0;
        
        int pos = 0, neg = 0;
        int res = 0;
        for(int a: A) {
            if (a == 0) {
                pos =0;
                neg = 0;
            } else if (a > 0) {
                pos++;
                neg = neg == 0? 0: neg+1;
            } else {
                int temp = pos;
                pos = neg ==0 ? 0:neg+1;
                neg = temp+1;
            }
            
            res = Math.max(pos, res);
            
        }
        return res;
    }
}