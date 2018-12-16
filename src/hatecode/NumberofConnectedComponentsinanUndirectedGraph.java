package hatecode;

import java.util.Arrays;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : NumberofConnectedComponentsinanUndirectedGraph
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 323. Number of Connected Components in an Undirected Graph
 */
public class NumberofConnectedComponentsinanUndirectedGraph {
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
    // n is nodes, edges are thread connected nodes
    // since 
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
    
    public static void main(String[] args) {
        int[][] in = {{0,1},{1,2},{3,4}};
        System.out.println(countComponents2(5, in));
    }
}
