package hatecode._0001_0999;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Description : 323. Number of Connected Components in an Undirected Graph
 */
public class _323NumberofConnectedComponentsinanUndirectedGraph {
    /**tag:union-find
     * Given n nodes labeled from 0 to n - 1 and a list of undirected edges 
     * (each edge is a pair of nodes),
     * write a function to find the number of connected components in an 
     * undirected graph.

     Example 1:
     0          3
     |          |
     1 --- 2    4
     Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

     Example 2:
     0           4
     |           |
     1 --- 2 --- 3
     Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.

     Note:
     You can assume that no duplicate edges will appear in edges. Since all edges are undirected,
     [0, 1] is the same as [1, 0] and thus will not appear together in edges.

     图 ： 点 - 边 = 1

     n = 5 4 edges n-- = 1

     time : O(edges * nodes)
     space : O(n)

     * @param n
     * @param edges
     * @return
     */
    // thinking process: O(n)/O(n)
    public static int countComponents(int n, int[][] edges) {

        // firstly, we resume each node is one component
        int res = n;

        // this is simplified map
        int[] roots = new int[n];
        for (int i = 0; i < n; i++) {
            roots[i] = -1;
        }

        // here is the key, when we scan the edges, we can add the pair if we cannot add them into the 
        // map, so eventually the find method will help the noes they are connected or not
        for (int[] pair : edges) {
            int x = find(roots, pair[0]);
            int y = find(roots, pair[1]);
            // they are connected, so res need to --.
            //if x == y, means 
            if (x != y) {
                roots[x] = y;
                res--;
            }
        }
        return res;
    }

    // in roots map, it will find the end of a component, we can call it transition map 
    private static int find(int[] roots, int i) {
        while (roots[i] != -1) {
            i = roots[i];
        }
        return i;
    }
    //another same solution, but we have a union method
    public static int countComponents2(int n, int[][] edges) {
        int res = n;
        int[] roots = new int[n];
        Arrays.fill(roots, -1);
        
        for(int[] pair: edges) {
            if (union(roots, pair[0], pair[1])) {
                res--;
            }
        }
        return res;
    }
    
    private static boolean union(int[] roots, int x, int y) {
        int i = find(roots, x);
        int j = find(roots, y);
        if (i != j){
            roots[i] = j;
            return true;
        }
        return false;
    }

    /*
     * interview friendly O (E +V)/O(V+E)
     * we use dfs , starting from each node, then helper() will return 1 or 0 if all visited 
     * 
     */

    public int countComponents_interviewfriendly (int n, int[][] edges) {
        if(n <= 1) return n;
        
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int[] e : edges) {
            map.computeIfAbsent(e[0], v->new HashSet<>()).add(e[1]);
            map.computeIfAbsent(e[1], v->new HashSet<>()).add(e[0]);
        }
        
        boolean[] visited = new boolean[n];
        int res = 0;
        for(int i = 0; i<n; i++) res += helper(i, map, visited);
        
        return res;
    
    }
    
    
    private int helper(int i, Map<Integer, Set<Integer>> map, boolean[] visited) {
        if(visited[i]) return 0;
        
        
        visited[i] = true;
        if(!map.containsKey(i)) return 1;
        for(int next: map.get(i)) {
            helper(next, map, visited);
        }
        
        return 1;
    }
    
    public static void main(String[] args) {
        int[][] in = {{0,1},{1,2},{3,4}};
        System.out.println(countComponents2(5, in));
    }
}
