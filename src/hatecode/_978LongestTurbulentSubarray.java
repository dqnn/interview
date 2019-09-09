package hatecode;
public class _978LongestTurbulentSubarray {
/*
978. Longest Turbulent Subarray
A subarray A[i], A[i+1], ..., A[j] of A is said to be turbulent if and only if:

For i <= k < j, A[k] > A[k+1] when k is odd, and A[k] < A[k+1] when k is even;
OR, for i <= k < j, A[k] > A[k+1] when k is even, and A[k] < A[k+1] when k is odd.
That is, the subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.

Return the length of a maximum size turbulent subarray of A.

 

Example 1:

Input: [9,4,2,10,7,8,8,1,9]
Output: 5
Explanation: (A[1] > A[2] < A[3] > A[4] < A[5])
*/
    //thinking process: O(n)/O(1)
    
    //so i<k>j or i >k<j will be the key character of the subarray,
    //so we use 3 adjacent integers to make it work, since i will move from 0 to len -1
    public int maxTurbulenceSize(int[] A) {
        if(A == null || A.length < 1) return 0;
        
        int res = 0, cur = 0;
        for(int i = 0; i< A.length; i++) {
            if(i == 0 || A[i] == A[i-1]) cur = 1;
            else if(i == 1 || A[i] > A[i-1] && A[i-1] <A[i-2] || A[i] < A[i-1] && A[i-1] >A[i-2]) cur += 1;
            else cur = 2;
            
            res = Math.max(cur, res);
        }
        return res;
    }
}