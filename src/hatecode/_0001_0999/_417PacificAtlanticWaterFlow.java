package hatecode._0001_0999;
import java.util.*;
public class _417PacificAtlanticWaterFlow {
/*
417. Pacific Atlantic Water Flow
Given an m x n matrix of non-negative integers representing the height of each unit cell in 
a continent, the "Pacific ocean" touches the left and top edges of the matrix and the 
"Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) 
from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]]
*/
    //it is stack overflow solution, but most cases will pass, the reason why overflow because 
    //it detect all points while we should only detect border ones
    
    //the more effective way is to starting from borders, looking for increasing path(>=), and we memorize
    //the result when we dfs. 
    
    //since two ocean, so we would like to use two 2D matrix left and right to record the result of each cell,
    //last is to check whether we can flow to both side
    List<int[]> res;
    int[][] dirs = {{-1, 0},{1, 0},{0, -1},{0, 1}};

    public List<int[]> pacificAtlantic(int[][] m) {
        res = new ArrayList<>();
        if (m == null || m.length < 1 || m[0].length < 1)
            return res;

        int r = m.length, c = m[0].length;
        boolean[][] left = new boolean[r][c];
        boolean[][] right = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            helper(m, i, 0, left);
            helper(m, i, c - 1, right);
        }
        for (int i = 0; i < c; i++) {
            helper(m, 0, i, left);
            helper(m, r - 1, i, right);
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (left[i][j] && right[i][j]) res.add(new int[] {i, j });
            }
        }
        return res;
    }

    private void helper(int[][] m, int i, int j, boolean[][] visited) {
        if (visited[i][j])
            return;

        visited[i][j] = true;

        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && y >= 0 && x < m.length && y < m[0].length && m[x][y] >= m[i][j]) {
                helper(m, x, y, visited);
            }
        }
    }
}