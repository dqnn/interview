package hatecode._1000_1999;

import java.util.*;

public class _1971FindIfPathExistsInGraph {
    /*
    1971. Find if Path Exists in Graph
    
    There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
    
    You want to determine if there is a valid path that exists from vertex source to vertex destination.
    
    Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.
    
     
    
    Example 1:
    
    
    Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
    Output: true
    Explanation: There are two paths from vertex 0 to vertex 2:
    - 0 → 1 → 2
    - 0 → 2
    */
    /*
     * thinking process: O(V+E)/O(V+E)
     * 
     * the problem is to say: given one bi-direction graph, return true if there is path from s to e
     * 
     * typical BFS
     */
        public boolean validPath(int n, int[][] edges, int s, int e) {
            if (edges == null || edges.length < 1 || edges[0].length < 1) return true;
            
            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for(int[] edge : edges) {
                graph.computeIfAbsent(edge[0], v->new HashSet<>()).add(edge[1]);
                graph.computeIfAbsent(edge[1], v->new HashSet<>()).add(edge[0]);
            }
            
            Queue<Integer> q = new LinkedList<>();
            Set<Integer> visited = new HashSet<>();
            q.offer(s);
            while(!q.isEmpty()) {
                int node = q.poll();
                if (node == e) return true;
                
                if (visited.contains(node) || !graph.containsKey(node)) continue;
                visited.add(node);
                for(int next: graph.get(node)) {
                    q.offer(next);
                }
            }
            return false;
        }
    }