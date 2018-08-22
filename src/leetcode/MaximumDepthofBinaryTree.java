package leetcode;


/**
 * Created by duqiang on 28/07/2017.
 */
public class MaximumDepthofBinaryTree {
    /**
     * 104. Maximum Depth of Binary Tree
     Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its depth = 3.
     time : O(n);
     space : O(n);
     * @param root
     * @return
     */

    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        int l = maxDepth2(root.left) + 1;
        int r = maxDepth2(root.right) + 1;
        return Math.max(l, r);
    }
    
    public int maxDepth3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
       int left = maxDepth(root.left);
       int right = maxDepth(root.right);
        
        return Math.max(left, right) + 1;
    }
}
