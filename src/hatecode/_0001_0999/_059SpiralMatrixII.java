package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SpiralMatrixII
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 59. Spiral Matrix II
 */
public class _059SpiralMatrixII {

    /**
     *Given an integer n, generate a square matrix 
     *filled with elements from 1 to n^2 in spiral order.
     *

     For example,
     Given n = 3,

     You should return the following matrix:
     [
     [ 1, 2, 3 ],
     [ 8, 9, 4 ],
     [ 7, 6, 5 ]
     ]

     time : O(n)     n : 总元素个数
     space : O(n)

     * @param n
     * @return
     */
    //thinking process: 
    //so the problem is to output n*n matrix from 1-n, 
    //we do not need to if (colB <= colE) because we know it is 
    // nxn matrix. 
    //I need these two if becuase their colE and rowE are different,
    //so we need 
    public int[][] generateMatrix(int n) {

        int[][] matrix = new int[n][n];
        int rowBegin = 0;
        int rowEnd = n - 1;
        int colBegin = 0;
        int colEnd = n - 1;
        int num = 1;

        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            for (int i = colBegin; i <= colEnd; i++) {
                matrix[rowBegin][i] = num++;
            }
            rowBegin++;

            for (int i = rowBegin; i <= rowEnd; i++) {
                matrix[i][colEnd] = num++;
            }
            colEnd--;

            for (int i = colEnd; i >= colBegin; i--) {
                matrix[rowEnd][i] = num++;
            }
            rowEnd--;

            for (int i = rowEnd; i >= rowBegin; i--) {
                matrix[i][colBegin] = num++;
            }
            colBegin++;
        }
        return matrix;
    }
}
