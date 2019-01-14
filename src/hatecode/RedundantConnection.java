package hatecode;
import java.util.*;
public class RedundantConnection {
/*
684. Redundant Connection
In this problem, a tree is an undirected graph that is connected and has no cycles.

The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.

Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given undirected graph will be like this:
  1
 / \
2 - 3
*/  //O(Nα(N))≈O(N)/O(n)
    public int[] findRedundantConnection(int[][] edges) {
        DUS dus = new DUS(edges.length);
        
        for (int[] edge : edges) {
            if (!dus.union(edge[0] - 1, edge[1] - 1)) return edge;
        }
        
        return new int[]{};
    }
    
    static class DUS {
        
        private int[] parent;
        private int[] size;
        
        public DUS(int n) {
            parent = new int[n];
            size = new int[n];
            for(int i =0; i< n;i++) parent[i] = i;
            Arrays.fill(this.size,1);
        }
        
        public int find(int x) {
            while(parent[x] != x) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }
        
        // Return false if x, y are connected.
        public boolean union(int x, int y) {
            x = find(x);
            y = find(y);
            if (x == y) return false;
            
            if (size[x] < size[y]) {
                parent[x] = y;
                size[y] += size[x];
            } else {
                parent[y] = x;
                size[x] += size[y];
            }
            
            return true;
        }
    }
    
    
    //DFS, O(n^2)/O(n)
    Set<Integer> seen = new HashSet();
    int MAX_EDGE_VAL = 1000;

    public int[] findRedundantConnection2(int[][] edges) {
        ArrayList<Integer>[] graph = new ArrayList[MAX_EDGE_VAL + 1];
        for (int i = 0; i <= MAX_EDGE_VAL; i++) {
            graph[i] = new ArrayList();
        }

        for (int[] edge: edges) {
            seen.clear();
            if (!graph[edge[0]].isEmpty() && !graph[edge[1]].isEmpty() &&
                    dfs(graph, edge[0], edge[1])) {
                return edge;
            }
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        throw new AssertionError();
    }
    public boolean dfs(ArrayList<Integer>[] graph, int source, int target) {
        if (!seen.contains(source)) {
            seen.add(source);
            if (source == target) return true;
            for (int nei: graph[source]) {
                if (dfs(graph, nei, target)) return true;
            }
        }
        return false;
    }
}