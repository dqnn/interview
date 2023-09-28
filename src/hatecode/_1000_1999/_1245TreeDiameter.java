package hatecode._1000_1999;

import java.util.*;

public class  _1245TreeDiameter {
    
    /*
    similar problem: 	
    543	 Diameter of Binary Tree 
    1522 Diameter of N-Ary Tree   
    1245. Tree Diameter
    The diameter of a tree is the number of edges in the longest path in that tree.

There is an undirected tree of n nodes labeled from 0 to n - 1. You are given a 2D array edges where edges.length == n - 1 and edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the tree.

Return the diameter of the tree.

Example 1:


Input: edges = [[0,1],[0,2]]
Output: 2
    */

    /*
     * thinking process: O(n)/O(n)
     * 
     * the problem is to say: given 2D matrix as edges, edges[i] means [start, end] as undirected graph, so 
     * return the max path from node to another node 
     * 
     * we can start from any node, here we should start from 0 because we do not know n, one common pattern from such problem is that there is no circle 
     * in graph, so you can go from any node.
     * 
     * another tricky part is that should use Integer[] as memo, becaus it has null or not to know whether it was assgined value or not. 
     * 
     */
    int max = Integer.MIN_VALUE;
    public int treeDiameter(int[][] A) {
        if (A == null || A.length < 1 || A[0].length < 1) return 0;
        
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int[] a : A) {
            map.computeIfAbsent(a[0], v->new HashSet<>()).add(a[1]);
            map.computeIfAbsent(a[1], v->new HashSet<>()).add(a[0]);
        }
        
        Integer[] visited = new Integer[map.size()];
        helper(0, map, visited);
        
        return max == Integer.MIN_VALUE ? 0 : max;
    }
    
    
    
    private int helper(int node, Map<Integer, Set<Integer>> map, Integer[] visited) {
       
        //this also works
        //if (visited[node] != null ) return visited[node];
        if (visited[node] != null && visited[node] > 0 ) return visited[node];
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
         visited[node] = -1;
         for(int next: map.get(node)) {
             if (visited[next] != null) continue;
             int steps = helper(next, map, visited);
             pq.offer(steps);
             if (pq.size() > 2) {
                 pq.poll();
             }
         }
        
        int l = pq.isEmpty() ? 0 : pq.poll();
        int r = pq.isEmpty() ? 0 : pq.poll();
        max = Math.max(max, l + r);
        
        int ret = 1 + Math.max(l, r);
        visited[node] = ret;
        return ret;
    }
}
