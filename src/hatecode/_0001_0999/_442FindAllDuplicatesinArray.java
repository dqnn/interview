package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name : Leetcode

 * Package Name : leetcode
 * File Name : FindAllDuplicatesinanArray
 * Creator : professorX
 * Date : June, 2018
 * Description : TODO
 */
/*
 * LeetCode 442
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements that appear twice in this array.

Could you do it without extra space and in O(n) runtime?

Example:
Input:
[4,3,2,7,8,2,3,1]

Output:
[2,3]
 */

/*
 *  use index as indicator to store whether visited before or not
 *  O(n), O(1)
 */
public class _442FindAllDuplicatesinArray {
    // so should be sensitive about the index,if it is within context of n, then we should consider 
    // take advantage of the value to be index. 
    public List<Integer> findDuplicates(int[] A) {
        //edge case
        List<Integer> res = new ArrayList<>();
        if (A == null || A.length < 2) {
            return res;
        }
        
        int len = A.length;
        // try to use starting from 0 always and use the i+1. which is better than 1 since you
        // will ignore some elements
        for(int i = 0; i <= len - 1; i++) {
            int index = Math.abs(A[i]) - 1;
            if (A[index] > 0) {
                A[index] = 0 - A[index];
            } else {
                res.add(Math.abs(A[i]));
            }
            
        }
        
        return res;
    }
}