package hatecode._1000_1999;
public class _1186MaximumSubarraySumWithOneDeletion {
/*
1186. Maximum Subarray Sum with One Deletion
Given an array of integers, return the maximum sum for a non-empty subarray (contiguous elements) with at most one element deletion. In other words, you want to choose a subarray and optionally delete one element from it so that there is still at least one element left and the sum of the remaining elements is maximum possible.

Note that the subarray needs to be non-empty after deleting one element.

 

Example 1:

Input: arr = [1,-2,0,3]
Output: 4
*/
    /* thinking process: O(n)/O(n)
     * the problem is to say, given one array, for each subarray, return max subarray
     * sum if we remove one element
     * 
     * suppose there is one subarray, we can cut there, then it will become 2 arrays,
     * then we can calulate first one and then 2nd one, then we sum together
     * 
     * l[i] means from 0->i, at position i, the max sum for previous 0->i.
     * r[i] means from n-1-> i, scan from right to i, the max sum 
     * 
     * so we can try all cases, 
     */
    public int maximumSum(int[] A) {
        int n = A.length;
        int[] l = new int[n], r = new int[n];
        int max = A[0];
        l[0] = A[0];
        for (int i = 1; i < n; i++) {
            l[i] = Math.max(A[i], l[i - 1] + A[i]);
            max = Math.max(max, l[i]);
        }
        r[n - 1] = A[n - 1];
        for (int i = n - 2; i >= 0; i--)
            r[i] = Math.max(A[i], r[i + 1] + A[i]);
        
        //start from index 1 and stop at n-2 because we suppose the element we removed is not 
        //start or end element of a subarray

        for (int i = 1; i < n - 1; i++)
            max = Math.max(max, l[i - 1] + r[i + 1]);
        return max;
    }

    //O(n)/O(1)
    public int maximumSum_DP(int[] arr) {
        int res = arr[0];
        int dp = arr[0];
        int dpExclude = 0;
        for (int i = 1; i < arr.length; i++) {
            dpExclude = Math.max(dp, dpExclude + arr[i]);
            dp = Math.max(dp + arr[i], arr[i]);
            res = Math.max(Math.max(dpExclude, dp), res);
        }

        return res;
    }
}