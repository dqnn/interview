package leetcode;
/*
 * author Denny Du 
 * Date 10/18/2018
576. Out of Boundary Paths
There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, 
you can move the ball to adjacent cell or cross the grid boundary in four 
directions (up, down, left, right). However, you can at most move N times. 
Find out the number of paths to move the ball out of grid boundary. The answer may be very large, 
return it after mod 109 + 7.

 

Example 1:

Input: m = 2, n = 2, N = 2, i = 0, j = 0
Output: 6
Explanation:

Example 2:

Input: m = 1, n = 3, N = 3, i = 0, j = 1
Output: 12
Explanation:

 

Note:

Once you move the ball out of boundary, you cannot move it back.
The length and height of the grid is in range [1,50].
N is in range [0,50].
 */
public class OutOfBoundaryPaths {
    
    
    
    
    
    
    
    
    
    
    //this is TLE, the problem for the case:
    // if N > 0 while we return to the cell we visited still this can be correct, as example 2. 
    
    int count = 0;
    public int findPaths(int m, int n, int N, int i, int j) {
        int[][] visited = new int[m][n];
        dfs(m, n, N, i, j, visited);
        return count;
    }
    
    private void dfs(int m, int n, int N, int i, int j, int[][] visited) {
        if (i < 0 || j < 0 || i >= m || j >= n) {
            if (N >= 0) {
                count += 1;
            }
            return;
        }
        //so if all steps are done, then leave
        if (N < 0) {
            return;
        }
        if (visited[i][j] == 1) {
            return;
        }
        
        //visited[i][j] = 1;
        
        dfs(m, n, N - 1, i+1, j, visited);
        dfs(m, n, N - 1, i-1, j, visited);
        dfs(m, n, N - 1, i, j + 1, visited);
        dfs(m, n, N - 1, i, j - 1, visited);
        //visited[i][j] = 0;
    }
}