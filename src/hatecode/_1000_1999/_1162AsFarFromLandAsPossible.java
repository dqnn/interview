package hatecode._1000_1999;

import java.util.*;
public class _1162AsFarFromLandAsPossible {
/*
1162. As Far from Land as Possible
Given an N x N grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell such that its distance to the nearest land cell is maximized and return the distance.

The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.

If no land or water exists in the grid, return -1.

 

Example 1:
Input: [[1,0,1],[0,0,0],[1,0,1]]
Output: 2
*/
    
    //thinking process: O(m * n)/O(1)
    
    //the problem is to say: given matrix m * n, filled with 0 and 1, find the max distance between 0 and all 1s, 
    //this means they all have same Manhattan distance
    
    //we use BFS to scan the grid, first we add all 1s into queue, then we bfs expand 
    //to look for 0s, change to 1s, so we can avoid visit again, if we cannot change the origin
    //grid then we use visited array
    
    //how many levels visited - 1 is actually the Manhattan distance, for above example
    /*[[1,0,1],      [1,1,1]     [1,1,1]
     * [0,0,0],  --> [1,0,1]  -> [1,1,1] -> nothing to do
     * [1,0,1]]      [1,1,1]     [1,1,1] 
     * q.size = 4     q.size= 4    q.size= 1
     * 
     * but overall the distance is 2, last one means we already there and we should remove last step.
     */
    public int maxDistance_BFS(int[][] g) {
        if(g == null || g.length < 1 || g[0].length < 1) return 0;
        
        Queue<int[]> q = new LinkedList<>();
        int r = g.length, c = g[0].length;
        for(int i = 0; i< r;i++) {
            for( int j = 0; j< c; j++) {
                if(g[i][j] == 1) q.offer(new int[]{i,j});
            }
        }
        
        if(q.size() == 0 || q.size() == r * c) return -1;
        int res = 0;
        int[][] dirs = {{-1, 0}, {0,-1}, {0, 1}, {1,0}};
        while(!q.isEmpty()) {
            int size = q.size();
            while(size-- > 0) {
                int[] p = q.poll();
                for(int[] d : dirs) {
                    int i = p[0] + d[0];
                    int j = p[1] + d[1];
                    if (i >=0 && i < r && j >=0 && j < c && g[i][j] == 0) {
                        g[i][j] = 1;
                        q.offer(new int[]{i, j});
                    }
                }
            }
            res++;
        }
        return res-1;
    }
    
    //TODO: to understand this, 
    public int maxDistance(int[][] g) {
        if(g == null || g.length < 1 || g[0].length < 1) return 0;
        
        int r = g.length, c = g[0].length, res = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (g[i][j] == 1) continue;
                g[i][j] = 201; //201 here cuz as the despription, the size won't exceed 100.
                if (i > 0) g[i][j] = Math.min(g[i][j], g[i-1][j] + 1);
                if (j > 0) g[i][j] = Math.min(g[i][j], g[i][j-1] + 1);
            }
        }
        
        for (int i = r-1; i > -1; i--) {
            for (int j = c-1; j > -1; j--) {
                if (g[i][j] == 1) continue;
                if (i < r-1) g[i][j] = Math.min(g[i][j], g[i+1][j] + 1);
                if (j < c-1) g[i][j] = Math.min(g[i][j], g[i][j+1] + 1);
                res = Math.max(res, g[i][j]); //update the maximum
            }
        }
        
        return res == 201 ? -1 : res - 1;
        
    }
}