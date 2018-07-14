package leetcode;

/**
 * Project Name : Leetcode Package Name : leetcode File Name :
 * DiameterofBinaryTree Creator : duqiang Date : July, 2018 Description : TODO
 */
public class DiameterofBinaryTree {
    /**
     * 543. Diameter of Binary Tree
     * Given a binary tree, you need to compute the length of the diameter of the tree.
     * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
     * This path may or may not pass through the root.
     * Example:
     Given a binary tree
         1
        / \
       2   3
      / \
     4   5
     Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

     length of longest path which pass it = MaxDepth of its left subtree + MaxDepth of its right subtree

     time : O(n)
     space : O(n)
     * @param root
     * @return
     */

    public int res = Integer.MIN_VALUE;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root);
        return res;
    }

    // the problem it has return value and recursive
    public int helper(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = helper(node.left);
        int right = helper(node.right);
        // whole max should be accessed by this
        res = Math.max(res, left + right);
        // we alrways return left or right plus 1.
        return Math.max(left, right) + 1;
    }
}
