package hatecode;
public class _980UniquePathsIII {
/*
980. Unique Paths III
On a 2-dimensional grid, there are 4 types of squares:

1 represents the starting square.  There is exactly one starting square.
2 represents the ending square.  There is exactly one ending square.
0 represents empty squares we can walk over.
-1 represents obstacles that we cannot walk over.
Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.

 

Example 1:

Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
Output: 2
*/
    //the problem is to say: given a matrix, 1 is start point, 2 is end point,-1 os obstacle
    //0 is empty cells, so we want to find how many ways from 1 to 2 and walk every empty 
    //cell. how many ways.
    
    //so we can try DP or DFS, but for dfs one way is to think how can we make sure we walk every
    //empty cell. 
    
    //since we only 1 source and 1 destination, so we use re-write the matrix value and then re
    //cover after dfs and 
    //use empty to remember how many empty cells
    int res = 0, empty = 1, sx, sy, ex, ey;
    public int uniquePathsIII(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) empty++;
                else if (grid[i][j] == 1) {
                    sx = i;
                    sy = j;
                } else if (grid[i][j] == 2) {
                    ex = i;
                    ey = j;
                }
            }
        }
        dfs(grid, sx, sy);
        return res;
    }

    public void dfs(int[][] grid, int x0, int y0) {
        if (check(grid, x0, y0) == false) return;
        if (x0 == ex && y0 == ey && empty == 0) {
            res++;
            return;
        }
        //say we visited, 
        grid[x0][y0] = -2;
        //reduced one 
        empty--;
        dfs(grid, x0 + 1, y0);
        dfs(grid, x0 - 1, y0);
        dfs(grid, x0, y0 + 1);
        dfs(grid, x0, y0 - 1);
        //and recovery
        grid[x0][y0] = 0;
        empty++;
    }

    public boolean check(int[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;
        return 0 <= i && i < m && 0 <= j && j < n && grid[i][j] >= 0;
    }
    
    //DP solution O(N2^N)/O(N)  N =RC
    class Solution {
        int ans;
        int[][] grid;
        int R, C;
        int tr, tc, target;
        int[] dr = new int[]{0, -1, 0, 1};
        int[] dc = new int[]{1, 0, -1, 0};
        Integer[][][] memo;

        public int uniquePathsIII(int[][] grid) {
            this.grid = grid;
            R = grid.length;
            C = grid[0].length;
            target = 0;

            int sr = 0, sc = 0;
            for (int r = 0; r < R; ++r)
                for (int c = 0; c < C; ++c) {
                    if (grid[r][c] % 2 == 0)
                        target |= code(r, c);

                    if (grid[r][c] == 1) {
                        sr = r;
                        sc = c;
                    } else if (grid[r][c] == 2) {
                        tr = r;
                        tc = c;
                    }
                }

            memo = new Integer[R][C][1 << R*C];
            return dp(sr, sc, target);
        }

        public int code(int r, int c) {
            return 1 << (r * C + c);
        }

        public Integer dp(int r, int c, int todo) {
            if (memo[r][c][todo] != null)
                return memo[r][c][todo];

            if (r == tr && c == tc) {
                return todo == 0 ? 1 : 0;
            }

            int res = 0;
            for (int k = 0; k < 4; ++k) {
                int nr = r + dr[k];
                int nc = c + dc[k];
                if (0 <= nr && nr < R && 0 <= nc && nc < C) {
                    if ((todo & code(nr, nc)) != 0)
                        res += dp(nr, nc, todo ^ code(nr, nc));
                }
            }
            memo[r][c][todo] = res;
            return res;
        }
    }
}