package hatecode._0001_0999;

import java.util.LinkedList;
import java.util.Queue;

class LeafSimilarTrees {
    /*
     * 872. Leaf-Similar Trees
Consider all the leaves of a binary tree.  From left to right order, 
the values of those leaves form a leaf value sequence.



For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).

Two binary trees are considered leaf-similar if their leaf value sequence is the same.

Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.

 

Note:

Both of the given trees will have between 1 and 100 nodes.
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        if (null == root1 || null == root2) {
            return false;
        }
        
        String a= helper(root1);
        String b = helper(root2);

        return a.equals(b);
    }
    
    public String helper(TreeNode node) {
        if (node == null) return "";
        
        if (node.left == null && node.right == null) {
            return node.val + "-";
        }
        return helper(node.left) + helper(node.right);
    }
    
    // we use two queue to store leaf node, but this is not a good way, the string is better
    public boolean leafSimilar2(TreeNode root1, TreeNode root2) {
        if (null == root1 || null == root2) {
            return false;
        }
        
        Queue<TreeNode> q1 = new LinkedList<>();
        helper2(root1, q1);
        Queue<TreeNode> q2 = new LinkedList<>();
        helper2(root2, q2);
        
        if (q1.size() != q2.size()) {
            return false;
        }
        int len = q1.size();
        for(int i = 0; i < len; i++) {
            if (q1.poll().val != q2.poll().val) {
                return false;
            }
        }
        return true;
    }
    
    public void helper2(TreeNode node, Queue<TreeNode> q) {
        if (node == null) return;
        
        if (node.left == null && node.right == null) {
            q.offer(node);
            return;
        }
        helper2(node.left, q);
        helper2(node.right, q);
    }
}