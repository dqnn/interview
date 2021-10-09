package hatecode._1000_1999;
import  java.util.*;;
public class _1377FrogPositionAfterTSeconds {
/*
1377. Frog Position After T Seconds
Given an undirected tree consisting of n vertices numbered from 1 to n. A frog starts jumping from vertex 1. In one second, the frog jumps from its current vertex to another unvisited vertex if they are directly connected. The frog can not jump back to a visited vertex. In case the frog can jump to several vertices, it jumps randomly to one of them with the same probability. Otherwise, when the frog can not jump to any unvisited vertex, it jumps forever on the same vertex.

The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means that exists an edge connecting the vertices ai and bi.

Return the probability that after t seconds the frog is on the vertex target.
Input: n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 2, target = 4
Output: 0.16666666666666666 

*/
    
    class Node{
        int value;
        double p;
        
        //int parent; this can help to remove the visited nodes, but 
        //it has to be a tree, not a graph
        
        public Node(int v, double p) {
            this.value = v;
            this.p = p;
        }
    }
    //thinking process: O(n)/O(n)
    
    //the problem is to say: given a list of edges, nodes count, time tick t and a target 
    //node value, after t ticks, return the probility of the frog in target node.
    
    //there are several rules there, one is you cannot jump back, another is 
    //you can only jump to new nodes, last one is if you have no choice ,just stay there.
    
    //need to understand the status count, the key is "after t seconds" which give us 
    //strong indicator there we need to care about the status.
    
    
    public double frogPosition(int n, int[][] edges, int t, int target) {
        if (n <= 1) return 1.0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        for(int[] e: edges) {
            map.computeIfAbsent(e[0], v->new ArrayList<>()).add(e[1]);
            map.computeIfAbsent(e[1], v->new ArrayList<>()).add(e[0]);
        }
        
        Set<Integer> visited = new HashSet<>();
        visited.add(1);
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(1, 1.0));
        
        while(!q.isEmpty() && t >=0) {
            //if (t < 0) break;
            int size = q.size();
            for(int i = 0; i< size; i++) {
                Node e = q.poll();
                int curV = e.value;
                double p = e.p;
                if (curV == target && t == 0) return p;
                List<Integer> needProcess = new ArrayList<>();
                for(Integer v: map.get(curV)) {
                    if (!visited.contains(v)) {
                        needProcess.add(v);
                        visited.add(v);
                    }
                }
                if (needProcess.size() == 0) {
                    needProcess.add(curV);
                }
                for(Integer v: needProcess) {
                    q.offer(new Node(v, p * 1/needProcess.size()));
                }
            }
            t--;
        }
        
        return 0;
    }
}