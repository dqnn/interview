package hatecode._1000_1999;

import java.util.*;

public class _1443MinimumTimetoCollectAllApplesinATree {
/*
1443. Minimum Time to Collect All Apples in a Tree
Given an undirected tree consisting of n vertices numbered from 0 to n-1, which has some apples in their vertices. You spend 1 second to walk over one edge of the tree. Return the minimum time in seconds you have to spend to collect all apples in the tree, starting at vertex 0 and coming back to this vertex.

The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means that exists an edge connecting the vertices ai and bi. Additionally, there is a boolean array hasApple, where hasApple[i] = true means that vertex i has an apple; otherwise, it does not have any apple.

 

Example 1:


Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
Output: 8 
Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.  
*/
    /*
     * thinking process: O(E + V)/O(E+V) E is edges, V is Node
     * 
     * the problem is to say: given one edges[i, j] as 2D array, i connected 
     * to j vise either,
     * hasApple is  list of booleans which means whether the node 
     * is Apple or not, 
     * 
     * return the minimal time to collect all apples, notes: you need to 
     * come back to node root.
     * 
     * the hard of this problem is you need to back to root, 
     * if it only collect apples, then it would be easy. BFS + PQ
     * 
     * but here it required to back to root, so we need to identify the smallest 
     * time cost pattern.
     * 
     * it should be able able to figure out that 
     * suppose root has 2 child, left's child has apple
     * 
     *      root
     *     /    
     *    left
     *   /   \
     * p1   apple
     * root->left->p1->left->p2->left->root has smallest path, so
     * the cost from each subtree node to its child would be 2
     * 
     */
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i< edges.length; i++) {
            map.computeIfAbsent(edges[i][0], v->new ArrayList<>()).add(edges[i][1]);
            map.computeIfAbsent(edges[i][1], v->new ArrayList<>()).add(edges[i][0]);
        }
        
        Map<Integer, Boolean> visited = new HashMap<>();
        return helper(map, visited, 0, hasApple, 0);
    }
    
    
    private int helper(Map<Integer, List<Integer>> map, Map<Integer, Boolean> visited, int node, List<Boolean> hasApple, int cost) {
        if (visited.containsKey(node)) return 0;
        visited.put(node, true);
        
        int res = 0;
        for(int n: map.get(node)) {
            res += helper(map, visited, n, hasApple, 2);
        }
        
        if (res == 0 && !hasApple.get(node)) return 0;
        return res + cost;
    }
}