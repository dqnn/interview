package hatecode;

import java.util.*;
public class _1059AllPathsFromSourceLeadToDestination {
/*
1059. All Paths from Source Lead to Destination
Given the edges of a directed graph, and two nodes source and destination of this graph, determine whether or not all paths starting from source eventually end at destination, that is:

At least one path exists from the source node to the destination node
If a path exists from the source node to a node with no outgoing edges, then that node is equal to destination.
The number of possible paths from source to destination is a finite number.
Return true if and only if all roads from source lead to destination.
Input: n = 3, edges = [[0,1],[0,2]], source = 0, destination = 2
Output: false
*/
    //thinking process:
    //given a DAG, return true if src go through all nodes can reach destination
    
    //so we start from src but put all connected dots into next level
    public boolean leadsToDestination(int n, int[][] g, int src, int dst) {
        if (g == null || g.length < 1 || g[0].length < 1) return true;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int[] edge : g) map.computeIfAbsent(edge[0], v->new HashSet<>()).add(edge[1]);
        
        return helper(map, src, dst, new HashSet<>());
    }
    //so each time we mark src as visited, if we can go to dst here, then it is true otherwise false
    private boolean helper(Map<Integer, Set<Integer>> map, int src, int dst, Set<Integer> set) {
        if (!map.containsKey(src)) return src == dst;
        
        for(int next : map.get(src)) {
            if (set.contains(next)) return false;
            set.add(next);
            if (!helper(map, next, dst, set)) return false;
            set.remove(next);
        }
        return true;
    }
}