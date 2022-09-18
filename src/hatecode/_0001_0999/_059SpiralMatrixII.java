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
        int rb = 0;
        int re = n - 1;
        int cb = 0;
        int ce = n - 1;
        int num = 1;

        while (rb <= re && cb <= ce) {
            for (int i = cb; i <= ce; i++) {
                res[rb][i] = num++;
            }
            rb++;

            for (int i = rb; i <= re; i++) {
                res[i][ce] = num++;
            }
            ce--;

            for (int i = ce; i >= cb; i--) {
                res[re][i] = num++;
            }
            re--;

            for (int i = re; i >= rb; i--) {
                res[i][cb] = num++;
            }
            cb++;
        }
        return res;
    }
}
