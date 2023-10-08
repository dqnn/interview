package hatecode._1000_1999;

import java.util.*;
public class _1102PathWithMaximumMinimumValue {
/*
1102. Path With Maximum Minimum Value
Given a matrix of integers A with R rows and C columns, find the maximum score of a path starting at [0,0] and ending at [R-1,C-1].

The score of a path is the minimum value in that path.  
For example, the value of the path 8 →  4 →  5 →  9 is 4.

A path moves some number of times from one visited cell to any 
neighbouring unvisited cell in one of the 4 cardinal directions 
(north, east, west, south).
Example 1:

Input: [[5,4,5],[1,2,6],[7,4,6]]
Output: 4
*/
    //thinking process： 
    
    //given a matrix, each cell has an integer value, so find one path from 
    //left top to bottom right, then return the min value during this path,
    
    //so bfs, but one difference here is that for visited elements, if we have max
    //path then we can re-visit here again

    /*
     * thinking process: O(rclgrc)/o(rc)
     * this problem is not easy for DFS, because it is max of min value of the path from top left 
     * to right bottom. you have to memorize the smallest value from that path. it is not easy to deisgn
     * a mem for DFS,
     * for example, let's say mem[i][j] is smallest value from (i,j)->(r-1,c-1), but you may have a smaller 
     * value, so mem here cannot help. 
     * 
     * you will have to find a greedy path with PQ.
     * 
     * 
     * we do not need to modify the original matrix value 
     */
    public int maximumMinimumPath(int[][] A) {
        if(A == null || A.length < 1 || A[0].length < 1) return -1;
        
        int m = A.length, n = A[0].length;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->Integer.compare(b[0], a[0]));
        
        boolean[][] visited = new boolean[m][n];
        
        pq.offer(new int[]{A[0][0], 0, 0});
        visited[0][0] = true;
        
        int res = Integer.MAX_VALUE;
        
        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        while(!pq.isEmpty()) {
            int[] e = pq.poll();
            int v = e[0], i = e[1], j = e[2];
            
            res = Math.min(res, v);
            if(i == m -1 && j == n -1) return res;
            
            for(int[] d:dirs) {
                int ni = i + d[0];
                int nj = j + d[1];
                if(ni >=0 && ni < m && nj >=0 && nj <n && !visited[ni][nj]) {
                    visited[ni][nj] = true;
                    pq.offer(new int[]{A[ni][nj], ni, nj});
                }
            }
            
        }
        
        return -1;
    }
}