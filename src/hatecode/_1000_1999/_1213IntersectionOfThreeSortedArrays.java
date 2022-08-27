package hatecode._1000_1999;

import java.util.*;
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
     * 
     */
    public List<Integer> arraysIntersection(int[] A1, int[] A2, int[] A3) {
        int p1 = 0, p2 = 0, p3 = 0;
        
        List<Integer> res = new ArrayList<>();
        while(p1 < A1.length && p2 < A2.length && p3 < A3.length) {
            if (A1[p1] == A2[p2] && A2[p2] ==A3[p3]) {
                res.add(A1[p1]);
                p1++;
                p2++;
                p3++;
            } else if (A1[p1] < A2[p2]) {
                p1++;
            } else if (A2[p2] < A3[p3]) {
                p2++;
            } else p3++;
        }
        
        return res;
    }
}