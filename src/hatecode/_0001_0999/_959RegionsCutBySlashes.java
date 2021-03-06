package hatecode._0001_0999;

import java.util.*;
import java.util.stream.*;
public class _959RegionsCutBySlashes {
/*
959. Regions Cut By Slashes
In a N x N grid composed of 1 x 1 squares, each 1 x 1 square consists of a /, \, or blank space.  These characters divide the square into contiguous regions.

(Note that backslash characters are escaped, so a \ is represented as "\\".)

Return the number of regions.

 

Example 1:

Input:
[
  " /",
  "/ "
]
Output: 2
*/
  
    //thinking process:O(n^2)/O(n^2)
    //so given array of string, we want to how many area are divided by these string
    // so each character in string is like 1x1 square, / means dialogue, \ means anti-
    //dialgoue, so one square can only have 3 types, / / or blank, when we put them together
    //it will have a n x n square, so return how many areas are not connected?
    
    //the difficulty is how to setup correct model, to modelize these squares to 
    //leverage Union find, so what we done is :
/*
 *   ------
 *  |   0  |
 *  |3    1|
 *  |___2__|
 *  the 2 dialogues will divide the square into 4 areas, the reasons why we doing this 
 *  way is to use integer number to represent each small square and leverage union find to 
 *  connect them with various cases
 *  for example:
 *  dialgoue, we need to union(0,1) and union(3,2)
 *  anti-dia, union(3,0), union(1,2)
 *  but if we add this 1x1 square into the big square, we also have to consider the neighours
 *  so union(previous row && same column) and union(previous cooumn && same row)
 */
    public int regionsBySlashes(String[] g) {
        if(g == null || g.length < 1) return 0;
        
        int r = g.length;
        DSU dsu = new DSU(r * r * 4);
        
        for(int i = 0; i<r; i++) {
            for(int j = 0; j< r; j++) {
                //previous row union with cur row
                if(i > 0) dsu.union(calc(g, i-1, j, 2), calc(g, i, j, 0));
                //previous column union with cur column
                if(j > 0) dsu.union(calc(g, i, j-1, 1), calc(g, i, j, 3));
                
                if(g[i].charAt(j) != '/') {
                    dsu.union(calc(g, i, j, 0), calc(g, i, j, 1));
                    dsu.union(calc(g, i, j, 2), calc(g, i, j, 3));
                }
                if(g[i].charAt(j) != '\\') {
                    dsu.union(calc(g, i, j, 0), calc(g, i, j, 3));
                    dsu.union(calc(g, i, j, 2), calc(g, i, j, 1));
                }
            }
        }
        
        return dsu.count;
    }
    
    public int calc(String[] g, int i, int j, int k) {
        return (i * g.length + j) * 4 + k;
    }
    
    class DSU {
        int[] parent;
        int count = 0;
        public DSU(int n) {
            parent = new int[n];
            count = n;
            IntStream.range(0, n).forEach(i->parent[i] = i);
            //System.out.println(Arrays.toString(parent));
        }
        
        public void union(int x, int y) {
            x = find(x);
            y = find(y);
            if(x != y) {
                parent[x] = y;
                count--;
            }
           
        }
        //recursive 
        public int find(int x) {
            if(x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            //System.out.println(count);
            return parent[x];
        }
    //iterative of find
    private int find_Iterative(int v) {
        int root = v;
        while(parent[root] != root) {
            root = parent[root];
        }
        
        while(parent[v] != root) {
            int next = parent[v];
            parent[v] = root;
            v = next;
        }
        return root;
    }
    }
    
    //this is tricky solution by using 3x3 cells
    int ans = 0;
    public int regionsBySlashes_UpScaledTo3(String[] grid) {
        int n = grid.length;
        // Transform grid into map
        boolean[][] map = new boolean[3*n][3*n];
        for(int i = 0; i < 3*n; i++) Arrays.fill(map[i], true);
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i].charAt(j) == '/') map[3*i+2][3*j] = map[3*i+1][3*j+1] = map[3*i][3*j+2] = false;
                if(grid[i].charAt(j) == '\\') map[3*i][3*j] = map[3*i+1][3*j+1] = map[3*i+2][3*j+2] = false;
            }
        }
        // DFS
        for(int i = 0; i < 3*n; i++) {
            for(int j = 0; j < 3*n; j++) {
                if(map[i][j]) {
                    ans++;
                    dfs(map, i, j);
                }
            }
        }
        return ans;
    }
    private void dfs(boolean[][] map, int i, int j) {
        if(0 <= i && i < map.length && 0 <= j && j < map[0].length && map[i][j]) {
            map[i][j] = false;
            dfs(map, i-1, j);
            dfs(map, i+1, j);
            dfs(map, i, j-1);
            dfs(map, i, j+1);
        }
    }
}