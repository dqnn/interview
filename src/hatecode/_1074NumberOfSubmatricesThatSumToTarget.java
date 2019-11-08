package hatecode;

import java.util.*;
public class _1074NumberOfSubmatricesThatSumToTarget {
/*
1074. Number of Submatrices That Sum to Target
560. Subarray Sum Equals K
Given a matrix, and a target, return the number of non-empty submatrices that sum to target.

A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.

Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate that is different: for example, if x1 != x1'.

 

Example 1:

Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
Output: 4
*/
    //thinking process：O(c^2 * r)/O(c^2*r)
    
    //given an matrix and integer target, we want to find how many matrix sum equals to 
    //target, each cell can be a matrix
    
    //so first we get each row prefix sum, change in place,
    //then we scan from each column, we use two loops on columns, i means top left point, 
    //j top right point, j will start from i, scan from left to right, 
    //k means the right bottom, move from row 0 to r-1, each move on k we will add all
    //columns numbers
    //for each rectangle, we will calculate the sum internally by prefix sum, and store 
    //each sum frequency into map
    public int numSubmatrixSumTarget(int[][] A, int target) {
        if(A == null || A.length < 1 || A[0].length < 1) return 0;
        int r = A.length, c = A[0].length;
        //each row has prefix sum
        for (int i = 0; i < r; i++)
            for (int j = 1; j < c; j++) A[i][j] += A[i][j - 1];
        
        int res = 0;
        for (int i = 0; i < c; i++) {
            for (int j = i; j < c; j++) {
                //sum<->frequency map
                Map<Integer, Integer> map = new HashMap<>();
                map.put(0, 1);
                int cur = 0;
                for (int k = 0; k < r; k++) {
                    cur += A[k][j] - (i > 0 ? A[k][i - 1] : 0);
                    res += map.getOrDefault(cur - target, 0);
                    map.put(cur, map.getOrDefault(cur, 0) + 1);
                }   
            }
        }
        return res;
    }
}