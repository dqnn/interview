package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : RangeSumQuery2DImmutable
 * Date : Sep, 2018
 * Description : 304. Range Sum Query 2D - Immutable
 */
public class _304RangeSumQuery2DImmutable {
    /**
     * Given a 2D matrix matrix, find the sum of the elements inside the rectangle 
     * defined by its 
     * upper left corner (row1, col1) and lower right corner (row2, col2).

     Range Sum Query 2D
     The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and 
     (row2, col2) = (4, 3), which contains sum = 8.

     Example:
     Given matrix = [
     [3, 0, 1, 4, 2],
     [5, 6, 3, 2, 1],
     [1, 2, 0, 1, 5],
     [4, 1, 0, 1, 7],
     [1, 0, 3, 0, 5]
     ]

     sumRegion(2, 1, 4, 3) -> 8
     sumRegion(1, 1, 2, 2) -> 11
     sumRegion(1, 2, 2, 4) -> 12

     time : O(m * n)
     space : O(m * n)

     */

    private int[][] sum;

    // it is more like area calculation
    
    /*
     * thinking process: O(n*m)/O(n*m)
     * the problem is to say, given a 2D matrix, and with top left and right bottom points,
     * return area
     * 
     * following are keys:
     * 1. sum[i][j] means area(0,0)->(i-1, j-1) area
     * 2. since top left and right bottom, we can easily get the other parts area 
     * 
     * Notes: r1+1-1 means top right upper coordination
     */
    public _304RangeSumQuery2DImmutable(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int m = matrix.length;
        int n = matrix[0].length;
        sum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // the four number 
                //  x x
                //  x x 
                //right bottom down is i j, so 
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
    }
    
    //this is simpler version, 
    public int sumRegion(int r1, int c1, int r2, int c2) {
        return sum[r2+1][c2+1] - sum[r1+1-1][c2+1]- sum[r2+1][c1+1-1] + sum[r1+1-1][c1+1-1];
    }

    public int sumRegion_Complex(int row1, int col1, int row2, int col2) {
        int iMin = Math.min(row1, row2);
        int iMax = Math.max(row1, row2);
        int jMin = Math.min(col1, col2);
        int jMax = Math.max(col1, col2);
        return sum[iMax + 1][jMax + 1] - sum[iMax + 1][jMin] - sum[iMin][jMax + 1] + sum[iMin][jMin];
    }
}
