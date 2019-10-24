package hatecode;

import java.util.*;
public class _1057CampusBikes {
/*
1057. Campus Bikes
On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.

Our goal is to assign a bike to each worker. Among the available bikes and workers, we choose the (worker, bike) pair with the shortest Manhattan distance between each other, and assign the bike to that worker. (If there are multiple (worker, bike) pairs with the same shortest Manhattan distance, we choose the pair with the smallest worker index; if there are multiple ways to do that, we choose the pair with the smallest bike index). We repeat this process until there are no available workers.

The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.

Return a vector ans of length N, where ans[i] is the index (0-indexed) of the bike that the i-th worker is assigned to.
Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
Output: [1,0]
*/
    public int[] assignBikes(int[][] workers, int[][] bikes) {
       if(workers == null || bikes == null) return new int[]{};
        
        //a[2], a[0]=bike index, a[1] = workerIndex, a[2] - distance
       //first smaller distance win
       //second if distance is the same, then smaller workerIndex win
       //if workerIndex is the same, then smaller bike index win
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->(a[2] == b[2] ? 
                    (a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]) : a[2] - b[2]));
        
        for(int i = 0; i< workers.length; i++) {
            for(int j = 0; j < bikes.length; j++) {
                pq.offer(new int[]{i, j, Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1])});
            }
        }
        
        //this is typical scan to get the combination answers
        Set<Integer> visited = new HashSet<>();
        int[] res = new int[workers.length]; 
        //System.out.println(res.length);
        Arrays.fill(res, -1);
        while(visited.size() < workers.length) {
        //while(!pq.isEmpty()) {//this also work but it is slower
            int[] node = pq.poll();
            if (res[node[0]] == -1 && !visited.contains(node[1])) {
                res[node[0]] = node[1];
                visited.add(node[1]);
            }
        }
        return res;
    }
}