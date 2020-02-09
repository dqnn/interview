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
    
    //so bfs, 
    public int maximumMinimumPath(int[][] A) {
        final int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> (b[0] - a[0]));
        pq.add(new int[] {A[0][0], 0, 0});
        int maxscore = A[0][0];
        A[0][0] = -1; // visited
        while(!pq.isEmpty()) {
            int[] top = pq.poll();
            int i = top[1], j = top[2], r = A.length, c = A[0].length;
            maxscore = Math.min(maxscore, top[0]);
            if(i == r - 1 && j == c - 1) return maxscore;
            for(int[] d : dirs) {
                int x = d[0] + i;
                int y = d[1] + j;
                if(x >= 0 && x < r && y >= 0 && y < c && A[x][y]>=0){
                    pq.add(new int[] {A[x][y], x, y});
                    A[x][y] = -1;
                }
            }
        }
        return -1; // shouldn't get here
    }
}