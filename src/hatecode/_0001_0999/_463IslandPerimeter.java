package hatecode._0001_0999;
public class  _463IslandPerimeter {
/*
 * 463. Island Perimeter
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

*/  //thinking process: we only count down and right neighbors, so we reduce 
    //the overlap ones
    
    //interview friendly: O(mn)/O(1) 
    //the key is understand the island perimeter, 4 * nodes - 2 *neighbous
    // because both have 1 side become 0 now, so we need to - 2 each time
    //we only count down and right side neighbour
    public int islandPerimeter(int[][] grid) {
        //island means the count of the cells of '1'
        //neighbors means the count of cells which have shared side
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




    //use global variable to solve the problem 
    int res = 0;
    public int islandPerimeter_global(int[][] A) {
        if(A == null || A.length < 1 || A[0].length < 1) return 0;
        
        int m = A.length, n = A[0].length;
        
        boolean[][] visited = new boolean[m][n];
        for(int i = 0; i<m; i++) {
            for(int j = 0; j< n; j++) {
                if (A[i][j] == 1 && !visited[i][j]){
                    visited[i][j] = true;
                    helper(A, i, j, visited);
                }
            }
        }
        
        return res;
    }
    private void helper(int[][] A, int i ,int j, boolean[][] visited) {
        int m = A.length, n = A[0].length;
       int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0,-1}};
       for(int[] d:dirs) {
           int ni = i + d[0];
           int nj = j + d[1];
           if(ni >=0 && ni <m && nj >=0 && nj < n && !visited[ni][nj] && A[ni][nj] == 1) {
               visited[ni][nj] = true;
               helper(A, ni, nj, visited);
           }
           
           if(ni < 0 || ni >= m || nj < 0 || nj >=n || A[ni][nj] == 0) {
               res++;
           }
       }
   }


  /*
  
零一组成的矩阵，意是陆地，领是水，找到最大的陆地，返回大小。注意，大小是最边缘的意，里边的不算，比如下边那个大小事8
1 1 1 0 0
1 1 1 0 0
1 1 1 0 0

   */
   public static int getIslandPermierCount(int[][] A) {
        int m = A.length, n = A[0].length;
        int res = 0;

        for(int i = 0; i< m; i++) {
            for(int j = 0; j< n; j++) {
                if(A[i][j] == 1 && helper(A, i, j)) {
                    res++;
                }
            }
        }

        return res;
   }


   private static boolean helper(int[][] A, int i, int j) {
    int m = A.length, n = A[0].length;
    
    if(i == 0 || i == m -1 || j == 0 || j == n -1) return true;

    int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0,-1}};

    for(int[] d:dirs) {
        int ni = i + d[0];
        int nj = j + d[1];

        if(ni >=0 && ni <m && nj>=0 && nj < n && A[ni][nj] == 0) {
            return true;
        }
    }
    return false;
   }


   public static void main(String[] args) {
       System.out.println(getIslandPermierCount(new int[][]{{1,1,1,0,0}, {1,1,1,0,0}, {1,1,1,0,0}}));
   }

}