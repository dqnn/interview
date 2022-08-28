package hatecode._1000_1999;
public class _1559DetectCyclesin2DGrid {
/*
1559. Detect Cycles in 2D Grid
Given a 2D array of characters grid of size m x n, you need to find if there exists any cycle consisting of the same value in grid.

A cycle is a path of length 4 or more in the grid that starts and ends at the same cell. From a given cell, you can move to one of the cells adjacent to it - in one of the four directions (up, down, left, or right), if it has the same value of the current cell.

Also, you cannot move to the cell that you visited in your last move. For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2) we visited (1, 1) which was the last visited cell.

Return true if any cycle of the same value exists in grid, otherwise, return false.

 

Example 1:



Input: grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
Output: true
Explanation: There are two valid cycles shown in different colors in the image below:
*/
    /*
     * thinking process: O(n*m)/o(nm)
     * the problem is to say: given a 2D matrix, we need to 
     */
  
    public boolean containsCycle(char[][] A) {
        int r = A.length, c= A[0].length;
        boolean[][] visited  =new boolean[r][c];
        for(int i = 0; i< r;i++) {
            for(int j =0; j< c;j++) {
                if (!visited[i][j]) {
                    if (helper(A, i, j, -1,-1, visited)) {
                        return true;
                    }
                }
                
            }
        }
        
        return false;
    }
    
    private boolean helper(char[][] A, int i, int j, int _i, int _j, boolean[][] visited) {
       
       int r = A.length, c=A[0].length;
       visited[i][j] = true;
       int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
       
        for(int[] d: dirs) {
            int x = i + d[0];
            int y = j + d[1];
            
            if (x >=0 && x < r && y >=0 && y < c && A[i][j] == A[x][y]) {
                if (!(x == _i && y == _j) ) {
                    if (visited[x][y]) return true;
                    else if (helper(A, x, y, i, j, visited)) return true;
                }
            }
        }
        
        return false;
    }
}