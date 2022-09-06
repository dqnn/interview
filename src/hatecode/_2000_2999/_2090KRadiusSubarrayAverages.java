package hatecode._2000_2999;

import java.util.*;
public class _2090KRadiusSubarrayAverages {
/*
2090. K Radius Subarray Averages
You are given a 0-indexed array nums of n integers, and an integer k.

The k-radius average for a subarray of nums centered at some index i with 
Build and return an array avgs of length n where avgs[i] is the k-radius 
average for the subarray centered at index i.

The average of x elements is the sum of the x elements divided by x, using 
integer division. The integer division truncates toward zero, which means 
losing its fractional part.

For example, the average of four elements 2, 3, 1, and 5 is (2 + 3 + 1 + 5) / 4 = 11 / 4 = 2.75, which truncates to 2.
 

Example 1:


Input: nums = [7,4,3,9,1,8,5,2,6], k = 3
Output: [-1,-1,-1,5,4,4,-1,-1,-1]
*/
    /*
     * thinking process: O(n)/O(n)
     * 
     * the problem is to say given a windows size, 2*k + 1, returan avg value 
     * 
     * others cells as -1.
     * 
     * no more technical 
     */
    public int[] getAverages_BF(int[] A, int k) {
        if (k == 0) return A;
        int n = A.length;
        long[] sum = new long[n];
        int[] res = new int[n];
        
        Arrays.fill(res, -1);
        
        sum[0]= A[0];
        for(int i = 1; i < n; i++) {
            sum[i] = sum[i-1] + A[i];
        }
        
        for(int i= k; i < A.length - k;i++) {
            long temp = (sum[i+k] - sum[i-k] + A[i - k])/(2*k + 1);
            res[i] = (int)temp;
        }
        
        return res;
    }
    
    
    public int[] getAverages(int[] A, int k) {
        
        int n = A.length;
        int[] res =new int[n];
        int w = 2*k + 1;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += A[i];
            res[i] = -1;
            if (i + 1 >= w) {
                res[i - k] = (int) (sum / w);
                sum -= A[i + 1 - w];
            }
        }
        return res;
    }
    
}