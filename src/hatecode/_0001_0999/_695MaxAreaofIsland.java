package hatecode._0001_0999;
public class _695MaxAreaofIsland {
/*
695. Max Area of Island
You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

The area of an island is the number of cells with a value 1 in the island.

Return the maximum area of an island in grid. If there is no island, return 0.

 

Example 1:


Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
Output: 6
Explanation: The answer is not 11, because the island must be connected 4-directionally.
*/
  
    // DFS
    /*
     * thinking process: O(mn)/O(mn)
     * the problem is to say: given one matrix, 1 represents island while 0 represents water,
     * so return the max area of the islands, cell 1 means area 1
     * 
     * typical way to traverse the 2D matrix and calculate the elements
     * how many are land
     * 
     * another way to solve is to use UF 
     */
    int[][] dirs = {{-1, 0}, {1, 0}, {0,1}, {0,-1}};
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int M = grid.length;
        int N = grid[0].length;
        boolean[][] visited = new boolean[M][N];
        int res = 0;
        for (int i=0; i<M; i++) {
            for (int j=0; j<N; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    res = Math.max(res, dfs(grid, visited, i, j));
                }
            }
        }
        return res;
    }

    private int dfs(int[][] grid, boolean[][] visited, int i, int j) {
        if (i < 0 || j < 0 ||
            i>= grid.length || j >= grid[0].length ||
            grid[i][j] != 1 || visited[i][j]) return 0;

        visited[i][j] = true;
        int res = 1;
        for (int[] dir: dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            res += dfs(grid, visited, x, y);
        }
        return res;
    }



    class DSU {
        int[] parent;
        int[] size;
        int max;
        public DSU(int cap) {
            parent = new int[cap];
            size = new int[cap];
            for(int i = 0; i< cap; i++) {
                parent[i] = i;
                size[i] = 1;
            }
            max = cap > 0 ? 1 : 0;
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
            if(size[x] < size[y]) {
                parent[x] = y;
                size[y] += size[x];
                
            } else {
                parent[y] = x;
                size[x] += size[y];
            }
            
            max = Math.max(max, Math.max(size[x], size[y]));
        }
    }
    
    
    /*
     * thinking process: O(mn*a(n))/O(mn)
     * 
     * the problem is to say: given one matrix, return the max area of the matrix
     * 
     * 
     * use UF size to record each block size, then when we union two cells, we increase 
     * the size and get the max, there are two edge cases:
     * [[0,0,0,0,0]] it should be 0
     * and [[1]] should be 1
     */
    public int maxAreaOfIsland_UF(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int r = grid.length;
        int c = grid[0].length;
        
        DSU dsu = new DSU(r *c);

        int[][] dirs = {{1, 0}, {0, 1}};
        
        int count = 0;
        for (int i=0; i<r; i++) {
            for (int j=0; j<c; j++) {
                if (grid[i][j] == 1) {
                    int p1 = c * i + j;
                    for(int[] d: dirs) {
                        int ni = i + d[0];
                        int nj = j + d[1];
                        if (ni >=0 && ni < r && nj >=0 && nj < c && grid[ni][nj] == 1) {
                            int p2 = c* ni + nj;
                            dsu.union(p1, p2);
                        }
                    }
                } else count++;
            }
        }
        return count == r*c ? 0: dsu.max;
    }

}