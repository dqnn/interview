package hatecode._1000_1999;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class _1676LowestCommonAncestorOfABinaryTreeIV {
    /*
    1676. Lowest Common Ancestor of a Binary Tree IV
    Given the root of a binary tree and an array of TreeNode objects nodes, return the lowest common ancestor (LCA) of all the nodes in nodes. All the nodes will exist in the tree, and all values of the tree's nodes are unique.

Extending the definition of LCA on Wikipedia: "The lowest common ancestor of n nodes p1, p2, ..., pn in a binary tree T is the lowest node that has every pi as a descendant (where we allow a node to be a descendant of itself) for every valid i". A descendant of a node x is a node y that is on the path from node x to some leaf node.

 

Example 1:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [4,7]
Output: 2
    */

    /*
     * interview frinendly: O(n)/O(h)
     * 
     * the same as 1644
     */
    int count = 0;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        return helper(root, nodes);
    }
    
    private TreeNode helper(TreeNode root, TreeNode[] nodes) {
        if (root == null) return null;
        
        TreeNode l = helper(root.left, nodes);
        TreeNode r = helper(root.right, nodes); 
        
        for(TreeNode node: nodes) {
            if(root == node) {
                count++;
                return root;
            }
        }
        
        if(l != null && r != null) return root;
        
        return l != null ? l : r;
    }

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
}