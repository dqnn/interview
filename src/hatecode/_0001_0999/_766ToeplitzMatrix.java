package hatecode._0001_0999;
public class _766ToeplitzMatrix {
/*
766. Toeplitz Matrix
Given an m x n matrix, return true if the matrix is Toeplitz. Otherwise, return false.

A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same elements.

 

Example 1:


Input: matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
Output: true
*/
    
    //just iterate from last line to next line, so do not need to 
    //vist last column or last row
    public boolean isToeplitzMatrix(int[][] A) {
        for(int i = 0; i< A.length -1;i++) {
            for(int j = 0; j< A[i].length - 1; j++) {
                if (A[i][j] != A[i+1][j+1]) return false;
            }
        }
        
        return true;
    }
}