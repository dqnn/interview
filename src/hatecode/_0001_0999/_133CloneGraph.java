package hatecode._0001_0999;

import java.util.*;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : CloneGraph
 * Date : Oct, 2017
 * Description : 133. Clone Graph
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
 */


//thinking process: O()
public class _133CloneGraph {
    /**
     * time : O(m + n) m : nodes n : edges
     * space : O(m)
     */

    HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        return helper(node);
    }
    public UndirectedGraphNode helper(UndirectedGraphNode node) {
        if (node == null) return null;
        if (map.containsKey(node)) return map.get(node);
        UndirectedGraphNode dup = new UndirectedGraphNode(node.val);
        map.put(node, dup);
        for (UndirectedGraphNode neighboer : node.neighbors) {
            UndirectedGraphNode clone = helper(neighboer);
            dup.neighbors.add(clone);
        }
        return dup;
    }


    public UndirectedGraphNode cloneGraph2(UndirectedGraphNode node) {
        if (node == null) return node;
        Map<UndirectedGraphNode, UndirectedGraphNode> nodeMap= new HashMap<>();
        
        helper2(node, nodeMap);
        
        for(var entry: nodeMap.entrySet()) {
            UndirectedGraphNode origin = entry.getKey();
            for(UndirectedGraphNode temp: origin.neighbors) {
                entry.getValue().neighbors.add(nodeMap.get(temp));
            }
        }
        
        return nodeMap.get(node);
    }
    
    private void helper2(UndirectedGraphNode root,
            Map<UndirectedGraphNode, UndirectedGraphNode> nodeMap) {
        Queue<UndirectedGraphNode> q = new LinkedList<>();
        q.offer(root);
        nodeMap.put(root, new UndirectedGraphNode(root.val));
        
        while(!q.isEmpty()) {
            UndirectedGraphNode e = q.poll();
            
            for(UndirectedGraphNode tem: e.neighbors) {
                if (nodeMap.containsKey(tem)) continue;
                nodeMap.put(tem, new UndirectedGraphNode(tem.val));
                q.add(tem);
            }
            
        }
        //nodeMap.keySet().forEach(e->System.out.println(e.val));
        //conns.keySet().forEach(e->System.out.println(e.val));
    }
}
