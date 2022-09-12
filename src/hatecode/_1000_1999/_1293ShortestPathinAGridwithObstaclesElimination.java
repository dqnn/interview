package hatecode._1000_1999;

import java.util.*;
public class _1293ShortestPathinAGridwithObstaclesElimination {
/*
1293. Shortest Path in a Grid with Obstacles Elimination
You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle). You can move up, down, left, or right from and to an empty cell in one step.

Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1) given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.


*/
    //thinking process: O(mnk)/O(mn)
    
    //the problem is to say, given a 2D matrix, 1 is block while 0 is empty you can cross,
    //you have k chances to clear the obstacle in that cell, return 
    //smallest path
    public int shortestPath(int[][] g, int k) {
        
        int res = 0;
        Queue<int[]> q = new LinkedList<>();
        //[i][j][step][left number to consume]
        int r=g.length, c=g[0].length;
        q.offer(new int[]{0,0,k});
        
        int[][] visited = new int[r][c];
        for(int i =0; i< r; i++) {
            for(int j = 0; j< c;j++) {
                visited[i][j] = -1;
            }
        }
        int[][] dirs ={{-1,0}, {0,-1}, {0,1}, {1, 0}};
        int step = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            while(size-- > 0) {
                int[] e = q.poll();
                int i = e[0], j =e[1];
                if(i == r- 1 && j == c-1) return step;

                for(int[] d: dirs) {
                    int nextX = i + d[0];
                    int nextY = j + d[1];
                    int nextK = e[2];
                    if(nextX >=0 && nextY>=0 && nextX < r && nextY < c ) {
                       

                        if (g[nextX][nextY] == 1 && nextK > 0) {
                            nextK -= 1;
                        } else if (g[nextX][nextY]== 1 && nextK == 0) {
                            continue;
                        }
                       
                        if(nextK <= visited[nextX][nextY]) continue;
                        
                        visited[nextX][nextY] = nextK;
                        q.offer(new int[]{nextX, nextY, nextK});
                        
                    }
                
                }
            }
            step++;
            //q.forEach(e->System.out.print(Arrays.toString(e)));
            //System.out.println("");
            //queue.forEach(System.out::println);
        }
        return -1;
    }
    
    //classic solution, 
    
    //thinking process: O(mnk)/o(mnk)
    public int shortestPath_BF(int[][] g, int k) {
        
        int res = 0;
        Queue<int[]> q = new LinkedList<>();
        //[i][j][step][left number to consume]
        int r=g.length, c=g[0].length;
        q.offer(new int[]{0,0,k});
        
        int[][][] visited = new int[r][c][k+1];
        int[][] dirs ={{-1,0}, {0,-1}, {0,1}, {1, 0}};
        int step = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            while(size-- > 0) {
                int[] e = q.poll();
                int i = e[0], j =e[1];
                if(i == r- 1 && j == c-1) return step;

                for(int[] d: dirs) {
                    int nextX = i + d[0];
                    int nextY = j + d[1];
                    int nextK = e[2];
                    if(nextX >=0 && nextY>=0 && nextX < r && nextY < c ) {
                        //temp

                        if (g[nextX][nextY] == 1 && nextK > 0) {
                            nextK -= 1;
                        } else if(g[nextX][nextY]== 1 && nextK == 0) {
                             continue;
                        } else {

                        }
                       
                        if(visited[nextX][nextY][nextK] == 1) continue;
                        else {
                            visited[nextX][nextY][nextK] = 1;
                            q.offer(new int[]{nextX, nextY, nextK});
                        }
                    }
                
                }
            }
            step++;
            //q.forEach(e->System.out.print(Arrays.toString(e)));
            //System.out.println("");
            //queue.forEach(System.out::println);
        }
        return -1;
    }
}