package hatecode._1000_1999;

import java.util.*;
public class _1504CountSubmatricesWithAllOnes {
    /*
    1504. Count Submatrices With All Ones
    Given an m x n binary matrix mat, return the number of submatrices that have all ones.
    
     
    
    Example 1:
    
    
    Input: mat = [[1,0,1],[1,1,0],[1,1,0]]
    Output: 13
    Explanation: 
    There are 6 rectangles of side 1x1.
    There are 2 rectangles of side 1x2.
    There are 3 rectangles of side 2x1.
    There is 1 rectangle of side 2x2. 
    There is 1 rectangle of side 3x1.
    Total number of rectangles = 6 + 2 + 3 + 1 + 1 = 13.
    */
       /*
        * thinking process: O(r^2c)/O(c)
        notes: for internal loop:
         for(int i = 0; i<r;i++)  --- r 
            Arrays.fill(h, 1);    -- c
            for(int k = i; k< r; k++) -- r
               for(int j = 0; j< c; j++) -- c
               count += helper(h);       -- c

        so it will be O(r * (c + r * (c + c))) --> O(rcr)

        the problem is to say: given one matrix only contains 1 and 0, return how many submattix in the given matirx.
       
        we can start 1D array, like 
        [1] --    1
        [1,1]--   3 = 1 + 2
        [1,1,1]-- 6 = 1 + 2 + 3
        [1,0,1]-- 2 = 1 + 0 + 1

        it will be like 1+2+3, but we have to start from 1 again if it breaks by 0. 
        from here we can write the code helper() as below

        but there still one problem we did not solve which like below:
        1 1
        1 1

        the whole submatrtx is one matrix and it cannot be solved above row scan
        to solve this problem, we have to scan the whole submatrix so we can know whether 
        whole submatrix is valid one, 

        there is a trick that we can continue to use int[] h to dot multiple each row, as below:
        for( N )
           for( N ) h[j] &= A[k][j]
           res += helper(h)
        so suppose we have 2 rows, this loop will dot multiple each row then we can know 
        whole submatrix status, 
        for example if 
        [1,1]
        [1,0]
        h = [1, 0]
        then it would only add 1 matrix to results, remember: the above loop here is to count:
        how many submatrix there which each submatrix include first row, the trick is that the count of addtional 
        submtrix are the same as helper(h). 


     similar problems: 
        https://leetcode.com/problems/maximal-rectangle/
        https://leetcode.com/problems/largest-rectangle-in-histogram
        */
        
        public int numSubmat(int[][] A) {
            int r = A.length, c = A[0].length;
            
            int count = 0;
            for(int i = 0; i<r;i++) {
                int[] h = new int[c];
                Arrays.fill(h, 1);
                for(int k = i; k< r; k++) {
                    for(int j = 0; j< c; j++)  h[j] &= A[k][j];
                    count += helper(h);
                }
            }
            
            return count;
        }
        
        private int helper(int[] h) {
           int len = 0;
           int res = 0;
           for(int i = 0; i<h.length; i++) {
               len = h[i] ==0 ? 0 : len + 1;
               res += len;
           }
            
           return res;
        }
    }
