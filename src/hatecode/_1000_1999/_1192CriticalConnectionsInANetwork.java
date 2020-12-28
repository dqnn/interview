package hatecode._1000_1999;

import java.util.*;
public class _1192CriticalConnectionsInANetwork {
/*
1192. Critical Connections in a Network
Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where connections[i] = [a, b] represents a connection between servers a and b. Any server can reach any other server directly or indirectly through the network.

A critical connection is a connection that, if removed, will make some server unable to reach some other server.

Return all critical connections in the network in any order.
*/
    /*
     * thinking process: O()
     * 
     * the problem is to say: given n nodes and one 2D array, connections[i]= [a, b] means 
     * a, b nodes are connected, so return the critical connection array, which means if it is cut,
     * the nodes will be isolated from the group.
     * 4, [[0,1],[1,2],[2,0],[1,3]]-->[1, 3]
     * 6, [[0,1],[1,2],[2,0],[1,3],[3,4],[4,5],[5,3]]-->[1, 3]
     * 2nd is speical case which means one group of nodes will be cut also means critical connection
     */
    
    // We record the timestamp that we visit each node. For each node, we check every neighbor except its parent and return a smallest timestamp in all its neighbors. If this timestamp is strictly less than the node's timestamp, we know that this node is somehow in a cycle. Otherwise, this edge from the parent to this node is a critical connection
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        
        for(List<Integer> conn :connections) {
            graph[conn.get(0)].add(conn.get(1));
            graph[conn.get(1)].add(conn.get(0));
        }
        int timer[] = new int[1];
        List<List<Integer>> results = new ArrayList<>();
        boolean[] visited = new boolean[n];
        int []timeStampAtThatNode = new int[n]; 
        helper(graph, -1, 0, timer, visited, results, timeStampAtThatNode);
        return results;
    }
    
    
    public void helper(List<Integer>[] graph, int parent, int node, 
            int timer[], boolean[] visited, List<List<Integer>> results, 
            int []timeStampAtThatNode) {
        visited[node] = true;
        timeStampAtThatNode[node] = timer[0]++;
        int currentTimeStamp = timeStampAtThatNode[node];
        
        for(int oneNeighbour : graph[node]) {
            if(oneNeighbour == parent) continue;
            if(!visited[oneNeighbour]) helper(graph, node, oneNeighbour, timer, visited, results, timeStampAtThatNode);
            timeStampAtThatNode[node] = Math.min(timeStampAtThatNode[node], timeStampAtThatNode[oneNeighbour]);
            if(currentTimeStamp < timeStampAtThatNode[oneNeighbour]) results.add(Arrays.asList(node, oneNeighbour));
        }
    }
}