package hatecode._1000_1999;

import java.util.*;
public class _1200MinimumAbsoluteDifference {
/*
1200. Minimum Absolute Difference
Given an array of distinct integers arr, find all pairs of elements with the minimum absolute difference of any two elements. 

Return a list of pairs in ascending order(with respect to pairs), each pair [a, b] follows

a, b are from arr
a < b
b - a equals to the minimum absolute difference of any two elements in arr
 

Example 1:

Input: arr = [4,2,1,3]
Output: [[1,2],[2,3],[3,4]]
*/
    
    //thinking process: O(nlgn)/O(1)
    
    //the problem is to say: given one distinct integer array, find the min difference bettwen
    //any two numbers of the array, return the pairs.
    
    //we sort, then each adjacent numbers have the diff is minimal compared to other elements.
    //then if we found a smaller, we clear all previous paris then continue
    public List<List<Integer>> minimumAbsDifference(int[] A) {
        List<List<Integer>> res = new ArrayList<>();
        if (A == null || A.length < 1) return res;
        
        //sort elements
        Arrays.sort(A);
        //init our min difference value
        int min = Integer.MAX_VALUE;
        //start looping over array to find real min element. Each time we found smaller difference
        //we reset resulting list and start building it from scratch. If we found pair with the same
        //difference as min - add it to the resulting list
        for (int i = 0; i < A.length - 1; i++) {
            int diff = A[i + 1] - A[i];
            if (diff < min) {
                min = diff;
                res.clear();
                res.add(Arrays.asList(A[i], A[i + 1]));
            } else if (diff == min) {
                res.add(Arrays.asList(A[i], A[i + 1]));
            }
        }
        return res;
    }
    
}