package hatecode._1000_1999;

import hatecode._0001_0999.TreeNode;

public class _1644LowestCommonAncestorofABinaryTreeII {
/*
1644. Lowest Common Ancestor of a Binary Tree II
Given the root of a binary tree, return the lowest common ancestor (LCA) of two given nodes, p and q. If either node p or q does not exist in the tree, return null. All values of the nodes in the tree are unique.

According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a binary tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)". A descendant of a node x is a node y that is on the path from node x to some leaf node.

 

Example 1:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
*/
    /*
     * thinking process: O(n)/O(h)
     * the problem compared to 236 is that p q may not be in the tree.
     * we have to search in whole tree, 
     * 
     * 236 use a short cut if (root == null || p == root || q==root) to short cut
     * because they are guarteened that p and q are in the tree. so we can return if we 
     * can find one and only need to find one in one branch
     * 
     * 
     */
    int count= 0;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = helper(root, p, q);
        return count == 2 ? res : null;
    }
    
    
    private TreeNode helper(TreeNode root, TreeNode p, TreeNode q) {
       // if (count == 2) return null;
        if (root == null ) return null;
        
        //we have to search whole tree with post order visiting the tree
        TreeNode l = helper(root.left, p, q);
        TreeNode r = helper(root.right, p, q);
        if (p == root || q == root) {
            count++;
            return root;
        }
        
        if (l !=null && r != null) return root;
        
        return l != null ? l : r;
    } 
}