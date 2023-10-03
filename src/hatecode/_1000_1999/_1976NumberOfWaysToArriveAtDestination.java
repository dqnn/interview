package hatecode._1000_1999;

import java.util.*;

public class _1976NumberOfWaysToArriveAtDestination {
    
/*
1976. Number of Ways to Arrive at Destination
Other similar problems set

743. Network Delay Time
2290. Minimum Obstacle Removal to Reach Corner
787. Cheapest Flights Within K Stops
1514. Path with Maximum Probability
2045. Second Minimum Time to Reach Destination
1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance
1631. Path With Minimum Effort
778. Swim in Rising Water
You are in a city that consists of n intersections numbered from 0 to n - 1 with bi-directional roads between some intersections. The inputs are generated such that you can reach any intersection from any other intersection and that there is at most one road between any two intersections.

You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means that there is a road between intersections ui and vi that takes timei minutes to travel. You want to know in how many ways you can travel from intersection 0 to intersection n - 1 in the shortest amount of time.

Return the number of ways you can arrive at your destination in the shortest amount of time. Since the answer may be large, return it modulo 109 + 7.

Input: n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
Output: 4
Explanation: The shortest amount of time it takes to go from intersection 0 to intersection 6 is 7 minutes.
The four ways to get there in 7 minutes are:
- 0 ➝ 6
- 0 ➝ 4 ➝ 6
- 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
- 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6

*/
    /*
     * interview friendly O(V+E)/O(V+E)
     * 
     * the problem is to say: given n nodes, 0-n-1, [0,1,3] means from node 0 to node 1, the cost is 3, so return how many ways we can 
     * go from 0 to n-1 with minimal cost, mod with 10^9+7.
     * 
     * 
     * 
     * 
     * 
     */
    public int countPaths(int n, int[][] A) {
        
        final long MOD = 1_000_000_007;
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] a : A) { 
            map.computeIfAbsent(a[0], v->new ArrayList<>()).add(new int[]{a[1],a[2]});
            map.computeIfAbsent(a[1], v->new ArrayList<>()).add(new int[]{a[0],a[2]});
        }
        long[] dp = new long[n], dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE); // -1 -> not visited
        Queue<long[]> q = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1])); // [node, cost]
        q.offer(new long[]{0,0});
        dp[0] = 1; 
        //we do not have to 
        dist[0] = 0;
        
        while (!q.isEmpty()) {
            long[] f = q.poll();
            int u =  (int)f[0];
            long du = f[1];
            
            // here we exit
            if (u == n - 1) return (int)(dp[u] % MOD);
            
            for (int[] vs : map.get(u)) {
                int v = vs[0], dv= vs[1];
                if (dist[v] == du + dv) // add all paths from the parent node
                    dp[v] += dp[u] % MOD;
                if (dist[v] > du + dv) {
                    dist[v] = du + dv;
                    dp[v] = dp[u]; // 
                    q.offer(new long[]{v, du+dv});
                }
            }
        }
        return -1; // shouldn't get here
    }


    /*
     * interview friendly, below is more universal one, 
     */
    public static int countPaths2(int n, int[][] A) {
        
        final long MOD = 1_000_000_007;
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] a : A) { 
            map.computeIfAbsent(a[0], v->new ArrayList<>()).add(new int[]{a[1],a[2]});
            map.computeIfAbsent(a[1], v->new ArrayList<>()).add(new int[]{a[0],a[2]});
        }
        long[] dp = new long[n], dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE); // -1 -> not visited
        Queue<long[]> q = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1])); // [node, cost]
        q.offer(new long[]{0,0});
        dp[0] = 1; 
        //we do not have to 
        dist[0] = 0;
        
        while (!q.isEmpty()) {
            long[] f = q.poll();
            int u =  (int)f[0];
            long du = f[1];
            
           
            
            for (int[] vs : map.get(u)) {
                int v = vs[0], dv= vs[1];
                if (dist[v] == du + dv) // add all paths from the parent node
                    dp[v] += dp[u] % MOD;
                if (dist[v] > du + dv) {
                    dist[v] = du + dv;
                    dp[v] = dp[u]; // 
                    q.offer(new long[]{v, du+dv});
                }
            }
        }
        return (int)(dp[n-1]%MOD);
    }

    public static void main(String[] args) {
        //if edge cost contains 0
        System.out.println(countPaths2(5, new int[][]{{0,1,1}, {0,2,1},{0,3,1},{1,4,0},{2,4,0},{3,4,0}}));
    }

    
    
    
    
}