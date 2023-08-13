package hatecode._2000_2999;

import java.util.*;

public class _2817MinimumAbsoluteDifferenceBetweenElementsWithConstraint {
/*
2817. Minimum Absolute Difference Between Elements With Constraint

You are given a 0-indexed integer array nums and an integer x.

Find the minimum absolute difference between two elements in the array that are at least x indices apart.

In other words, find two indices i and j such that abs(i - j) >= x and abs(nums[i] - nums[j]) is minimized.

Return an integer denoting the minimum absolute difference between two elements that are at least x indices apart.

 

Example 1:

Input: nums = [4,3,2,4], x = 2
Output: 0
*/
/*
 * thinking process: O(n)/O(1)
 * 
 * the problem is to say: given list of integers, and integer x, you need calculate 
 * val = Math.abs(A[i] - A[j]), abs(i-j) >=x, return the min(val)
 * 
 * 
 */
    public int minAbsoluteDifference(List<Integer> A, int x) {
        if (A == null || A.size() < 1 || x <= 0) return 0;
        
        TreeSet<Integer> set = new TreeSet<>();
        int res = Integer.MAX_VALUE;
        for(int i = 0; i< A.size(); i++) {
            if (i >=x) set.add(A.get(i -x));
            Integer ceiling = set.ceiling(A.get(i));
            if (ceiling != null) {
                res = Math.min(res, Math.abs(A.get(i) - ceiling));
            }
            
            Integer floor = set.floor(A.get(i));
            if (floor != null) {
                res = Math.min(res, Math.abs(A.get(i) - floor));
            }
        }
        
        return res;
    }
}