package hatecode._0001_0999;
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
    //one easy understand way, interview friendly: 
    
    //thinking process: so the problem is to print a matrix as dialgue, so we use flag to say up or down, 
    // 1. decide direction  2. process the col and row edge case,
    //first is up, then if it is row = 0 or col = m -1, 
    public int[] findDiagonalOrder3(int[][] m) {
        if(m == null || m.length < 0 || m[0].length < 0) return new int[0];
        
        int r = m.length, c = m[0].length;
        
        int[] res = new int[r * c];
        //variable to generate res
        int idx = 0;
        int i = 0, j = 0;//coordinations
        
        boolean up = true;
        while(idx < r * c) {
            res[idx++] = m[i][j];
            if(up) {
                //turn direction on edge
                if(i == 0 || j == c - 1) {
                    up = !up;
                    if(j == c - 1) i++;
                    else j++;
                } else {
                    //normal case
                    i--;j++;
                }
            //moving down
            } else {
                if(j == 0 || i == r - 1) {
                    up = !up;
                    if(i == r - 1) j++;
                    else i++;
                } else {
                    i++;j--;
                }
            }
        }
        return res;
    }
}