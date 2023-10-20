package hatecode._1000_1999;

import java.util.*;
import java.util.stream.Collectors;
public class _1213IntersectionOfThreeSortedArrays {
/*
1213. Intersection of Three Sorted Arrays
Given three integer arrays arr1, arr2 and arr3 sorted in strictly increasing order, return a sorted array of only the integers that appeared in all three arrays.

 

Example 1:

Input: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
Output: [1,5]
*/
    /*
     * thinking process: O(n)/O(1)
     * 
     * the problem is to say:
     * given 3 strictly increasing arrays, return their intersection sets
     * 
     * since we only have 3 sorted arrays, so we use 3 pointers to them,
     * then compare each pair, 
     * 
     * at p1,p2,p3 if they are not the same, then we have to move smaller pointers
     * to bigger ones, we do not have to care about P1 with p3 because 
     * even they do not move, they also do not have same number,
     * 
     * when P1, p2 are the same, then we can start to move p3
     * 
     * if we have K arrays, then we need to use a PQ.
     * 
     */
    public List<Integer> arraysIntersection(int[] A, int[] B, int[] C) {
        int p1 = 0, p2 = 0, p3 = 0;
        
        List<Integer> res = new ArrayList<>();
        while(p1 < A.length && p2 < B.length && p3 < C.length) {
            if (A[p1] == B[p2] && B[p2] ==C[p3]) {
                res.add(A[p1]);
                p1++;
                p2++;
                p3++;
            } else if (A[p1] < B[p2]) {
                p1++;
            } else if (B[p2] < C[p3]) {
                p2++;
            } else p3++;
        }
        
        return res;
    }
}