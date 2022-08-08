package hatecode._0001_0999;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ShortestDistancefromAllBuildings
 * Date : Sep, 2018
 * Description : 317. Shortest Distance from All Buildings
 */
public class _317ShortestDistancefromAllBuildings {
    /**
     * You want to build a house on an empty land which reaches all buildings
     * in the shortest amount of distance. You can only move up, down, left and right.
     * You are given a 2D grid of values 0, 1 or 2, where:

     Each 0 marks an empty land which you can pass by freely.
     Each 1 marks a building which you cannot pass through.
     Each 2 marks an obstacle which you cannot pass through.
     For example, given three buildings at (0,0), (0,4), (2,2), 
     and an obstacle at (0,2):

     1 - 0 - 2 - 0 - 1
     |   |   |   |   |
     0 - 0 - 0 - 0 - 0
     |   |   |   |   |
     0 - 0 - 1 - 0 - 0

     The point (1,2) is an ideal empty land to build a house,
     as the total travel distance of 3+3+1=7 is minimal. So return 7.


     int[][] dist
     int[][] nums

     for
        for
            if (grid[i][j] == '1')
               BFS

     * time: O(m^2 * n^2)
     * space: O(mn)
     * @param grid
     * @return
     */
    //thinking process:
    
    //the problem is to say we want to know the shortest distance in a given 2D matrix, 
    //from 0 to all buildings 1, 0 is empty, 1 is building cannot pass and 2 is obstacle. 
    
    //we start from each 1 building, we use nums to store when cell = 0, what's the building
    //number it could reach. dist is used to store the distance from 1 cell. 
    //so when we 2 loop in 2D matrix, if cell == 1, we will bfs from that cell and 
    //fill nums and dist array, 
    
    //after the scan done, we visit dist array, if buildingNUmber == nums cell
    //then that element in nums will be considered. we get the min one
    
    
    //as for BFS, we use a queue to store the elements around the elements
    //and each time, we use a visits array to store visited elements in bfs arrays. 
    
    //one point: 296. Best Meeting Point, because there are obstacles and building we cannot pass,so
    //we cannot use manhantan distance to quickly get the results
    
    
    //this type of problem is good to use dist[][] array to store the all nearest distances,
    //num[][] array to store the accessibleBuildings
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0) return -1;
        int m = grid.length;
        int n = grid[0].length;
        //dist store the distance to nearest 1 elements, bfs, so it would be always minimal distance
        int[][] dist = new int[m][n];
        //nums store the building numbers, which means how many buildings can reach this cell
        int[][] accessibleBuildingCountMatrix = new int[m][n];
        int totalbuildingCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                 if (grid[i][j] == 1) {
                    totalbuildingCount++;
                    //we start from this point, 
                    bfs(grid, i, j, dist, accessibleBuildingCountMatrix);
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && dist[i][j] != 0 && accessibleBuildingCountMatrix[i][j] == totalbuildingCount) {
                    res = Math.min(res, dist[i][j]);
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    /**
     1 - 0 - 2 - 0 - 1
     |   |   |   |   |
     0 - 0 - 0 - 0 - 0
     |   |   |   |   |
     0 - 0 - 1 - 0 - 0

     visited :
     1 - 0 - 0 - 0 - 0
     |   |   |   |   |
     0 - 0 - 0 - 0 - 0
     |   |   |   |   |
     0 - 0 - 0 - 0 - 0

     dist :
     0 - 1 - 0 - 0 - 0
     |   |   |   |   |
     1 - 0 - 0 - 0 - 0
     |   |   |   |   |
     0 - 0 - 0 - 0 - 0

     nums :
     0 - 1 - 0 - 0 - 0
     |   |   |   |   |
     1 - 0 - 0 - 0 - 0
     |   |   |   |   |
     0 - 0 - 0 - 0 - 0
     queue : (0,0)
     queue : (0,1) (1,0)
     queue : ()


     * @param grid
     * @param row
     * @param col
     * @param dist
     * @param nums
     */
    //the reason why we need a bfs function because we have to start each cell in matrix and 
    //update the nums
    private void bfs(int[][] grid, int row, int col, int[][] dist, int[][] nums) {
        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});

        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        boolean[][] visited = new boolean[m][n];
        int distance = 0;

        while (!queue.isEmpty()) {
            distance++;
            //BFS in 2D matrix
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                //four directions dfs
                for (int[] dir: dirs) {
                    int x = cur[0] + dir[0];
                    int y = cur[1] + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n 
                            && !visited[x][y] && grid[x][y] == 0) {
                        visited[x][y] = true;
                        dist[x][y] += distance;
                        nums[x][y]++;
                        queue.offer(new int[]{x, y});
                    }
                }
            }
        }


    }
}
