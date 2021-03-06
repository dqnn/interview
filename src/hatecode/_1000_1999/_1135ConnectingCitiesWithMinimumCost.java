package hatecode._1000_1999;

import java.util.*;
import java.util.stream.*;
public class _1135ConnectingCitiesWithMinimumCost {
/*
1135. Connecting Cities With Minimum Cost
There are N cities numbered from 1 to N.

You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1 and city2 together.  (A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1.)

Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1) that connects those two cities together.  The cost is the sum of the connection costs used. If the task is impossible, return -1.

 

Example 1:



Input: N = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
Output: 6
Explanation: 
Choosing any 2 edges will connect all cities so we choose the minimum 2.

*/
    //thinking process: O(nlgn)/O(n)
    
    //the problems is to say given a array like
    //A[i]=[1,2,3] means 1->2 or 2->1 cost is 3, so we want to 
    //find in this graph, each pair, the total mini cost, if not
    //exist, return -1, some node is not connected
    
    //so for example, N = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
    //the total cost is minimal when each pair is minimal, if not, it does not add 
    //extra to the graph, so we use UF to dectect whether they can be connected, if yes
    //and they directly connected, then we directly add cost, if not
    //we just skip, because total cost does not change.
    class DSU {
        int[] parent;
        int count;
        public DSU(int n) {
            parent = new int[n];
            count = n;
            IntStream.range(0, n).forEach(i->parent[i] = i);
        }
        
        public int find(int i) {
            while(i != parent[i]) i = parent[i];
            
            return parent[i];
        }
        
        public boolean union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if(px == py) return false;
            parent[px] = py;
            count--;
            return true;
        }
    } 
    //UF
    public int minimumCost(int n, int[][] cons) {
        if( n <= 0) return 0;
        
        DSU dsu = new DSU(n);
        Arrays.sort(cons, (a, b)->(a[2] - b[2]));
        int res = 0;
        for(int[] con : cons) {
            //System.out.println(Arrays.toString(dsu.parent));
            if (dsu.union(con[0]- 1, con[1]-1)) {
                res += con[2];
            }
            //this line is just to improve the performance
            if(dsu.count == 1) break;
        }
        return dsu.count == 1 ? res : -1;
    }
        //Prim algothrims, minimum spanning tree
        //starting one node with all its edges, we cannot start from just one node
        //and we know the mini result
        public int minimumCost_Prim(int n, int[][] cons) {
            if( n == 0 || cons == null || cons.length <1) return 0;

            boolean[] visited = new boolean[n+1];
            
            //store a-b cost and b->a cost
            Map<Integer, HashSet<int[]>> map = new HashMap<>();
            for(int[] c : cons) {
                map.computeIfAbsent(c[0], y -> new HashSet<>()).add(c);
                map.computeIfAbsent(c[1], y -> new HashSet<>()).add(new int[] {c[1], c[0], c[2]});
            }
            Queue<int[]> pq = new PriorityQueue<>((int[] a, int[] b) -> a[2] - b[2]);
            
            int k = map.keySet().iterator().next();
            visited[k] = true;
            for(int[] e : map.get(k)) pq.offer(e);
            //above 3 can be changed to:
            //visited[1] = true;
            //for(int[] e : map.get(1)) pq.offer(e);

            int r = 0;
            while(!pq.isEmpty()) {
                int[] e = pq.poll();
                if(visited[e[1]]) continue;
                r += e[2];
                visited[e[1]] = true;
                for(int[] ne : map.get(e[1])) {
                    if(!visited[ne[1]]) {
                        pq.offer(ne);
                    }
                }
            }
            return IntStream.range(1, n+1).allMatch(i -> visited[i] == true) ? r : -1;
    }
}