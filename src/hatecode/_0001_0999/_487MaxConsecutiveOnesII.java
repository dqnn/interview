package hatecode._0001_0999;

import java.util.LinkedList;
import java.util.Queue;

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
 * if we found zero counter > k, k = 1 here, which means we can shrink the window, until zero <=k. 
 * 
 * then it is an eligible window to count how many successive 1s
 * 
 * for sliding window here we found the condition how to move the left pointer, if we found there are more than k
 * zeros ,then we should move left pointers
 * 
 * 
  */
public int findMaxConsecutiveOnes_interview_friendly(int[] A) {

    if (A == null || A.length < 1) return 0;
    int n = A.length;
    int res = 0;
    int count = 0, k = 1;
    int l = 0, r = 0;
    while(r < n) {
        if (A[r] == 0) count++;
        
        while(count > k) {
            if(A[l] == 0) count--;
            l++;
        }
        
        res = Math.max(res, r - l+1);
        r++;
    }
    
    return res;

}

/*
 * we use a queue to keep track of index of all 0 in A 
 */
public int findMaxConsecutiveOnes_Queue(int[] A) {
        if (A == null || A.length < 1) return 0;
        
        Queue<Integer> q = new LinkedList<>();
        int l = 0;
        int res = 0;
        for(int i = 0; i< A.length; i++) {
            if (A[i] == 0) {
                q.offer(i);
            }
            
            while (q.size() > 1) {
                l = q.poll() + 1;
            }
            
            res = Math.max(res, i - l + 1);
            
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