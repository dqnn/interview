package hatecode._2000_2999;

import java.util.*;

/*
2574. Left and Right Sum Differences
 * Given a 0-indexed integer array nums, find a 0-indexed integer array answer where:

answer.length == nums.length.
answer[i] = |leftSum[i] - rightSum[i]|.
Where:

leftSum[i] is the sum of elements to the left of the index i in the array nums. If there is no such element, leftSum[i] = 0.
rightSum[i] is the sum of elements to the right of the index i in the array nums. If there is no such element, rightSum[i] = 0.
Return the array answer.

 

Example 1:

Input: nums = [10,4,8,3]
Output: [15,1,11,22]
 */
public class _2574LeftAndRightSumDifferences {
    public int[] leftRigthDifference(int[] A) {
        int n = A.length;
        
        int rSum = Arrays.stream(A).sum();
        int[] res = new int[n];
        
        int left = 0;
        for(int i = 0; i<n; i++) {
            res[i] = Math.abs(left - (rSum - left - A[i]));
            left += A[i];
        }
        
        return res;
    }
}