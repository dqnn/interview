package hatecode._0001_0999;

import java.util.*;
public class _802FindEventualSafeStates {
/*
802. Find Eventual Safe States
In a directed graph, we start at some node and every turn, walk along a directed edge of the graph.  If we reach a node that is terminal (that is, it has no outgoing directed edges), we stop.

Now, say our starting node is eventually safe if and only if we must eventually walk to a terminal node.  More specifically, there exists a natural number K so that for any choice of where to walk, we must have stopped at a terminal node in less than K steps.

Which nodes are eventually safe?  Return them as an array in sorted order.

The directed graph has N nodes with labels 0, 1, ..., N-1, where N is the length of graph.  The graph is given in the following form: graph[i] is a list of labels j such that (i, j) is a directed edge of the graph.

Example:
Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
Output: [2,4,5,6]
Here is a diagram of the above graph.
*/
    //in a graph, find which node can be definitely go to end node, means if they can be in a circle then they should not be in the result set
    //so we use a way to mark these nodes by dfs
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        if (graph == null || graph.length < 1) return res;
        
        int n = graph.length;
        int[] color = new int[n];
        for(int i = 0; i< n; i++) {
            if (helper(graph, i, color)) {
                res.add(i);
            }
        }
        return res;
    }
    //1 safe, 2 unsafe, use dfs to mark each node is safe or not
    public boolean helper(int[][] graph, int pos, int[] color) {
        if (color[pos] !=0) return color[pos] == 1;
        //the way how we change the color is tricky and classical
        color[pos] = 2;
        for(int next : graph[pos]) {
            if (!helper(graph, next, color)) {
                return false;
            }
        }
        //correct previous wrong color to safe
        color[pos] = 1;
        return true;
    }
}