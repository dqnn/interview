package hatecode._1000_1999;

import java.util.*;
public class _1213_IntersectionOfThreeSortedArrays {
/*
    1213. Intersection of Three Sorted Arrays
    Given three integer arrays arr1, arr2 and arr3 sorted in strictly increasing order, return a sorted array of only the integers that appeared in all three arrays.
    
    Example 1:
    
    Input: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
    Output: [1,5]
    Explanation: Only 1 and 5 appeared in the three arrays.
    Constraints:
    
    1 <= arr1.length, arr2.length, arr3.length <= 1000
    1 <= arr1[i], arr2[i], arr3[i] <= 2000
*/
    
    public static List<Integer> arraysIntersection(int[] A1, int[] A2, int[] A3) {
        List<Integer> res = new ArrayList<>();
        if(A1 == null || A2 == null || A3 == null){
            return res;
        }

        int i = 0, j = 0, k = 0;
        
        while (i < A1.length && j < A2.length && k < A3.length) {
            if (A1[i] == A2[j] && A1[i] == A3[k]) {
                res.add(A1[i]);
                i++;
                j++;
                k++;
            }
            else if (A1[i] < A2[j]) i ++;
            else if (A2[j] < A3[k]) j ++;
            else k ++;
        }

        return res;
    }
    
    public static void main(String[] args) {
        System.out.println(arraysIntersection(new int[]{1,2,3,4,5}, 
                                                new int[]{1,2,5,7,9}, 
                                                new int[]{1,3,4,5,8}));
    }
    
}

