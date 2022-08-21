package hatecode._0001_0999;

import java.util.*;
public class _778SwimInRisingWater {
/*
778. Swim in Rising Water
On an N x N grid, each square grid[i][j] represents 
the elevation at that point (i,j).
Now rain starts to fall. At time t, the depth of the water 
everywhere is t. You can swim from a square to another 
4-directionally adjacent square if and only if the elevation 
of both squares individually are at most t. You can swim 
infinite distance in zero time. Of course, you must stay 
within the boundaries of the grid during your swim.

You start at the top left square (0, 0). What is the 
least time until you can reach the bottom right square 
(N-1, N-1)?

Example 1:

Input: [[0,2],[1,3]]
Output: 3
Explanation:
At time 0, you are in grid location (0, 0).
You cannot go anywhere else because 4-directionally 
adjacent neighbors have a higher elevation than t = 0.

You cannot reach point (1, 1) until time 3.
When the depth of water is 3, we can swim anywhere inside the grid.


//the problem is to say in a 2D matrix, rain is falling and grid[i][j] means 
 *the time the rain needed to cover which means the time you have to wait, 
 *then you can across, src->dst, 
 *
 *Essentially, for 2D matrix visit, need to understand whetherwe need visited 
 * for this case, we need visit because if already visited this cell, then previous value
 * should be smaller,we do not want to visit again.
 * 
*/
  
    //O(n^2 lgn)
    
    /*
     * 
     */
    public int swimInWater(int[][] grid) {
        if (grid == null || grid.length < 1 || grid[0].length < 1) return 0;
        
        int n = grid.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->Integer.compare(a[2], b[2]));       
        boolean[][] visited = new boolean[n][n];
        pq.offer(new int[]{0,0,grid[0][0]});
        visited[0][0] = true;
        int[][] dirs = {{-1,0}, {1, 0}, {0, -1}, {0, 1}};
        while(!pq.isEmpty()) {
            int[] node = pq.poll();
            if (node[0] == n-1 && node[1] == n-1) return node[2];
            for(int[] dir : dirs) {
                int x = node[0] + dir[0];
                int y = node[1] + dir[1];
                if (x <0 || x>=n || y < 0 || y >=n || visited[x][y]) continue;
                visited[x][y] = true;
                pq.offer(new int[]{x, y, Math.max(node[2], grid[x][y])});
            }
        }
        return -1;
    }
}