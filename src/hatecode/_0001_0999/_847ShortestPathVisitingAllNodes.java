package hatecode._0001_0999;
import java.util.*;
public class _847ShortestPathVisitingAllNodes {
/*
847. Shortest Path Visiting All Nodes
An undirected, connected graph of N nodes (labeled 0, 1, 2, ..., N-1) is given as graph.

graph.length = N, and j != i is in the list graph[i] exactly once, if and only if nodes i and j are connected.

Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.

 

Example 1:

Input: [[1,2,3],[0],[0],[0]]
Output: 4
*/
    class State {
        int key;
        int val;
        
        public State(int key, int val) {
            this.key = key;
            this.val = val;
        }
        
        public String toString() {
            return key +"->" + val;
        }
    }
    public int shortestPathLength(int[][] g) {
        if (g == null || g.length < 1 || g[0].length < 1) return 0;
        
        int r = g.length, c= g[0].length;
        int max = r;
        //use key +"->" + val as key
        Set<String> visited = new HashSet<>();
        
        Queue<State> q = new LinkedList<>();
        for(int i = 0; i<max; i++) {
            int key = (1 << i);
            q.offer(new State(key, i));
            visited.add(key + "->" + i);
        }
        
        int steps = -1;
        while(!q.isEmpty()) {
            System.out.println("queue: " + q);
            System.out.println("visited: " + visited);
            steps ++;
            int size = q.size();
            while(size-- > 0) {
                State cur = q.poll();
                if (cur.key == ((1<<max) - 1)) return steps;
                int[] nexts = g[cur.val];
                //System.out.println(Arrays.toString(nexts));
                for(int next : nexts) {
                    int key = cur.key;
                    //we do not need this, even visited, that's ok
                    if (((key >> next) & 1) == 1) {
                       // continue;
                    }
                    key |= (1 << next);
                    if (!visited.contains(key + "->" + next)) {
                        visited.add(key + "->" + next);
                        q.offer(new State(key, next));
                    }
                }
            }
        }
        return -1;
    }
}