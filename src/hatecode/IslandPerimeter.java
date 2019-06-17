package hatecode;
class  IslandPerimeter {
/*
You are given a map in form of a two-dimensional integer grid where 
1 represents land and 0 represents water.

Grid cells are connected horizontally/vertically (not diagonally). 
The grid is completely surrounded by water, and there is exactly one 
island (i.e., one or more connected land cells).

The island doesn't have "lakes" (water inside that isn't connected to 
the water around the island). One cell is a square with side length 1. 
The grid is rectangular, width and height don't exceed 100. Determine the 
perimeter of the island.

 

Example:

Input:
[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

Output: 16

*/  //thinking process: we only count down and right neighbours, so we reduce 
    //the overlap ones
    
    //interview friendly: 
    //the key is understand the perimeter, 4 * nodes - 2 *neighbours
    // because both have 1 side become 0 now, so we need to - 2 each time
    public int islandPerimeter(int[][] grid) {
        int islands = 0, neighbours = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    islands++; // count islands
                    if (i < grid.length - 1 && grid[i + 1][j] == 1) neighbours++; // count down neighbours
                    if (j < grid[i].length - 1 && grid[i][j + 1] == 1) neighbours++; // count right neighbours
                }
            }
        }

        return islands * 4 - neighbours * 2;
    }
    //DFS solution
    public int islandPerimeter2(int[][] grid) {
        if (grid == null) return 0;
        for (int i = 0 ; i < grid.length ; i++){
            for (int j = 0 ; j < grid[0].length ; j++){
                if (grid[i][j] == 1) {
                    return getPerimeter(grid,i,j);
                }
            }
        }
        return 0;
    }
    
    public int getPerimeter(int[][] grid, int i, int j){
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length
                || grid[i][j] == 0) {
                 return 1;
        }
        //here is the key, we have visited this element, so we return 0
        if (grid[i][j] == -1) return 0;
        
        int count = 0;
      //here is the key, we can aviod some run in parent loop
        grid[i][j] = -1;
        
        count += getPerimeter(grid, i-1, j);
        count += getPerimeter(grid, i, j-1);
        count += getPerimeter(grid, i, j+1);
        count += getPerimeter(grid, i+1, j);
        
        return count;
        
    }
}