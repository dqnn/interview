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
     * thinking process: O(n)/O(n)
     * 
     * the problem is to say: given one binary tree, 
     * if parent, left and right has no 1, then remove it from original tree
     * 
     * to demonstrate, let's pick one random subtree in the origin tree,
     * 
     * suppose the recursive call to current subtree, how we can decide to remove this 
     * subtree or not?
     * 
     * to remove it, we need to make sure left, right is null and root is 0,
     * if left or right is not null, then there is element not 0
     * 
     * 
     */
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return root;
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        
        return root.val == 0 && root.left == null && root.right == null ? null : root;
    }
    
}