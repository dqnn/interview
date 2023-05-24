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

/*
 * thinking process: O(n)/O(1)
 * 
 * interview friendly: the problem is to say: given one array with only 1 and 0, you can only flip 0 to 1 
 * only 1 time, try to find the max length of successive 1 in array. 
 * 
 * we can use recursive way to find the max succesive ones, for example 
 * at position i, if A[i] == 0, then you have 2 paths, flip or not flip, 
 *                if A[i] == 1, you only have 1 path 
 * 
 * another bettwe way is to use two pointers, left and right,
 * 
 * r will continue to right, every time if we visit A[i] == 0, then zero++, 
 * 
  */
public int findMaxConsecutiveOnes(int[] A) {

    if (A == null || A.length < 1) return 0;
    int n = A.length;
    int res = 0;
    int zero = 0, k = 1;
    for(int l = 0, r = 0; r < n; r++) {
        if (A[r] == 0) zero++;
        while(zero > k) {
            if (A[l++] == 0) {
                zero--;
            }
        }
        
        res = Math.max(res, r -l + 1);
    }
    
    return res;

}

public int findMaxConsecutiveOnes_recursive(int[] A) {
    if (A == null || A.length < 1) return 0;
    
    return helper(A, 0, 0, 1);
}

private int helper(int[] A, int i, int cur, int k) {
    if (i == A.length) return cur;
    
    if (A[i] == 1) {
        return helper(A, i + 1,  cur+1, k);
    } else {
        if (k <=0)
            return Math.max(cur, helper(A, i + 1, 0, k));
        else return Math.max(helper(A, i + 1, cur + 1, k-1), helper(A, i + 1, 0, k));
    }
}









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