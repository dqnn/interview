package hatecode._0001_0999;

import java.util.*;
import java.util.Queue;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : NumberofIslands
 * Creator : professorX
 * Date : Aug, 2018
 * Description : 200. Number of Islands
 */
public class _200NumberofIslands {

    /**
     * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
     * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
     * You may assume all four edges of the grid are all surrounded by water.

     Example 1:

     11110
     11010
     11000
     00000
     Answer: 1

     Example 2:

     11000
     11000
     00100
     00011
     Answer: 3

     time : O(m * n)
     space : O(n)

     * @param grid
     * @return
     */
    
    //BFS and DFS need to update the matrix
    private int m;
    private int n;

    public int numIslands(char[][] grid) {
        int res = 0;
        m = grid.length;
        if (m == 0) return 0;
        n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    /** in the end, we only visit MN nodes, and for depth, we only need to go to 
     * level MN deep
     time : O(m * n)
     space : O(m * n)
     * @param grid
     * @param i
     * @param j
     */

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == '0') return;
        // we mark it as 0 since we already reached  so mark it as 0
        grid[i][j] = '0';
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
    }

    public int numIslands2(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; i++) {
                if (grid[i][j] == '1') {
                    bfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    private void bfs(char[][] grid, int x, int y) {
        grid[x][y] = '0';
        int n = grid.length;
        int m = grid[0].length;
        Queue<Integer> queue = new LinkedList<>();
        int code = x * m + y;
        queue.offer(code);
        while (!queue.isEmpty()) {
            code = queue.poll();
            int i = code / m;
            int j = code % m;
            if (i > 0 && grid[i - 1][j] == '1') {
                queue.offer((i - 1) * m + j);
                grid[i - 1][j] = '0';
            }
            if (i < n - 1 && grid[i + 1][j] == '1') {
                queue.offer((i + 1) * m + j);
                grid[i + 1][j] = '0';
            }
            if (j > 0 && grid[i][j - 1] == '1') {
                queue.offer((i * m) + j - 1);
                grid[i][j - 1] = '0';
            }
            if (j < m - 1 && grid[i][j + 1] == '1') {
                queue.offer((i * m) + j + 1);
                grid[i][j + 1] = '0';
            }
        }
    }
    
    //UF with two scan
    class DUS {
        int[] parent;
        int[] size;
        int count;
        public DUS(int cap) {
            parent = new int[cap];
            size = new int[cap];
            Arrays.fill(size, 1);
            for(int i = 0; i<cap; i++) parent[i] = i;
            count = cap;
        }
        
        public int find(int x) {
            while(x != parent[x]) {
                x = parent[x];
            }
            return x;
        }
        
        public void union(int x, int y) {
            x = find(x);
            y = find(y);
            if (x == y) return;
            
            if(size[x] <= size[y]) {
                size[y] += size[x];
                parent[x] = y;
            } else {
                size[x] += size[y];
                parent[y] = x;
            }
            count--;
        }
        
        public int getCount() {
            return count;
        }
    }
    
    public int numIslands_UF(char[][] g) {
        if (g == null || g.length < 1 || g[0].length < 1) return 0;
        
        int r = g.length, c = g[0].length;
        int count = 0;
        DUS dus = new DUS(r *c);
        for(int i = 0; i < r; i++) {
            for(int j =0; j< c; j++) {
                if (g[i][j] == '1') {
                /* 
                 * here is the improvemnts, we only need to look to right and down, so we don;t
                 * have to look back
                 */
                    //int[][] dirs = {{-1, 0}, {1,0}, {0,1}, {0,-1}};
                    int[][] dirs = {{1,0}, {0,1}};
                    for(int[] dir : dirs) {
                        int x = i + dir[0];
                        int y = j + dir[1];
                        
                        if (x>=0 && x< r && y>=0 && y<c && g[x][y] == '1') {
                            dus.union(i * c + j, x *c + y);
                        }
                    }
                } else count++;
            }
        }
        return dus.getCount() - count;
    }
}
