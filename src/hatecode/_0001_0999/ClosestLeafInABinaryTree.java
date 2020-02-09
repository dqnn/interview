package hatecode._0001_0999;

import java.util.*;
public class ClosestLeafInABinaryTree {
/*
742. Closest Leaf in a Binary Tree
Given a binary tree where every node has a unique value, and a target key k, find the value of the nearest leaf node to target k in the tree.

Here, nearest to a leaf means the least number of edges travelled on the binary tree to reach any leaf of the tree. Also, a node is called a leaf if it has no children.

In the following examples, the input tree is represented in flattened form row by row. The actual root tree given will be a TreeNode object.

Example 1:
Input:
root = [1,2,3,4,null,null,null,5,null,6], k = 2
Diagram of binary tree:
             1
            / \
           2   3
          /
         4
        /
       5
      /
     6

Output: 3
*/
    //O(n)/O(n), we first build the graph, then we use bfs to find nearest leaf node
    //if it is not unique node, then we have multiple source
    public int findClosestLeaf(TreeNode root, int k) {
        if(root.left == null && root.right == null) return root.val;

        // basic strategy: tree -> graph
        Map<TreeNode, List<TreeNode>> graph = new HashMap<>();

        // build graph
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode startNode = null;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (!graph.containsKey(node)) {
                graph.put(node, new ArrayList<TreeNode>());
            }
            graph.computeIfAbsent(node, v->new ArrayList<>());

            if (node.left != null) {
                graph.computeIfAbsent(node, v->new ArrayList<>()).add(node.left);
                graph.computeIfAbsent(node.left, v->new ArrayList<>()).add(node);
    
                queue.offer(node.left);
            }

            if (node.right != null) {
                graph.computeIfAbsent(node, v->new ArrayList<>()).add(node.right);
                graph.computeIfAbsent(node.right, v->new ArrayList<>()).add(node);
                queue.offer(node.right);
            }

            if (node.val == k && startNode == null) {
                startNode = node;
            }
        }

        // bfs to search shortest path
        queue.clear();
        queue.offer(startNode);
        Set<TreeNode> visited = new HashSet<>();
        visited.add(startNode);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left == null && node.right == null) return node.val;
            
            for(TreeNode e: graph.get(node)) {
                if (visited.contains(e)) continue;
                queue.offer(e);
                visited.add(e);
            }
        }
        return root.val;
    }
}