package hatecode._0001_0999;
import java.util.*;
public class _934ShortestBridge {
/*
934. Shortest Bridge
In a given 2D binary array A, there are two islands.  
(An island is a 4-directionally connected group of 1s not 
connected to any other 1s.)

Now, we may change 0s to 1s so as to connect the two islands 
together to form 1 island.

Return the smallest number of 0s that must be flipped.  
(It is guaranteed that the answer is at least 1.)

 

Example 1:

Input: [[0,1],[1,0]]
Output: 1

*/
    //thinking process: O(mn)O(mn)
    /*
    the problem is to say: given one matrix and there is only 2 islands in the matrix, you can flip
    0 to 1 make them connected, return the min number of cells needs to be flipped.
    
     * 1. first build the queue, the queue only contains the [i][j] which is first
     * round of water elements, 
     * 2. then we BFS on these elements, if we found anyone not visited before and it is 
     * land, then we found it
     */
    int[][] dirs = {{-1,0}, {1,0}, {0,1}, {0,-1}};
    public int shortestBridge(int[][] A) {
        int r = A.length, c = A[0].length;
        boolean[][] visited = new boolean[r][c];
        
        Queue<int[]> q = new LinkedList<>();
        
        boolean found = false;
        for(int i = 0; i< r; i++) {
            if (found) break;
            for(int j = 0; j<c;j++) {
                if (A[i][j] == 1) {
                    helper(A, i, j, visited, q);
                    found = true;
                    break;
                }
            }
        }
        
        int step = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            
            
            while(size-- > 0) {
                int[] e = q.poll();
                int x = e[0], y=e[1];
                if (A[x][y] == 1) return step;
                for(int[] d: dirs) {
                    int i = x + d[0];
                    int j = y + d[1];
                    if (i >0 && i < r && j >=0 && j < c && !visited[i][j]) {
                         visited[i][j] = true;
                         q.offer(new int[]{i, j});
                    }
                }
            }
            step++;
        }
        
        return -1;
    }
    
    //we only add these most out round of 0 into queue, not all of them
    private void helper(int[][] A, int i, int j, boolean[][] visited, Queue<int[]> q) {
        int r = A.length, c = A[0].length;
        if (i >=0 && i < r && j >=0 && j < c && !visited[i][j]) {
            visited[i][j] = true;
            if (A[i][j] == 1) {
                for(int[] d: dirs) {
                    int x = i + d[0];
                    int y = j + d[1];
                    helper(A, x, y, visited, q);
                }
            } else {
                q.offer(new int[]{i,j});
            }
        }
    }
}