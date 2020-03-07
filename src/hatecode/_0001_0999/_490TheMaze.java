package hatecode._0001_0999;
import java.util.*;
public class _490TheMaze {
    /*
     * 490. The Maze There is a ball in a maze with empty spaces and walls. The ball
     * can go through empty spaces by rolling up, down, left or right, but it won't
     * stop rolling until hitting a wall. When the ball stops, it could choose the
     * next direction.
     * 
     * Given the ball's start position, the destination and the maze, determine
     * whether the ball could stop at the destination.
     * 
     * The maze is represented by a binary 2D array. 1 means the wall and 0 means
     * the empty space. You may assume that the borders of the maze are all walls.
     * The start and destination coordinates are represented by row and column
     * indexes.
     *

Example 1:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: true
*/
    //DFS O(mn)/O(mn)
    public boolean hasPath(int[][] m, int[] src, int[] dst) {
        if (m == null || m.length < 1 || m[0].length < 1) return true;
        int r = m.length, c=m[0].length;
        if (src[0] < 0 || src[0] >= r || src[1] < 0 || src[1] >= c
            || dst[0] < 0 || dst[0] >= r || dst[1] < 0 || dst[1] >= c) return false;
        //visited here records the coordination which the ball rolls to the edge of the 
        //wall
        int[][] visited = new int[r][c];
        return helper(m, src[0], src[1], dst, visited);
    }
    
    private boolean helper(int[][] m, int i, int j, int[] dst, int[][] visited) {
        if (visited[i][j] == 1) return false;

        if (i == dst[0] && j == dst[1]) return true;
        
        visited[i][j] = 1;
        int[][] dirs = {{-1,0},{0,-1},{1,0},{0,1}};
        for(int[] dir : dirs){
            int x = i + dir[0];
            int y = j + dir[1];
            //we cannot put visited here because the ball is rolling no matter we 
            //visited it or not
            //this while loop is mimic the rolling behavior
            while(x >= 0 && y >= 0 && x < m.length && y < m[0].length && m[x][y] ==0) {
                x += dir[0];
                y += dir[1];
            }
            if (helper(m, x - dir[0], y - dir[1], dst, visited)) return true;
        }
        return false;
    }
    
    //BFS
    public boolean hasPath2(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        int[][] dirs={{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        Queue < int[] > queue = new LinkedList < > ();
        queue.add(start);
        visited[start[0]][start[1]] = true;
        while (!queue.isEmpty()) {
            int[] s = queue.remove();
            if (s[0] == destination[0] && s[1] == destination[1])
                return true;
            for (int[] dir: dirs) {
                int x = s[0] + dir[0];
                int y = s[1] + dir[1];
                while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                }
                if (!visited[x - dir[0]][y - dir[1]]) {
                    queue.add(new int[] {x - dir[0], y - dir[1]});
                    visited[x - dir[0]][y - dir[1]] = true;
                }
            }
        }
        return false;
    }
    
}