package hatecode._0001_0999;

import java.util.*;
import java.util.Queue;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : WallsandGates
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 286. Walls and Gates
 */
public class _286WallsandGates {
    /**
     * You are given a m x n 2D grid initialized with these three possible values.

     -1 - A wall or an obstacle.
     0 - A gate.
     INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume
     that the distance to a gate is less than 2147483647.
     Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate,
     it should be filled with INF.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.


     For example, given the 2D grid:
     INF  -1  0  INF
     INF INF INF  -1
     INF  -1 INF  -1
     0  -1 INF INF

     After running your function, the 2D grid should be:
     3  -1   0   1
     2   2   1  -1
     1  -1   2  -1
     0  -1   3   4

     time : O(4^n)
     space : O(4^n)

     * @param rooms
     */

    // space : O(n)
    // thinking process:
    
    //this problem is to say in a 2D array,  we cannot have visited for this question, because later 
    //the cell has to be changed
    //we have 3 types of elements, INF empty room, -1 means wall, 0 means gate
    // so we need to find for each room, the smallest steps to gate, 
    // each move(4 directions) will be 1. and mark each room with steps to nearest gate
    
    //we can start from each room, and we use BFS to visit each gate and get the nearest one. 
    //another way is to visit by dfs, we start from gate, and record each steps in each room until 
    //we find next shorter ones
    
    //dfs exit condition: rooms[i][j] < dis , dis start value is 0. and we set room value 
    //in dfs function

    public void wallsAndGates_BFS(int[][] rooms) {
        if(rooms == null || rooms.length < 1 || rooms[0].length < 1) return;
        int r = rooms.length, c = rooms[0].length;
        Queue<int[]> q = new LinkedList<>();
        for(int i =0; i<r;i++) {
            for(int j =0; j<c;j++) {
                if (rooms[i][j] == 0) {
                  q.offer(new int[]{i, j});
                }
            }
        }
        
        int d = 0;
        int[][] dirs = {{-1,0},{1, 0}, {0,1}, {0,-1}};
        while(!q.isEmpty()) {
            d++;
            int size = q.size();
            while(size-- > 0) {
                int[] p = q.poll();
                for(int[] dir : dirs) {
                    int x = p[0] + dir[0];
                    int y = p[1] + dir[1];
                    if(x >=0 && x<r && y >=0 &&y < c && rooms[x][y] != -1 && rooms[x][y] != 0 && rooms[x][y] > d) {
                        q.offer(new int[]{x, y});
                        rooms[x][y] = Math.min(rooms[x][y], d);
                        
                    }
                }
            }
        }
    }
    
    
    public void wallsAndGates_Naive_BFS(int[][] rooms) {
        if(rooms == null || rooms.length < 1 || rooms[0].length < 1) return;
        int r = rooms.length, c = rooms[0].length;
        
        for(int i =0; i<r;i++) {
            for(int j =0; j<c;j++) {
                if (rooms[i][j] == 0) {
                  helper(rooms, i, j);
                }
            }
        }
    }
    
    private void helper(int[][] g, int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.offer(new int[]{i, j});
        visited.add(i +"->" + j);
        int r= g.length, c =g[0].length;
        int d = 0;
        int[][] dirs = {{-1,0},{1, 0}, {0,1}, {0,-1}};
        while(!q.isEmpty()) {
            d++;
            int size = q.size();
            while(size-- > 0) {
                int[] p = q.poll();
                for(int[] dir : dirs) {
                    int x = p[0] + dir[0];
                    int y = p[1] + dir[1];
                    if(x >=0 && x<r && y >=0 &&y < c && g[x][y] != -1 
                            && g[x][y] !=0 && !visited.contains(x +"->" + y)) {
                        q.offer(new int[]{x, y});
                        visited.add(x + "->" + y);
                        g[x][y] = Math.min(g[x][y], d);
                        
                    }
                }
            }
        }
    }
}
