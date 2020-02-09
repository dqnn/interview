package hatecode._1000_1999;
public class _1004MaxConsecutiveOnesIII {
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
    
    //thinking process: 
    //two pointers templates
    //first we try best to move right, then get the max length,
    //then we will try to recover, we move to right as right as possible
    //if we played 0->1 too many then we will recover from left, 
    //"remove" 0 from the window by moving l. 
    public int longestOnes(int[] A, int k) {
        if (A == null || A.length < 1) return 0;
        
        int res = 0, l =0, r =0;
        int count = 0;
        while(r < A.length) {
            if(A[r] == 0) count++;
            r++;
            while(count > k) {
                if(A[l] == 0) count--;
                l++;
            }
            res = Math.max(res, r- l);
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