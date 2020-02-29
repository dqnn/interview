package hatecode._0001_0999;
public class _487MaxConsecutiveOnesII {
/*
487. Max Consecutive Ones II
Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.

Example 1:
Input: [1,0,1,1,0]
Output: 4
Explanation: Flip the first zero will get the the maximum number of consecutive 1s.
    After flipping, the maximum number of consecutive 1s is 4.
*/
    //same as Max Consecutive Ones III, just change k = 1; others are the same
    public int findMaxConsecutiveOnes(int[] A) {
        if (A == null ||A.length < 1) return 0;
        int n = A.length, l = 0, r= -1;
        int left = 1;
        int res = 0;
        while(r + 1 < n) {
            while(r + 1 < n && (A[r+1] == 1 || A[r+1] == 0 && left > 0)) {
                if (A[r+1] == 0 && left > 0) left --;
                r++;
            }
            
            res = Math.max(res, r - l +1);
            while(l < n && left <= 0) {
                if (A[l] == 0) left++;
                l++;
            }
        }
        
        return res;
    }
}