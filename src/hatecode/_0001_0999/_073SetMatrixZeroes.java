package hatecode._0001_0999;

/**
 * Description : 73. Set Matrix Zeroes
 */
public class _073SetMatrixZeroes {
    /**
Given a m x n matrix, if an element is 0, 
set its entire row and column to 0. Do it in-place.
Example 1:

Input: 
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
Output: 
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
Example 2:

Input: 
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
Output: 
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]
Follow up:

A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?


     time : O(n * m)
     space : O(1)

     * @param matrix
     */
    //thinking process:
    //the problem is to set row i and column j in matrix to 0 when cell is 0
    
    /*
     *  first round we scan 0->r-1 and 1->c-1, leave first col , we also use
     *  col0 to indicate whether there is 0 in column 0;
     *  
     *  in 2nd round, 
     */
    
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return;
        }
        int col0 = 1, r = matrix.length, c = matrix[0].length;

        for (int i = 0; i < r; i++) {
            if (matrix[i][0] == 0) col0 = 0;
            //for column, we start from 1 becz we use column 0 as flag, so 
            //it is 0 does not column 0 all have to be 0
            for (int j = 1; j < c; j++)
                //we set first row and first column to 0, 
                //since we can scan back, so this can help all to be 0 after 2nd 2 lops
                if (matrix[i][j] == 0)
                    matrix[i][0] = matrix[0][j] = 0;
        }

        for (int i = r - 1; i >= 0; i--) {
            for (int j = c - 1; j >= 1; j--)
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            if (col0 == 0) matrix[i][0] = 0;
        }
    }
    //this is interview friendly, because it is stright forward, but we can improve by above solution, 
    /*
     * 
     */
    public void setZeroes2(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        int r = matrix.length;
        int c = matrix[0].length;
        boolean row = false;
        boolean col = false;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                    if (i == 0) row = true;
                    if (j == 0) col = true;
                }
            }
        }
        for (int i = 1; i < r; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < c; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int j = 1; j < c; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < r; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (row) {
            for (int j = 0; j < c; j++) {
                matrix[0][j] = 0;
            }
        }
        if (col) {
            for (int i = 0; i < r; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
