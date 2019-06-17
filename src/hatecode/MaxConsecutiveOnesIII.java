package hatecode;
public class MaxConsecutiveOnesIII {
/*
1004. Max Consecutive Ones III
Given an array A of 0s and 1s, we may change up to K values from 0 to 1.

Return the length of the longest (contiguous) subarray that contains only 1s. 

 

Example 1:

Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
Output: 6
Explanation: 
[1,1,1,0,0,1,1,1,1,1,1]
*/
    
    //two pointers
    //first we try best to move right, then get the max length,
    //then we will try to recover 
    public int longestOnes(int[] A, int K) {
        if (A == null || A.length < 1) return 0;
        
        int n = A.length, l = 0, r = -1;
        int res = Integer.MIN_VALUE;
        while(r + 1 < n) {
            while (r + 1 < n && (A[r + 1] == 1 || A[r + 1] == 0 && K > 0)) {
                if (A[r + 1] == 0 && K > 0) K--;
                r++; 
            }
            
            res = Math.max(res, r - l + 1);
            while(l < n && K <= 0) {
                if (A[l] == 0) K++;
                l++;
            }
            
        }
        return res;
    }
    //best but not interview friendly, 
    //it is not easy to understand how left and right pointers move
    public int longestOnes_Best(int[] A, int K) {
        int i = 0, j = 0;
        for (; j < A.length; ++j) {
            if (A[j] == 0) K--;
            if (K < 0 && A[i++] == 0) K++;
        }
        return j - i;
    }
}