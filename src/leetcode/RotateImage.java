package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : RotateImage
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 48. Rotate Image
 */
public class RotateImage {
    /**
     * Example 1:

     Given input matrix =
     [
     [1,2,3],
     [4,5,6],
     [7,8,9]
     ],

     rotate the input matrix in-place such that it becomes:
     [
     [7,4,1],
     [8,5,2],
     [9,6,3]
     ]

     [
     [1,4,7],
     [2,5,8],
     [3,6,9]
     ]

     time : O(n * m)
     space : O(1)
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[0].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length - 1 - j];
                matrix[i][matrix.length - 1 - j] = temp;
            }
        }
    }
    
    // same as above
    public void rotate2(int[][] m) {
        //edge case
        if (m == null || m.length == 1 && m[0].length == 1) {
            return;
        }
        int r = m.length, c = m[0].length;
        // i j <-> j i
        for(int i = 0; i< r; i++) {
            for(int j = i; j < c; j++) {
                int temp = m[i][j];
                m[i][j] = m[j][i];
                m[j][i] = temp;
            }
        }
        // we swap the elements in one row
        for(int i = 0; i< r;i++){
            int left = 0, right = c - 1;
            while(left < right) {
                int temp = m[i][left];
                m[i][left++] = m[i][right];
                m[i][right--] = temp;
            }
        }
    }
}
