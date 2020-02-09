package hatecode._1000_1999;

import hatecode._0001_0999.TreeNode;

public class _1080InsufficientNodesInRootToLeafPaths {
/*
1080. Insufficient Nodes in Root to Leaf Paths
Given the root of a binary tree, consider all root to leaf paths: paths from the root to any leaf.  (A leaf is a node with no children.)

A node is insufficient if every such root to leaf path intersecting this node has sum strictly less than limit.

Delete all insufficient nodes simultaneously, and return the root of the resulting binary tree.

Example 1:
Input: root = [1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14], limit = 1
Output: [1,2,3,4,null,null,7,8,9,null,14]
*/
    //thinking process: O(n)/O(lgn),lgn is mem recursion management
    //given a binary tree, from each path from root to leaf, if one node, with every
    //path sum less than limit, then remove this node and return the new root
    
    //this solution is BEST, i really was surprised by root.left == root.right, this is 
    //COOL code i have never seen. 
    
    //when we visit the tree recursively, if a node become a new leaf, we need to remove this node 
    //because it means no valid path to leaf, tricky!!!!
/*

if root.left == root.right == null, root is leaf with no child {
    if root.val < limit, we return null;
    else we return root.
}
if root.left != null, root has left child {
    Recursively call sufficientSubset function on left child,
    with limit = limit - root.val
}
if root.right != null, root has right child {
    Recursively call sufficientSubset function on right child,
    with limit = limit - root.val
}
if root.left == root.right == null,
root has no more children, no valid path left,
we return null;
else we return root.
 */
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        if (root == null) return null;
        //if this is the leaf and we found the it is less than limit,then ew return the result
        if (root.left == null && root.right == null)
            return root.val < limit ? null : root;
        
        root.left = sufficientSubset(root.left, limit - root.val);
        root.right = sufficientSubset(root.right, limit - root.val);
        //this is the key, means no path to leaf, need to remove 
        //root.left == root.right ? null : root;
        return root.left == null && root.right == null ? null : root;
    }
}