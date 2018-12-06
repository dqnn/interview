package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * 863. All Nodes Distance K in Binary Tree
User Accepted: 827
User Tried: 1105
Total Accepted: 838
Total Submissions: 2572
Difficulty: Medium
We are given a binary tree (with root node root), 
a target node, and an integer value K.

Return a list of the values of all nodes that have a distance K from the target node.  
The answer can be returned in any order.

 

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
             3
            /   \
           5       1
          / \     /  \
         6   2   0    8
            / \
           7   4

Output: [7,4,1]

Explanation: 
The nodes that are a distance 2 from the target node (with value 5)
have values 7, 4, and 1.



Note that the inputs "root" and "target" are actually TreeNodes.
The descriptions of the inputs above are just serializations of these objects.
 

Note:

The given tree is non-empty.
Each node in the tree has unique values 0 <= node.val <= 500.
The target node is a node in the tree.
0 <= K <= 1000.


 * 
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class AllNodesDistanceKInBinaryTree {
/*
We are given a binary tree (with root node root), a target node, and an integer value K.

Return a list of the values of all nodes that have a distance K from the target node. 
 The answer can be returned in any order.
 Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

Output: [7,4,1]
 */
    // < 0, then target is on right subtree
    // > 0 then its left tree
    private Map<TreeNode, Set<TreeNode>> map = new HashMap<>();

    public void buildMap(TreeNode node, TreeNode parent) {
        if (node == null) return;
        if (!map.containsKey(node)) {
            map.computeIfAbsent(node, v->new HashSet<>());
        }
        if (null != parent) {
            map.get(node).add(parent);
            map.get(parent).add(node);
        }
        buildMap(node.left, node);
        buildMap(node.right, node);
        
    }
    //in tree, if we see K distance, then think about BFS, level scan so we can 
    // understand the depth
    
    //thinking process: the problem is to ask for finding all nodes wbich has the K distance nodes from
    //target node
    
    //so it maybe one node in trees but we now do not know where they are, so from target to do 
    //BFS is the entry point, 
    
    //the next thing is to think about how to find the relationship from child -> parent because 
    //if one node is in middle of the tree, we will going to lose the the ability to go back. 
    //so we choose map to store the nodes relationship. Node<->Set{Node, Node}
    //maybe parent point to child or child to parent it does not matter
    //buildMap(node, parent) needs to be remembered
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> res = new ArrayList<>();
        if (root == null || target == null || k < 0) {
            return res;
        }
        
        buildMap(root, null);
        if (!map.containsKey(target)) {
            return res;
        }
        //System.out.println(map);
        Set<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> q = new LinkedList<>();
        visited.add(target);
        q.offer(target);
        
        while(!q.isEmpty()) {
            int size = q.size();
            if (k == 0) {
                for(int i = 0; i < size; i++) {
                    res.add(q.poll().val);
                }
                return res;
            }
            for(int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                for(TreeNode n : map.get(node)) {
                    if (!visited.contains(n)) {
                        visited.add(n);
                        q.offer(n); // from graph to expand to to ther nodes
                    }
                }
            }
            k--; 
        }
        return res;
    }
}