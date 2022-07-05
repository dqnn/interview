package hatecode._1000_1999;

import java.util.*;
public class _1091ShortestPathInBinaryMatrix {
/*
1091. Shortest Path in Binary Matrix
In an N by N square grid, each cell is either empty 
(0) or blocked (1).

A clear path from top-left to bottom-right has length k 
if and only if it is composed of cells C_1, C_2, ..., C_k such that:

Adjacent cells C_i and C_{i+1} are connected 8-directionally 
(ie., they are different and share an edge or corner)
C_1 is at location (0, 0) (ie. has value grid[0][0])
C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
If C_i is located at (r, c), then grid[r][c] is empty 
(ie. grid[r][c] == 0).
Return the length of the shortest such clear path from 
top-left to bottom-right.  If such a path does not exist, 
return -1.

Example 1:
Input: [[0,0,0],[1,1,0],[1,1,0]]
Output: 4
*/
    //thinking process: 
    
    //given 2D matrix, 0 is empty, 1 is blocked, find the shortest path from top left
    //to right bottom
    
    //we use BFS to find shortest, but make sure we should get all elements in queue in one bfs
    public int shortestPathBinaryMatrix(int[][] m) {
        if(m == null || m.length < 1 || m[0].length < 1 || m[0][0] == 1) return -1;
        
        Queue<int[]> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.add(new int[]{0, 0});
        visited.add("0->0");
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0},{1,-1},{-1,1},{-1,-1},{1,1}};
        int r = m.length - 1, c = m[0].length - 1;
        int res = 1;
        while(!q.isEmpty()) {
            int n = q.size();
            while(n-- > 0) {
                int[] p = q.poll();
                if(p[0] == r && p[1] == c) return res;
                for(int[] d: dirs) {
                    int x = p[0] + d[0];
                    int y = p[1] + d[1];
                    if(x >= 0 && y >=0 && x <= r && y <= c && !visited.contains(x + "->" + y) && m[x][y] == 0) {
                        visited.add(x + "->" + y);
                        q.offer(new int[]{x, y});
                    }
                }
            }
            res++;
        }
        return -1;
    }
}