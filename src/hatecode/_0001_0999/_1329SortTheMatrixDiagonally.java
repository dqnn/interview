package hatecode._0001_0999;

import java.util.*;

public class _1329SortTheMatrixDiagonally {
/*
1329. Sort the Matrix Diagonally

A matrix diagonal is a diagonal line of cells starting from some cell in either the topmost row or leftmost column and going in the bottom-right direction until reaching the matrix's end. For example, the matrix diagonal starting from mat[2][0], where mat is a 6 x 3 matrix, includes cells mat[2][0], mat[3][1], and mat[4][2].

Given an m x n matrix mat of integers, sort each matrix diagonal in ascending order and return the resulting matrix.

 

Example 1:


Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]
*/

/*
 * thinking process: O(mn)/O(m)
 * 
 * the problem is to say, given one 2D matrix, sort the matrix by diag
 * 
 *  3               1
 *    1      --->     2
 *     2                3
 * 
 * 
 * 
 */
    public int[][] diagonalSort(int[][] A) {
        if (A == null || A.length < 1 ||A[0].length < 1) return new int[0][0];
        
        int m = A.length, n = A[0].length;
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for(int i = 0 ;i<m; i++) {
            for(int j = 0; j<n; j++) {
                map.computeIfAbsent(i -j, v->new PriorityQueue<>()).add(A[i][j]);
            }
        }
        
        
        for(int i = 0; i<m; i++) {
            for(int j = 0; j<n;j++) {
                A[i][j] = map.get(i-j).poll();
            }
        }
        
        return A;
    }
}