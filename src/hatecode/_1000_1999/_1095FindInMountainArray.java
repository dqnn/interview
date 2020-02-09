package hatecode._1000_1999;
/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */
 
public class _1095FindInMountainArray {
/*
 * 1095. Find in Mountain Array
(This problem is an interactive problem.)

You may recall that an array A is a mountain array if and only if:

A.length >= 3
There exists some i with 0 < i < A.length - 1 such that:
A[0] < A[1] < ... A[i-1] < A[i]
A[i] > A[i+1] > ... > A[A.length - 1]
Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target.  If such an index doesn't exist, return -1.

You can't access the mountain array directly.  You may only access the array using a MountainArray interface:

MountainArray.get(k) returns the element of the array at index k (0-indexed).
MountainArray.length() returns the length of the array.
Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer.  Also, any solutions that attempt to circumvent the judge will result in disqualification.

 

Example 1:

Input: array = [1,2,3,4,5,3,1], target = 3
Output: 2
*/
    
    //thinking process O(lgn)/O(1)
    
    //given a montain array, find the target number's index. 
    //first we find the moutain index, then search in left, if we cannot find it, then search on right side
    
    //if we want to return index, then l < r
    //if we want to return a value in array, then l <=r
    //if the array has dup number, and we want to know left most or right most, then , then l + 1 < r
    
    public int findInMountainArray(int target, MountainArray A) {
        int n = A.length(), l, r, m, peak = 0;
        // find index of peak
        l  = 0;
        r = n - 1;
        while (l < r) {
            m = (l + r) / 2;
            if (A.get(m) < A.get(m + 1)) l = peak = m + 1;
            else r = m;
        }
        // find target in the left of peak
        l = 0;
        r = peak;
        while (l <= r) {
            m = (l + r) / 2;
            if (A.get(m) < target) l = m + 1;
            else if (A.get(m) > target) r = m - 1;
            else return m;
        }
        // find target in the right of peak
        l = peak;
        r = n - 1;
        while (l <= r) {
            m = (l + r) / 2;
            if (A.get(m) > target) l = m + 1;
            else if (A.get(m) < target) r = m - 1;
            else return m;
        }
        return -1;
    }
    interface MountainArray {
        public int get(int index);
        public int length();
    }
}