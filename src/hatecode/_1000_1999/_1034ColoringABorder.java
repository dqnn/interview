package hatecode._1000_1999;

import java.util.*;
public class _1034ColoringABorder {

/*
1034. Coloring A Border
Given a 2-dimensional grid of integers, each value in the grid represents the color of the grid square at that location.

Two squares belong to the same connected component if and only if they have the same color and are next to each other in any of the 4 directions.

The border of a connected component is all the squares in the connected component that are either 4-directionally adjacent to a square not in the component, or on the boundary of the grid (the first or last row or column).

Given a square at location (r0, c0) in the grid and a color, color the border of the connected component of that square with the given color, and return the final grid.


*/
    
   Set<int[]> borders = new HashSet<>();
    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
        if (grid == null || grid.length < 1 || grid[0].length < 1) return grid;
        int r = grid.length, c =grid[0].length;
        boolean[][] visited = new boolean[r][c];
        int origin = grid[r0][c0];
        helper(grid, color, r0, c0,  visited, origin);
        
        for(int[] b : borders) {
            grid[b[0]][b[1]] = color;
        }
        return  grid;
    }
    
    private void helper(int[][] grid,  int color, int i, int j, boolean[][] visited, int origin) {
        visited[i][j] = true;
        if (grid[i][j] != origin) return;
        int r = grid.length, c = grid[0].length;

        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0,-1}};
        for(int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < 0 || x >= r || y <0 || y>= c || grid[x][y] != origin) {
                borders.add(new int[]{i ,j});
                continue;
            }
            if (!visited[x][y]) {
                helper(grid, color, x, y,  visited, origin);
            }
        }
    }
}