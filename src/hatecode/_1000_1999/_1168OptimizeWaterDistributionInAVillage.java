package hatecode._1000_1999;

import java.util.*;
public class _1168OptimizeWaterDistributionInAVillage {
/*
1168. Optimize Water Distribution in a Village
There are n houses in a village. We want to supply water for all the houses by building wells and laying pipes.

For each house i, we can either build a well inside it directly with cost wells[i], or pipe in water from another well to it. The costs to lay pipes between houses are given by the array pipes, where each pipes[i] = [house1, house2, cost] represents the cost to connect house1 and house2 together using a pipe. Connections are bidirectional.

Find the minimum total cost to supply water to all houses.
Input: n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
Output: 3
*/
    //thinking process: O(n)/O(n)
    
    //so the problem is to say: given n houses, each house has 2 ways to get water,
    //one is to dig wells, cost is wells[i], another is get water from another house, cost is 
    //pipes[1][2] means house 1 to house 2 cost an vice either.
    //so if we want all houses get water, how much cost totally?
    
    //so this is typical MST algortihms, given N nodes and its edges, find the smallest
    //cost with all nodes there.
    
    //but the only different here is the nodes not start from 0 while they start from 1, and 
    //we add node 0 virtually connect to all nodes. 
    class Edge {
        int v;
        int w;
        
        public Edge(int v, int w) {this.v = v;this.w = w;}
    }
    
    class Node {
        int id;
        List<Edge> edges;
        
        public Node(int id) {this.id = id;this.edges = new ArrayList<>();}
    }
    
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        if(n < 1 || wells == null || wells.length < 1) return 0;
        
        Map<Integer, Node> map = new HashMap<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b)->(a.w - b.w));
        Set<Integer> seen = new HashSet<>();
        int cost = 0;
        //we create mapping i->node(i)
        for (int i = 0; i <= n; i++) map.put(i, new Node(i));
        
        //we create virtually node 0 which would connect all other nodes
        for (int i = 0 ; i < wells.length; i++) {
            Edge e = new Edge(i + 1, wells[i]);
            map.get(0).edges.add(e);
            pq.offer(e);
        }
        
        //for every node, we add all real edges
        for (int[] pipe : pipes) {
            map.get(pipe[0]).edges.add(new Edge(pipe[1], pipe[2]));
            map.get(pipe[1]).edges.add(new Edge(pipe[0], pipe[2]));
        }
        
        seen.add(0);
        //so we want to make sure the max(seen.size) = n + 1, because 
        // what we need to smallest connected graph in G, we do not need to 
        //loop every possible path, so pq.size() will have a long way to 0 if 
        //backtracking or others
        while (pq.size() > 0 && seen.size() < n + 1) {
            Edge minEdge = pq.poll();
            
            if (seen.contains(minEdge.v)) continue;
            
            seen.add(minEdge.v);
            cost += minEdge.w;
            
            for (Edge newEdge : map.get(minEdge.v).edges) {
                if (!seen.contains(newEdge.v))
                    pq.offer(newEdge);
            }
        }
        
        return cost;
    }
    
    public static void main(String[] args) {
        System.out.println(new _1168OptimizeWaterDistributionInAVillage().minCostToSupplyWater(3,  new int[] {1,2,1}, new int[][] {{1,2,1}, {2,3,1}}));
    }
}