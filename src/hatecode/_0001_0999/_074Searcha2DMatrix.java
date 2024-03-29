package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : Searcha2DMatrix
 * Creator : professorX
 * Date : Sep, 2018
 * Description : 74. Search a 2D Matrix
 */
public class _074Searcha2DMatrix {
    /**
     * Write an efficient algorithm that searches for a value in an m x n matrix.
     * This matrix has the following properties:

     Integers in each row are sorted from left to right.
     The first integer of each row is greater than the last integer of the previous row.
     For example,

     Consider the following matrix:

     [
     [1,   3,  5,  7],
     [10, 11, 16, 20],
     [23, 30, 34, 50]
     ]
     Given target = 3, return true.

     end = 11 mid = 5 col = 4

     time : O(log(n * m));
     space : O(1)

     * @param matrix
     * @param target
     * @return
     */
    //binary search tamplates 1
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return false;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        //we consider while maxtrix as 1D array
        //x = mid / col, y = mid % col
        int begin = 0, end = row * col - 1;
        //we use first template,since we just need to find the target
        while (begin <= end) {
            int mid = (end - begin) / 2 + begin;
            int value = matrix[mid / col][mid % col];
            if (value == target) {
                return true;
            } else if (value < target) {
                begin = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }
}
