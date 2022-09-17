package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SpiralMatrixII
 * Creator : professorX
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
    // n x n matrix. 
    //I need these two if because their colE and rowE are different,
    //so we need 
    public int[][] generateMatrix(int n) {

        int[][] res = new int[n][n];
        int rowBegin = 0;
        int rowEnd = n - 1;
        int colBegin = 0;
        int colEnd = n - 1;
        int num = 1;

        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            for (int i = colBegin; i <= colEnd; i++) {
                res[rowBegin][i] = num++;
            }
            rowBegin++;

            for (int i = rowBegin; i <= rowEnd; i++) {
                res[i][colEnd] = num++;
            }
            colEnd--;

            for (int i = colEnd; i >= colBegin; i--) {
                res[rowEnd][i] = num++;
            }
            rowEnd--;

            for (int i = rowEnd; i >= rowBegin; i--) {
                res[i][colBegin] = num++;
            }
            colBegin++;
        }
        return res;
    }
}
