package hatecode;

import java.util.LinkedList;
import java.util.Queue;

class IsGraphBipartite {
/*
 * 785. Is Graph Bipartite?
Given an undirected graph, return true if and only if it is bipartite.

Recall that a graph is bipartite if we can split it's set of nodes into two 
independent subsets A and B such that every edge in the graph has one node in 
A and another node in B.

The graph is given in the following form: graph[i] is a list of indexes j 
for which the edge between nodes i and j exists.  Each node is an integer 
between 0 and graph.length - 1.  There are no self edges or parallel 
edges: graph[i] does not contain i, and it doesn't contain any element twice.

Example 1:
Input: [[1,3], [0,2], [1,3], [0,2]]
Output: true
Explanation: 
The graph looks like this:
0----1
|    |
|    |
3----2
We can divide the vertices into two groups: {0, 2} and {1, 3}.
Example 2:
Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
Output: false
Explanation: 
The graph looks like this:
0----1
| \  |
|  \ |
3----2
We cannot find a way to divide the set of nodes into two independent subsets.
 */
    //O(N+E), O(N)
    //thinking process: 
    //the problem is to say, given a graph, find two set of nodes, which they did not have any intersections
    
    //
    public boolean isBipartite(int[][] g) {
        if (g == null || g.length < 1) {
            return true;
        }
        //we use 0 as not know, 1 as one box, 2 as another box
        int[] visited = new int[g.length];
        for(int i = 0; i< g.length; i++) {
            if (g[i].length > 0 && visited[i] == 0) {
                //we put 1 as one set,
                visited[i] = 1;
                Queue<Integer> q = new LinkedList<>();
                q.offer(i);
                //[[1,3], [0,2], [1,3], [0,2]]
                // for i =0, we get current = 0, and c =1 and 3
                //we mark visited[1] and visited[3] = 1 and add 1 and 3 into queue
                //so for 1 we get 0 and 2, here visited[0] = 1. so we move visited[0] = 2, then add 0 to queue, for 2, we visited[2] = 1; 
                //
                while(!q.isEmpty()) {
                    int current = q.poll();
                    for(int c: g[current]){
                        //here means c and current may not know, if they know we should move 
                        // c to another box as 2
                        if (visited[c] == 0) {
                            visited[c] = (visited[current] == 1) ? 2 : 1;
                            q.offer(c);
                        } else {
                            // here means c and current in one box and are connected. find cone 
                            //conflicts
                            if (visited[c] == visited[current]) return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}