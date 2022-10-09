package hatecode._1000_1999;
public class _1588SumOfAllOddLengthSubarrays {

    /*
    1588. Sum of All Odd Length Subarrays
Given an array of positive integers arr, return the sum of all possible odd-length subarrays of arr.

A subarray is a contiguous subsequence of the array.

 

Example 1:

Input: arr = [1,4,2,5,3]
Output: 58
Explanation: The odd-length subarrays of arr and their sums are:
[1] = 1
[4] = 4
[2] = 2
[5] = 5
[3] = 3
[1,4,2] = 7
[4,2,5] = 11
[2,5,3] = 10
[1,4,2,5,3] = 15
If we add all these together we get 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
    
    */
    /*
     * thinking process : O(n)/O(1)
     * A =    [1,  4,  2,  5,  3]
     * times=  3   
     * 
     * for each A[i], we have two cases:
     * 1. start with A[i], we have (n-1-i + 1) subarrays, means from i->n-1, we have n-1-i+1 numbers 
     * 
     * 2. not start with A[i],  we have 
     *    left   A[i] * (right)
     * (i-1-0+1) * (n-1 - (i +1) + 1 + 1) subarrays
     * 
     * left at least 1, right can be 0 length, so it means count from i+1 ->n-1, we add 1 last because we have to 
     * include A[i]
     * 
     * add them together will be n - i + i * (n-i) = (n-i)(i+1) subarrays contains A[i],
     * 
     * how many are odds length, then will be (K +1)/2, if K is even, then K/2, if K
     * is odd, then K/2+1, so we just use K + 1 to avoud write if else clause
     * 
     * 
     */
    public int sumOddLengthSubarrays(int[] A) {
        int res = 0;
        int n = A.length;
        for(int i = 0; i< n; i++) {
            res += ((n - i) * (i + 1) + 1)/2 * A[i];
        }
        
        return res;
    }
}