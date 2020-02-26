package hatecode._0001_0999;
public class _941ValidMountainArray {
/*
941. Valid Mountain Array
Given an array A of integers, return true if and only if it is a valid mountain array.

Recall that A is a mountain array if and only if:

A.length >= 3
There exists some i with 0 < i < A.length - 1 such that:
A[0] < A[1] < ... A[i-1] < A[i]
A[i] > A[i+1] > ... > A[B.length - 1]
 

Example 1:

Input: [2,1]
Output: false
Example 2:

Input: [3,5,5]
Output: false
Example 3:

Input: [0,3,2,1]
Output: true
 

Note:

0 <= A.length <= 10000
0 <= A[i] <= 10000 
 */
    public boolean validMountainArray(int[] A) {
        if (A == null || A.length < 3 || A[0] >= A[1] || A[A.length -2] <= A[A.length - 1]) {
            return false;
        }
        
        int len = A.length;
        for(int i = 1; i < len; i++) {
            int left = 0, right = len - 1;
            while ((left < i) && (A[left] < A[left + 1])) {
                    left++;
            }
            while ((right > i) && (A[right] < A[right - 1])) {
                    right--;
            }
            if (left == i && right == i) {
                return true;
            }
            
        }
        return false;
    }
}