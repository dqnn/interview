package hatecode._0001_0999;

import java.util.*;
public class _977SquaresOfASortedArray {
/*
977. Squares of a Sorted Array
Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.

 

Example 1:

Input: [-4,-1,0,3,10]
Output: [0,1,9,16,100]

*/
    //straight forward
    public int[] sortedSquares(int[] A) {
        if (A == null || A.length < 1) return new int[]{};
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) ->(a-b));
        
        for(int temp : A) {
            q.offer(temp * temp);
        }
        for(int i = 0; i< A.length; i++) {
            A[i] = q.poll();
        }
        
        return A;
    }
}