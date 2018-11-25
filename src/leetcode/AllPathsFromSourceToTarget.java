package leetcode;

import java.util.ArrayList;
import java.util.List;

public class AllPathsFromSourceToTarget {
/*
 * 797. All Paths From Source to Target
Given a directed, acyclic graph of N nodes.  Find all possible paths from 
node 0 to node N-1, and return them in any order.

The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  
graph[i] is a list of all nodes j for which the edge (i, j) exists.

Example:
Input: [[1,2], [3], [3], []] 
Output: [[0,1,3],[0,2,3]] 
Explanation: The graph looks like this:
0--->1
|    |
v    v
2--->3
There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
Note:

The number of nodes in the graph will be in the range [2, 15].
You can print different paths in any order, but you should keep the order of nodes inside one path.
*/  
    //typical DFS
    //time complexity: O(n!), space: O(n)
    //T(n) = n * T(n-1), n elements, each sub nodes needs T(n-1) and we have n nodes
    //Space Complexity: O(N) becasue we did not allocate in each stack
    
    //if we only have 1 element, the time would be T(n) = T(n-1) + T(n-2)+...+T(1) 
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        if (graph == null || graph.length < 1 || graph[0].length < 1) {
            return res;
        }
        //the templates, 0 here is always the starting point and end is always length - 1
        List<Integer> temp = new ArrayList<>();
        temp.add(0);
        helper(res, temp, 0, graph.length - 1, graph);
        return res;
    }
    
    public void helper(List<List<Integer>> res, List<Integer> list, int start, int end, int[][] g) {
        //list.size() > 0 can be removed
        if (list.size() > 0 && list.get(list.size() - 1) == end) {
            res.add(new ArrayList<>(list));
            return;
        }
        int[] routes = g[start];
        for(int cur: routes) {
            list.add(cur);
            helper(res, list, cur, end, g);
            list.remove(list.size() - 1);
        }
    }
}