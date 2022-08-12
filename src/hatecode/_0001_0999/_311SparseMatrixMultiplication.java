package hatecode._0001_0999;

import java.util.*;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SparseMatrixMultiplication
 * Date : Sep, 2018
 * Description : 311. Sparse Matrix Multiplication
 */
public class _311SparseMatrixMultiplication {
    /**
Given two sparse matrices A and B, return the result of AB.

You may assume that A's column number is equal to B's 
row number.



     A = [
     [ 1, 0, 0],
     [-1, 0, 3]
     ]

     B = [
     [ 7, 0, 0 ],
     [ 0, 0, 0 ],
     [ 0, 0, 1 ]
     ]

          |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
     AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                       | 0 0 1 |

     time : O(m * n *nB)
     space : O(m * nB)

     * @param A
     * @param B
     * @return
     */
    // this is matrix calculations, so just know how to use
    // 3 loops to calculate the sum
    //one small trick is res[i][j] +=...
    public int[][] multiply_BF(int[][] A, int[][] B) {
        int m = A.length, n = A[0].length;
        int nB = B[0].length;
        int[][] res = new int[m][nB];

        for (int i = 0; i < m; i++) {
            for (int k = 0; k < n; k++) {
                if (A[i][k] != 0) {
                    for (int j = 0; j < nB; j++) {
                        if (B[k][j] != 0) {
                            res[i][j] += A[i][k] * B[k][j];
                        }
                    }
                }
            }
        }
        return res;
    }
    
    //interview friendly::
    
    //since it is sparse matrix, so we use two maps to store the value which are not 
    // 0, then compute  - * |. 
    
    //https://github.com/SCIN/Facebook-Interview-Coding-1/blob/master/Sparce%20Matrix%20Multiplication.java 
    //follow up 
    /*
     * 1. 
     */
    public int[][] multiply(int[][] m1, int[][] m2) {
        Map<Integer, Map<Integer, Integer>> map1 = new HashMap<>();
        
        for(int i = 0; i<m1.length; i++) {
            for(int j = 0; j< m1[0].length; j++) {
                if (m1[i][j] != 0) {
                    map1.computeIfAbsent(i, v->new HashMap<>()).put(j, m1[i][j]);
                }
            }
        }
        
        Map<Integer, Map<Integer, Integer>> map2 = new HashMap<>();
        
        for(int j = 0; j<m2[0].length; j++) {
            for(int i = 0; i< m2.length; i++) {
                if (m2[i][j] != 0) {
                    map2.computeIfAbsent(j, v->new HashMap<>()).put(i, m2[i][j]);
                }
            }
        }
        
        int[][] res = new int[m1.length][m2[0].length];
        for(int i = 0; i<m1.length; i++) {
            for(int j =0; j<m2[0].length; j++) {
                res[i][j] = ops(map1.get(i), map2.get(j));
            }
        }
        
        return res;
    }
    
    
    private int ops(Map<Integer, Integer> m1, Map<Integer, Integer> m2) {
        if (m1 == null || m2 == null) return 0;
        int res = 0;
        for(var entry: m1.entrySet()) {
            int k = entry.getKey(), v = entry.getValue();
            if (!m2.containsKey(k)) continue;
            res += v * m2.get(k);
        }
        return res;
    }
}
