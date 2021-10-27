package hatecode._2000_2999;

import java.util.*;
public class _2016MaximumDifferenceBetweenIncreasingElements {
/*
2016. Maximum Difference Between Increasing Elements
Given a 0-indexed integer array nums of size n, find the maximum difference between nums[i] and nums[j] (i.e., nums[j] - nums[i]), such that 0 <= i < j < n and nums[i] < nums[j].

Return the maximum difference. If no such i and j exists, return -1.

 

Example 1:

Input: nums = [7,1,5,4]
Output: 4
*/
    //thinking process: O(n)/O(1)
    
    //the problem is to say: given one integer array, find one number in the array,
    //its index i < j while A[i] < A[j], return the max difference, if not, return -1;
    
    //it is essentially buy stock I problem
    public int maximumDifference(int[] A) {
        if (A == null || A.length < 1) return 0;
        
        int min = Integer.MAX_VALUE;
        int res = Integer.MIN_VALUE;
        for(int t: A) {
            min = Math.min(min, t);
            res = Math.max(res, t - min);
        }
        
        return res == 0 ? -1 : res;
    
    }
    
    //this is used by a sliding window.
    //why we can use sliding window here:
    //
    public int maximumDifference2(int[] A) {
        if (A == null || A.length < 1) return 0;
        
        Deque<Integer> q = new ArrayDeque<>();
        int res = 0;
        for(int i =0; i<A.length; i++) {
            while (!q.isEmpty() && A[q.peekFirst()] > A[i]) {
                q.pollLast();
            }
            q.offerLast(i);
            res = Math.max(res, A[i] - A[q.peekFirst()]);
        }
        
        
        return res > 0  ? res: -1;
    }
}