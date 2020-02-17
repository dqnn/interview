package hatecode._1000_1999;

import java.util.*;
public class _1129ShortestPathWithAlternatingColors {
/*
1129. Shortest Path with Alternating Colors
Consider a directed graph, with nodes labelled 0, 1, ..., n-1.  In this graph, 
each edge is either red or blue, and there could be self-edges or parallel edges.

Each [i, j] in red_edges denotes a red directed edge from node i to node j.  
Similarly, each [i, j] in blue_edges denotes a blue directed edge from node i to node j.

Return an array answer of length n, where each answer[X] is the 
length of the shortest path from node 0 to node X such that the edge colors 
alternate along the path (or -1 if such a path doesn't exist).

 

Example 1:

Input: n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
Output: [0,1,-1]
*/
    //thinking process: O(V+E)/O()
    
    //the problem is to say: given 0,1,...n-1 nodes, two edges, reds means i->j with red edges,
    //blues means i->j with blue edges, return an array res, res[x] means node 0->x shortest path with each edge
    //alternative color
    
    //
    public int[] shortestAlternatingPaths(int n, int[][] reds, int[][] blues) {
        if(n <= 0) return new int[0];
        
        // Two sets one for blu and another for red
        Set<Integer>[][] graph = new HashSet[2][n];
        for (int i = 0; i < n; i++) {
            graph[0][i] = new HashSet<>();
            graph[1][i] = new HashSet<>();
        }
        // red edges in 0 - col
        for (int[] re : reds) {
            graph[0][ re[0] ].add(re[1]);
        }
        // blu edges in 1 - col
        for (int[] blu : blues) {
            graph[1][ blu[0] ].add(blu[1]);
        }
        
        //res[0][i] means from node 0 to node i if last edge is red, the min distance
        //res[1][i] means from node 0 to node i if last edge is blue, the min is distance
        int[][] res = new int[2][n];
        // Zero edge is always accessible to itself - leave it as 0
        for (int i = 1; i < n; i++) {
            res[0][i] = 2 * n; // 2* n means not accessible and have not not use it
            res[1][i] = 2 * n;
        }
        // Q entries are vert with a color (up to that point)
        //we start from two possible nodes, one has red edge and another has blue edge
        //each time, we search for alternate color and add to Q,
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {0, 0}); // either with red
        q.offer(new int[] {0, 1}); // or with blue
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int vert = cur[0];
            int colr = cur[1];
            // No need to keep track of level up to now
            // only need to keep what color - and the length
            // is automatically derived from previous node
            for (int next : graph[1 - colr][vert]) {
                if (res[1 - colr][next] == 2 * n) {
                    res[1 - colr][next] = 1 + res[colr][vert];//this is tricky part, 
                    q.offer(new int[] {next, 1 - colr});
                }
            }
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int t = Math.min(res[0][i], res[1][i]);
            ans[i] = (t == 2 * n) ? -1 : t;
        }
        return ans;
    }
    
    //O(V + E^2)
    public int[] shortestAlternatingPaths_BF(int n, int[][] red_edges, int[][] blue_edges) {
        // Each vertex has 2 values for starting from red and blue edges, respectively.
        int[][] steps = new int[2][n];
        // Initialized with MAX values, except that 2 starting points initialized with 0.
        Arrays.fill(steps[0], 1, n, Integer.MAX_VALUE);
        Arrays.fill(steps[1], 1, n, Integer.MAX_VALUE);
        // DFS
        dfs_BF(0, 0, 0, steps, red_edges, blue_edges);
        dfs_BF(0, 1, 0, steps, red_edges, blue_edges);
        // Compare the 2 paths for each vertex and choose the shorter one.
        for (int i = 1; i < n; ++i) {
            int shorter = Math.min(steps[0][i], steps[1][i]);
            steps[0][i] = shorter == Integer.MAX_VALUE ? -1 : shorter;
        }
        return steps[0];
    }
    private void dfs_BF(int v, int color, int cnt, int[][] steps, int[][] red_edges, int[][] blue_edges) {
        int[][] edges = color == 0 ? red_edges : blue_edges;
        for (int[] vertex : edges) {
            if (v == vertex[0] && steps[1 - color][vertex[1]] > cnt + 1) {
                steps[1 - color][vertex[1]] = cnt + 1;
                dfs_BF(vertex[1], 1 - color, cnt + 1, steps, red_edges, blue_edges);
            }
        }
    }
    
    //O(V+E)
    private Map<Integer, List<Integer>> red, blue;  
    private int[][] steps;
    public int[] shortestAlternatingPaths_DFS2(int n, int[][] red_edges, int[][] blue_edges) {
        // Each vertex has 2 values for starting from red and blue edges, respectively.
        steps  = new int[2][n];
        // Initialized with MAX values, except that 2 starting points initialized with 0.
        Arrays.fill(steps[0], 1, n, Integer.MAX_VALUE);
        Arrays.fill(steps[1], 1, n, Integer.MAX_VALUE);
        // Build graphs for red and blue edges, respectively.
        red = new HashMap<>(); blue = new HashMap<>();  
        for (int[] e : red_edges)
            red.computeIfAbsent(e[0], l -> new ArrayList<>()).add(e[1]);
        for (int[] e : blue_edges)
            blue.computeIfAbsent(e[0], l -> new ArrayList<>()).add(e[1]);
        // DFS
        dfs(0, 0, 0); dfs(0, 1, 0);
        // Compare the 2 paths for each vertex and choose the shorter one.
        for (int i = 1; i < n; ++i) {
            int shorter = Math.min(steps[0][i], steps[1][i]);
            steps[0][i] = shorter == Integer.MAX_VALUE ? -1 : shorter;
        }
        return steps[0];
    }
    private void dfs(int v, int color, int cnt) {
        Map<Integer, List<Integer>> edges = color == 0 ? red : blue;
        for (int u : edges.getOrDefault(v, new ArrayList<>())) {
            if (steps[1 - color][u] > cnt + 1) {
                steps[1 - color][u] = cnt + 1;
                dfs(u, 1 - color, cnt + 1);
            }
        }
    }
}