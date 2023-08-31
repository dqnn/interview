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

     Integers in each row are sorted in ascending from left to right.
     Integers in each column are sorted in ascending from top to bottom.
     For example,

     Consider the following matrix:

     [
     [1,   4,  7, 11, 15],
     [2,   5,  8, 12, 19],
     [3,   6,  9, 16, 22],
     [10, 13, 14, 17, 24],
     [18, 21, 23, 26, 30]
     ]
     Given target = 5, return true.

     Given target = 20, return false.

     time : O(m + n)
     space : O(1)

     * @param matrix
     * @param target
     * @return
     */
    //so we went through the 2D matrix by two pointer, row and col
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        int row = 0;
        int col = matrix[0].length - 1;
        // we choose top right element to starting to look up,
        //because i only ++ while j only -- 
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
    
    //Binary Search, interview frinendly 
    //TC: O(logmn) = O(lgm + lgn), every time we reduced half of the search space 
    //space comlexity: O(max (logn, lgn))
    
    // so the array was increase in 2D, so if we want to find some element faster, 
    //the fastest way is bianry search, so we need to adjust the common BS to be 
    //2D Binary search. 
    
    //since it is 2D BS, so we need binary search, top left and bottom right coordination
/*
 zone 1  zone 2  |
        -(middle)|
 zone 3  zone 4  |
 ----------------| bottom right


 (x1,y1)          (x1, my+1)

         (mx,my)              (mx, y2)

(mx+1, y1)         (mx+1, my+1)
        (x2, my)             (x2, y2)
 
/*
 * every time, 
 * if target is on left side, (Topleft, middle), then we discard zone 4, try to search in zone 1, zone 2 and zone 3
 * if target is on right side,(middle, bottom right), then we discard zone 1, try to search in zone 2, 3 and zone 4
 * 
 */



    int[][] m;
    int target;
    public boolean searchMatrix2(int[][] matrix, int target) {
        this.m = matrix;
        this.target = target;
        if(matrix.length==0)
            return false;
        return helper(0, 0, matrix.length-1, matrix[0].length-1);
    }
    /**
    * @param x1, row coordinate of top left element of the matrix
    * @param y1, column coordinate of top left elemeent of the matrix
    * @param x2, row coordinate of bottom right element of the matrix
    * @param y2, column coordinate of bottom right element of the matrix
    */
    private boolean helper(int x1, int y1, int x2, int y2){
        if(x2<x1 || y2<y1 || x1>=m.length || y1>=m[0].length || x2<0 || y2<0)
            return false;
        int nx = (x2-x1)/2+x1;
        int ny = (y2-y1)/2+y1;
        // Check the middle element of the matrix, if not found, 
        // recursively call on sub matrices where
        // the value could still exist. 
        // You will realize that the resultant possible places will
        // form a L shape on the original matrix.
        // This L shape can be broken down into 2 matrices.
        // If number found in any of the 2 matrices, we return true.
        if(m[nx][ny]==target)
            return true;
        //so it is in (mid, end), so we left to mid
        else if(m[nx][ny]<target){
            return helper(x1,ny+1,x2,y2) || helper(nx+1,y1,x2,ny);
        //so it is in (left, mid). so we move to left
        }else if(m[nx][ny]>target){
            return helper(x1,y1,x2,ny-1) || helper(x1,ny,nx-1,y2);
        //return
        }else
            return false;
    }
}
