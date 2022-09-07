package hatecode._0001_0999;

public class _814BinaryTreePruning {
/*
814. Binary Tree Pruning
Given the root of a binary tree, return the same tree where every subtree (of the given tree) not containing a 1 has been removed.

A subtree of a node node is node plus every node that is a descendant of node.

 

Example 1:


Input: root = [1,null,0,0,1]
Output: [1,null,0,null,1]
*/
    /*
     * 
     */
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return root;
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        
        return root.val == 0 && root.left == null && root.right == null ? null : root;
    }
    
}