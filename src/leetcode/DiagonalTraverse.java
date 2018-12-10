package leetcode;
public class DiagonalTraverse {
/*
Given a matrix of M x N elements (M rows, N columns), return all elements of 
the matrix in diagonal order as shown in the below image.
Example:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]

Output:  [1,2,4,7,5,3,6,8,9]
*/
    public int[] findDiagonalOrder2(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return new int[0];
        int m = matrix.length, n = matrix[0].length;
        
        int[] result = new int[m * n];
        int row = 0, col = 0, d = 0;
        int[][] dirs = {{-1, 1}, {1, -1}};
        
        for (int i = 0; i < m * n; i++) {
            result[i] = matrix[row][col];
            row += dirs[d][0];
            col += dirs[d][1];
            
            if (row >= m) { row = m - 1; col += 2; d = 1 - d;}
            if (col >= n) { col = n - 1; row += 2; d = 1 - d;}
            if (row < 0)  { row = 0; d = 1 - d;}
            if (col < 0)  { col = 0; d = 1 - d;}
        }
        
        return result;
    }
    
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length == 0) return new int[0];
        int r = 0, c = 0, m = matrix.length, n = matrix[0].length, arr[] = new int[m * n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = matrix[r][c];
            if ((r + c) % 2 == 0) { // moving up
                if      (c == n - 1) { r++; }
                else if (r == 0)     { c++; }
                else            { r--; c++; }
            } else {                // moving down
                if      (r == m - 1) { c++; }
                else if (c == 0)     { r++; }
                else            { r++; c--; }
            }   
        }   
        return arr;
    }
    //one easy understand way
    
    //thinking process: so the problem is to print a matrix as dialgue, so we use flag to say up or down, 
    // 1. decide direction  2. process the col and row edge case,
    //first is up, then if it is row = 0 or col = m -1, 
    public int[] findDiagonalOrder3(int[][] matrix) {
        if(matrix == null || matrix.length < 0 || matrix[0].length < 0) return new int[0];
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        int[] res = new int[m * n];
        int idx = 0;
        
        int row = 0;
        int col = 0;
        
        boolean up = true;
        while(idx < m * n) {
            res[idx++] = matrix[row][col];
            if(up) {
                if(row == 0 || col == n - 1) {
                    up = !up;
                    if(col == n - 1) row++;
                    else col++;
                } else {
                    //normal case
                    row--;col++;
                }
            //moving down
            }else{
                if(col == 0 || row == m - 1) {
                    up = !up;
                    if(row == m - 1) col++;
                    else row++;
                } else {
                    row++;col--;
                }
            }
        }
        return res;
    }
}