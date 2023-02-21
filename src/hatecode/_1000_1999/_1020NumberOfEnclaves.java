package hatecode._1000_1999;
import java.util.*;
public class _1020NumberOfEnclaves {
/*
1020. Number of Enclaves
Given a 2D array A, each cell is 0 (representing sea) or 1 (representing land)

A move consists of walking from one land square 4-directionally to another land square, or off the boundary of the grid.

Return the number of land squares in the grid for which we cannot walk off the boundary of the grid in any number of moves.

 

Example 1:

Input: [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
Output: 3
*/
    /*
     * thinking process: O(mn)/O(mn)
     * the problem is to say: given one matrix, 
     */
    int N = 0;
    public int numEnclaves(int[][] A) {
        if (A == null || A.length < 1 || A[0].length < 1) return 0;
        
        int r = A.length, c = A[0].length;
        int total = 0;
        Set<String> visited = new HashSet<>();
        for(int i = 0; i< r; i++) {
            for(int j =0; j< c; j++) {
                if (A[i][j] == 1)  {
                    total++;
                    if (i == 0 || i == r-1 || j == 0 || j == c-1) {
                        helper(A, i, j, visited);
                    }
                }
            }
        }
        return total - N;
    }
    
    private void helper(int[][] A, int i, int j, Set<String> visited) {
        
        if (i < 0 || i >=A.length || j < 0 || j >= A[0].length 
                || A[i][j] == 0 || visited.contains(i +"->" + j)) return;
        
        visited.add(i +"->" + j);
        N++;
        int r = A.length, c = A[0].length;
        int[][] dirs = {{-1,0}, {1, 0}, {0,1}, {0,-1}};
        for(int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            helper(A, x, y, visited);
        }
    }

    class Solution_FloodFill {
        int[][] grid;
        public int numEnclaves(int[][] A) {
            this.grid = A;
            for(int i=0;i<grid.length;i++){
                fill(i,0);
                fill(i,grid[0].length-1);
            }
            for(int j=0;j<grid[0].length;j++){
                fill(0,j);
                fill(grid.length-1, j);
            }
    
            int res = 0;
            for(int i=1;i<grid.length;i++){
                for(int j=1;j<grid[0].length;j++){
                    res += grid[i][j];
                }
            }
            return res;
        }
    
        private void fill(int i,int j){
            if(i<0||j<0||i>=grid.length || j>=grid[0].length || grid[i][j]==0){
                return;
            }
            grid[i][j]=0;
            fill(i-1,j);
            fill(i+1,j);
            fill(i,j-1);
            fill(i,j+1);
        }
    
    }
}