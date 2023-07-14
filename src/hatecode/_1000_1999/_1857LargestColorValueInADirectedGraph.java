package hatecode._1000_1999;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.IntStream;

public class _1857LargestColorValueInADirectedGraph {
    /*
    1857. Largest Color Value in a Directed Graph

There is a directed graph of n colored nodes and m edges. The nodes are numbered from 0 to n - 1.

You are given a string colors where colors[i] is a lowercase English letter representing the color of the ith node in this graph (0-indexed). You are also given a 2D array edges where edges[j] = [aj, bj] indicates that there is a directed edge from node aj to node bj.

A valid path in the graph is a sequence of nodes x1 -> x2 -> x3 -> ... -> xk such that there is a directed edge from xi to xi+1 for every 1 <= i < k. The color value of the path is the number of nodes that are colored the most frequently occurring color along that path.

Return the largest color value of any valid path in the given graph, or -1 if the graph contains a cycle.

 

Example 1:



Input: colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
Output: 3
Explanation: The path 0 -> 2 -> 3 -> 4 contains 3 nodes that are colored "a" (red in the above image).
    
    
    
    
    */
    /*
     * thinking process: O(V+E)/O(V+E)
     * 
     * the problem is to say: given one colors string, abaca, means node 0 is color 'a', 1 is colored as 'b'. 
     * you have list of edges, like [[0,1],[0,2],[2,3],[3,4]], it means 0->1, 0 -> 2 etc, directed graph
     * 
     * return the max color value in this graph, max color value means you are on one path, the max color frequency, for example:
     * 
     *   a--> a--> c --> a
     *   |
     *   b 
     * 
     *   return 3, because in the horizonte path, there are 3 'a'
     * 
     * 
     *   for directed graph, it is easy to think about DFS/BFS and toplogic sort, if we look into deeper, 
     *   DFS can solve the problem with TLE. 
     * 
     *   if we think about toplogic sort, also the color value must exist in the  path which starts with indegree = 0. because if not, we 
     * can always find indegree = 0 to that start node. this can help simplify the problem 
     * 
     *  we use Map<Integer, Set<Integer>> to store the graph, then we use indegree[n] to store each node indegree, when it is 0, we add to queue
     *  the key point here is that count[n][26]
     *  count[i][0] means for node i, the max count of frequency from path head to node i with color 'a'
     *  count[i][1] means for node i, the max count of frequency from path head to node i with color 'b'
     *  count[i][2] means for node i, the max count of frequency from path head to node i with color 'c'
     * 
     *  we roll up all the count from front to end. suppose we have two merged path like following 
     * 
     *   -------------|-----------------
     *   -------------|
     * 
     *  it is still good, because we will use Math.max() always pick the bigger value
     * 
     * we use visited to record how many nodes visited in the topologic sort, if not equals to n, then it has circle, return -1.
     * 
     */
    
     public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();

        //store the graph
        Map<Integer, Set<Integer>> map = new HashMap<>();
        // count[i][0] means for node i, the max count of frequency from path head to node i with color 'a'
        int[][] count = new int[n][26];
        //initialize the graph and count
        for(int i = 0; i < n; i++) {
            count[i][colors.charAt(i)-'a']++;
            map.put(i, new HashSet<>());
        }
         
        int[] indegree= new int[n];
        for(int[] e: edges) {
            map.get(e[0]).add(e[1]);
            indegree[e[1]]++;
        }
        //add indegree 0 nodes to queue
        Queue<Integer> q = new LinkedList<>();
        IntStream.range(0,n).filter(i->indegree[i] == 0).forEach(i->q.offer(i));
         
        int visited = 0;
        int res = 0;
        
        //start BFS
        while(!q.isEmpty()) {
            int u = q.poll();
            visited++;
            
            for(int v: map.get(u)) {
                for(int i = 0; i< 26; i++) {
                    count[v][i] = Math.max(count[v][i], count[u][i] + (colors.charAt(v) -'a' == i ? 1 : 0));
                }
                
                indegree[v]--;
                if (indegree[v] == 0) {
                    q.offer(v);
                }
            }
            res = Math.max(res, Arrays.stream(count[u]).max().getAsInt());
        }
         
         return visited == n ? res: -1;
     }
    
    
    
    public int largestPathValue_BF(String colors, int[][] A) {
        if (colors == null || colors.length() < 1 || A == null || A.length < 1 || A[0].length < 1) return -1;
        
        int n = colors.length();
        Map<Integer, Character> node2color = new HashMap<>();
        IntStream.range(0, n).forEach(i->node2color.put(i, colors.charAt(i)));
        
        
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        IntStream.range(0, A.length).forEach(i->graph.computeIfAbsent(A[i][0], v->new HashSet<>()).add(A[i][1]));
        
        Map<Integer, Map<Character, Integer>> map = new HashMap<>();
        
        for(int i = 0; i< n; i++) {
            if (map.containsKey(i)) continue;
            Set<Integer> visited = new HashSet<>();
            map.computeIfAbsent(i, v->new HashMap<>()).put(node2color.get(i), 1);
            if (!helper(i, i, graph, map, visited, node2color)) return -1;
        }
        
        int res = 0;
        for(var val: map.values()) {
            res = Math.max(res, val.values().stream().max(Integer::compare).get());
        }
        
        return res;
    }
    
        private boolean helper(int i, int j, Map<Integer, Set<Integer>> graph, Map<Integer, Map<Character, Integer>> map, Set<Integer> visited, Map<Integer, Character> node2color) {
            if (!graph.containsKey(j)) return true;
            if (visited.contains(j)) return false;
            visited.add(j);
            Map<Character, Integer> temp = map.get(i);
            for(int node: graph.get(j)) {
                char c = node2color.get(node);
                temp.put(c, temp.getOrDefault(c, 0) + 1);
                map.put(i, temp);
                if (!helper(i, node, graph, map, visited, node2color)) return false;
            }
            
            return true;
        }
    }