package hatecode._2000_2999;

import hatecode._0001_0999.ListNode;

public class _2326SpiralMatrixIV {
/*
2326. Spiral Matrix IV
You are given two integers m and n, which represent the dimensions of a matrix.

You are also given the head of a linked list of integers.

Generate an m x n matrix that contains the integers in the linked list presented in spiral order (clockwise), starting from the top-left of the matrix. If there are remaining empty spaces, fill them with -1.

Return the generated matrix.

 

Example 1:


Input: m = 3, n = 5, head = [3,0,2,6,8,1,7,9,4,2,5,5,0]
Output: [[3,0,2,6,8],[5,0,-1,-1,1],[5,2,4,9,7]]
*/
    
    //thinking process: 
    //
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] res = new int[m][n];
        
        for(int i = 0; i<m; i++) {
            for(int j =0;j<n;j++) {
                res[i][j] = -1;
            }
        }
        
        int i =0, j = 0;
        String dir = "R";
        while(head != null) {
            res[i][j] = head.val;
            head=head.next;
            if (dir.equals("R")) {
                
               if (j < n-1 && res[i][j+1] == -1) {
                   j++;
               } else {
                   dir = "D";
                   i++;
               }
            } else if (dir.equals("D")){
                if (i < m -1 && res[i+1][j] == -1) i++;
                else {
                    dir = "L";
                    j--;
                }
            } else if (dir.equals("L")){
                if (j > 0 && res[i][j-1] == -1)  j --;
                else {
                    dir ="U";
                    i--;
                }
            } else if (dir.equals("U")){
                if (i > 0 && res[i-1][j] == -1) i--;
                else {
                    dir = "R";
                    j++;
                }
            }
        }
        
        return res;
    }
}