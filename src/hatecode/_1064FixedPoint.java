package hatecode;
public class _1064FixedPoint {
/*
1064. Fixed Point
Given an array A of distinct integers sorted in ascending order, return the smallest index i that satisfies A[i] == i.  Return -1 if no such i exists.

 

Example 1:

Input: [-10,-5,0,3,7]
Output: 3
*/
    public int fixedPoint(int[] A) {
        int l = 0, r = A.length;
        while(l < r) {
            int m = l + (r - l)/2;
            if (A[m] < m) l = m + 1;
            else r = m;
        }
        if(l == A.length || A[l] != l) return -1;
        else return l;
        
    }
    
    public int fixedPoint_BF(int[] A) {
        for(int i = 0; i< A.length; i++) {
            if(A[i] == i) return i;
        }
        return -1;
    }
}