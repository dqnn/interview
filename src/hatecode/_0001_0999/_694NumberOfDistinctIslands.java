package hatecode._0001_0999;

import java.util.*;

public class _694NumberOfDistinctIslands {
/*
 694 Number Of Distinct Islands
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's
 (representing land) connected 4-directionally (horizontal or vertical.)
  You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as 
another if and only if one island can be translated (and not rotated or reflected) 
to equal the other.

Example 1:
11000
11000
00011
00011
Given the above grid map, return 1.
Example 2:
11011
10000
00001
11011
Given the above grid map, return 3.

Notice that:
11
1
and
 1
11
are considered different island shapes, because we do not consider reflection / rotation.
 */
    //the question is to say, ask how many different shape of these lands have from your eyes into the screen
    
    // so we use a char o, u, d,l,r to describe our next direction, after each, we will marki,j =0
    //so next would not consider this one. 
    
    //finally we will count how many in set.
    public static int numDistinctIslands(int[][] grid) {
    Set<String> set = new HashSet<>();
    for(int i = 0; i < grid.length; i++) {
        for(int j = 0; j < grid[i].length; j++) {
            if(grid[i][j] != 0) {
                StringBuilder sb = new StringBuilder();
                dfs(grid, i, j, sb, "o"); // origin
                set.add(sb.toString());
            }
        }
    }
    System.out.println(set);
    return set.size();
}
private static void dfs(int[][] grid, int i, int j, StringBuilder sb, String d) {
    if(i < 0 || i == grid.length || j < 0 || j == grid[i].length 
       || grid[i][j] == 0) return;
    //every step will be added here
    sb.append(d);
    //we mark each point to 0, so we should not have any other call to here for only 1 connected 
    //islands
    grid[i][j] = 0;
    
    
    dfs(grid, i, j+1, sb, "r");
    dfs(grid, i+1, j, sb, "d");
    dfs(grid, i, j-1, sb, "l");
    dfs(grid, i-1, j, sb, "u");
   
    
    //this line of code could not be removed because some cases will have wered shapes to be same string
    sb.append("b"); // back
}

public static void main(String[] args) {
    int[][] grid = {{1,1,0,0,0}, 
                    {1,0,0,0,0},
                    {0,0,0,0,0},
                    {1,1,0,0,0},
                    {0,1,0,0,0}};
    System.out.println(numDistinctIslands(grid));
}
}