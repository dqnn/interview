package hatecode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/*
787. Cheapest Flights Within K Stops
There are n cities connected by m flights. Each fight starts from city u and arrives 
at v with a price w.

Now given all the cities and flights, together with starting city src and the destination 
dst, your task is to find the cheapest price from src to dst with up to k stops. 
If there is no such route, output -1.

Example 1:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
Output: 200
 */
public class CheapestFlightsWithinKStops {
    // we use shortest path algorithms
    //O(E + nlgn) E is total flights , nlgn is the heap sort/ O(n) (heap)
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        if (n <= 1 || flights  == null || src >= n || dst >=n || K < 0) {
            return -1;
        }
        
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for(int[] fl : flights) {
            map.computeIfAbsent(fl[0], v->new HashMap<>()).put(fl[1],fl[2]);
        }
        Queue<int[]> q = new PriorityQueue<>((a,b)->(a[2] - b[2]));
        q.offer(new int[]{src, K + 1, 0});
        while(!q.isEmpty()) {
            int[] fy = q.poll();
            int city = fy[0];
            int stop = fy[1];
            int price = fy[2];
            if (city == dst) return price;
            if (stop > 0) {
                Map<Integer, Integer> adj = map.getOrDefault(city, new HashMap<>());
                for(int cty : adj.keySet()) {
                    q.offer(new int[]{cty, stop - 1, price + adj.get(cty)});
                }
            }
        }
        return -1;
    }
    
     
}