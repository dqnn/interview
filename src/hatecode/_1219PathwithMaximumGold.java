package hatecode;

import java.util.*;
public class _1219PathwithMaximumGold {
/*
1219. Path with Maximum Gold
In a gold mine grid of size m * n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.

Return the maximum amount of gold you can collect under the conditions:

Every time you are located in a cell you will collect all the gold in that cell.
From your position you can walk one step to the left, right, up or down.
You can't visit the same cell more than once.
Never visit a cell with 0 gold.
You can start and stop collecting gold from any position in the grid that has some gold.
 

Example 1:

Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
Output: 24
Explanation:
[[0,6,0],
 [5,8,7],
 [0,9,0]]
Path to get the maximum gold, 9 -> 8 -> 7.
*/
    //thinking process:O(4^k + m * n), k is cells
    //the problem is to say: given an 2D array, each cell value means gold amount, you
    //can start any cell to collect gold, but u cannot visit same cell twice and should not visit
    //cell with no gold, so return max gold you can collect
    
    //so for this typical question, we can see there are multiple 
    //follow ups: 
    //1. the path and the max gold
    //2. DFS/BFS
    //3. complexity 
    
    //so brute force is to say we try every cell and dfs all max gold we can collect
    //the better way is we use BFS, assume we start from every cell, 
    //each time, we have 4 directions to collect. 
    
    //this one has one technical, BFS 2D array often use List<int[]> to remember
    //what should be stored
    //

    public int getMaximumGold(int[][] grid) {
        int ans = 0, m = grid.length, n = grid[0].length;
        int[][] oneCellTrace = new int[m][n];
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0, goldCellId = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] > 0) {
                    oneCellTrace[i][j] = 1 << goldCellId++;
                    q.offer(new int[]{i, j, grid[i][j], oneCellTrace[i][j]});
                }
            }
        }
        int[][] dirs ={{0,1}, {0,-1}, {1, 0}, {-1, 0}};
        while (!q.isEmpty()) {
            int i = q.peek()[0], j = q.peek()[1], sum = q.peek()[2], trace = q.poll()[3];
            ans = Math.max(sum, ans);
            for (int[] d: dirs) {
                int r = i + d[0], c = j + d[1];
                if (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] > 0 && (trace & oneCellTrace[r][c]) == 0) {
                    q.offer(new int[]{r, c, sum + grid[r][c], trace | oneCellTrace[r][c]});
                }
            }
        }
        return ans;
    }
    
    //brute force, O(4^n)/O(n), max depth is n
    public int getMaximumGold_BF(int[][] grid) {
        int maxGold = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                maxGold = Math.max(maxGold, getMaximumGoldBacktrack(grid, i, j, 0));
            }
        }
        return maxGold;
    }
    
    private int getMaximumGoldBacktrack(int[][] grid, int i, int j, int curGold) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0)
            return curGold;
        curGold += grid[i][j];
        int temp = grid[i][j];
        int maxGold = curGold;
        
        grid[i][j] = 0;
        maxGold = Math.max(maxGold, getMaximumGoldBacktrack(grid, i+1, j, curGold));
        maxGold = Math.max(maxGold, getMaximumGoldBacktrack(grid, i, j+1, curGold));
        maxGold = Math.max(maxGold, getMaximumGoldBacktrack(grid, i-1, j, curGold));
        maxGold = Math.max(maxGold, getMaximumGoldBacktrack(grid, i, j-1, curGold));
        grid[i][j] = temp;
        
        return maxGold;
    }
}