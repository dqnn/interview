package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : Searcha2DMatrixII
 * Creator : professorX
 * Date : Sep, 2018
 * Description : 240. Search a 2D Matrix II
 */
public class _240Searcha2DMatrixII {
    /**
     * Write an efficient algorithm that searches for a value in an m x n matrix.
     * This matrix has the following properties:
     * 
     * Integers in each row are sorted in ascending from left to right.
     * Integers in each column are sorted in ascending from top to bottom.
     * For example,
     * 
     * Consider the following matrix:
     * 
     * [
     * [1, 4, 7, 11, 15],
     * [2, 5, 8, 12, 19],
     * [3, 6, 9, 16, 22],
     * [10, 13, 14, 17, 24],
     * [18, 21, 23, 26, 30]
     * ]
     * Given target = 5, return true.
     * 
     * Given target = 20, return false.
     * 
     * time : O(m + n)
     * space : O(1)
     * 
     * @param matrix
     * @param target
     * @return
     */
    // so we went through the 2D matrix by two pointer, row and col
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0)
            return false;
        int row = 0;
        int col = matrix[0].length - 1;
        // we choose top right element to starting to look up,
        // because i only ++ while j only --
        while (col >= 0 && row <= matrix.length - 1) {
            if (target == matrix[row][col]) {
                return true;
            } else if (target < matrix[row][col]) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }

    // Binary Search, interview frinendly
    // TC: O(logmn) = O(lgm + lgn), every time we reduced half of the search space
    // space comlexity: O(max (logn, lgn))

    // so the array was increase in 2D, so if we want to find some element faster,
    // the fastest way is bianry search, so we need to adjust the common BS to be
    // 2D Binary search.

    // since it is 2D BS, so we need binary search, top left and bottom right
    // coordination
    /*
     * zone 1 zone 2 |
     * -(middle)|
     * zone 4 zone 3 |
     * ----------------| bottom right
     * 
     * 
     * (x1,y1)        (x1, my+1)
     * 
     *       (mx,my)            (mx, y2)
     * (mx+1, y1)     (mx+1, my+1)
     * 
     * 
     *       (x2, my)           (x2, y2)
     * 
     * /*
     * every time,
     * if target is on left side, (Topleft, middle), then we discard zone 3, try to
     * search in zone 1, zone 2 and zone 3
     * if target is on right side,(middle, bottom right), then we discard zone 1,
     * try to search in zone 2, 3 and zone 4
     * 
     * T(n) = 3(T/2)
     * 
     * 
     */

     public boolean searchMatrix_BS(int[][] A, int t) {
        if(A == null || A.length < 1 || A[0].length < 1) return false;
        
        int m = A.length,n = A[0].length;
        
        return helper(A, 0, 0, m-1,n-1, t);
    }
    
    
    private boolean helper(int[][] A, int x1, int y1, int x2, int y2, int t) {
        if(x1 > x2 || y1 > y2) return false;
        
        //if they are equals, so return
        if(x1 == x2 && y1 == y2) return A[x1][y1] == t;
        
        int xm = x1 + (x2 - x1)/2;
        int ym = y1 + (y2 - y1)/2;
        if(t < A[xm][ym]) {
            return helper(A, x1, y1, xm, ym, t)  // z 1
                 ||helper(A, x1, ym + 1, xm, y2,t) // z 2
                 ||helper(A, xm + 1, y1, x2, ym, t); //z 4
        } else if (t > A[xm][ym]) {
            return helper(A, x1, ym + 1, xm, y2, t) // z 2
                 ||helper(A, xm + 1, y1, x2, ym, t)  //z 4
                 || helper(A, xm+1, ym +1, x2, y2, t); //z3
        } else return true;
    }
    
    
    
    int[][] m;
    int target;

    public boolean searchMatrix2(int[][] matrix, int target) {
        this.m = matrix;
        this.target = target;
        if (matrix.length == 0)
            return false;
        return helper(0, 0, matrix.length - 1, matrix[0].length - 1);
    }

    /**
     * @param x1, row coordinate of top left element of the matrix
     * @param y1, column coordinate of top left elemeent of the matrix
     * @param x2, row coordinate of bottom right element of the matrix
     * @param y2, column coordinate of bottom right element of the matrix
     */
    private boolean helper(int x1, int y1, int x2, int y2) {
        if (x2 < x1 || y2 < y1 || x1 >= m.length || y1 >= m[0].length || x2 < 0 || y2 < 0)
            return false;
        int nx = (x2 - x1) / 2 + x1;
        int ny = (y2 - y1) / 2 + y1;
        // Check the middle element of the matrix, if not found,
        // recursively call on sub matrices where
        // the value could still exist.
        // You will realize that the resultant possible places will
        // form a L shape on the original matrix.
        // This L shape can be broken down into 2 matrices.
        // If number found in any of the 2 matrices, we return true.
        if (m[nx][ny] == target)
            return true;
        // so it is in (mid, end), so we left to mid
        else if (m[nx][ny] < target) {
            return helper(x1, ny + 1, x2, y2) || helper(nx + 1, y1, x2, ny);
            // so it is in (left, mid). so we move to left
        } else if (m[nx][ny] > target) {
            return helper(x1, y1, x2, ny - 1) || helper(x1, ny, nx - 1, y2);
            // return
        } else
            return false;
    }

    


    public boolean searchMatrix_binarySearchByRow(int[][] A, int target) {
        for (int i=0;i<A.length;i++){
                int l  = 0;
                int r = A[i].length-1;
                while (l <= r){
                        int m = l + (r - l) / 2; // find mid
                        if (A[i][m] == target) return true; // if target matches then return true
                        else if (A[i][m] < target) l = m+1; // if the value is less than target then increment the left pointer
                        else r = m-1; // otherwise decrement the right pointer
                } 
        }
        return false;
    }
}
