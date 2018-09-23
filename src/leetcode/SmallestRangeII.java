package leetcode;

import java.util.Arrays;

public class SmallestRangeII {
/*
 * 910. Smallest Range II
 * Author Denny 
 * Sep 2018
 */
/*
 Given an array A of integers, for each integer A[i] we need to choose either x = -K or x = K, and add x to A[i] (only once).

After this process, we have some array B.

Return the smallest possible difference between the maximum value of B and the minimum value of B.

 

Example 1:

Input: A = [1], K = 0
Output: 0
Explanation: B = [1]
Example 2:

Input: A = [0,10], K = 2
Output: 6
Explanation: B = [2,8]
Example 3:

Input: A = [1,3,6], K = 3
Output: 3
Explanation: B = [4,6,3]
 

Note:

1 <= A.length <= 10000
0 <= A[i] <= 10000
0 <= K <= 10000
 */
//Assuming there is a point, on the left of the point, all elements add K, 
//on the right of the point, all elements 
//substract K, check each possible point. (a point is between two numbers).
//thinking process:
    // each element can +k or -k so we can have 2 ^ n arrays, so we want to
    //find smallest between min and max in the same array
    
    // so thinking about this way, there are 3 use cases that smallest diff exists
    //1 all +k, then it is same as current
    //2 all -k then it is same as current
    //3 partially +k, partially -k, so we want to have a cut in the array, left +k, right
    // -k, so n elements have n-1 slots, i < len -1, 
    
    // so res default to be max - min. max = right, min = left 
    // suppose cut from 0 to n - 1, 
    // left +k while right -k so we can get smallest diff
    public int smallestRangeII(int[] A, int k) {
        if (A == null || A.length < 1) {
            return 0;
        }
        
        Arrays.sort(A);
        int left = A[0] + k, right = A[A.length - 1] - k;
        int res = A[A.length - 1] - A[0];
        for(int i = 0; i < A.length - 1; i++) {
            int max = Math.max(right, A[i] + k);
            int min = Math.min(left, A[i+1] - k);
            res = Math.min(res, max - min);
        }
        return res;
    }
}
