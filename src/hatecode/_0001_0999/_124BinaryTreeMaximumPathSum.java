package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : BinaryTreeMaximumPathSum
 * Date : Nov, 2017
 * Description : 124. Binary Tree Maximum Path Sum
 */
public class _124BinaryTreeMaximumPathSum {
    /**
     * Given a binary tree, find the maximum path sum.

     For this problem, a path is defined as any sequence of nodes from some starting node to any node
     in the tree along the parent-child connections. The path must contain at least one node and does not
     need to go through the root.

     For example:
     Given the below binary tree,

       1
      / \
     2   3
     Return 6.

          3
         / \
        9  20
      /  \
     15   7


     time : O(n)
     space : O(n)

     */

    int res;

    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        res = Integer.MIN_VALUE;
        helper(root);
        return res;
    }

    // we need PostOrder to visit the tree. model the program in this way.
    // it has 4 use cases
    // 1 Node only, 2 L child, 3 R child 4 L + root Node,
    // 5 R + root Node, 6 L + R + RootNode

    public int helper(TreeNode root) {
        if (root == null) return 0;
        // thinking about this way:
        // we postOrder visit the tree is like visiting array from index 0,
        // so when in left bottom, the leaf node, the left = 0, right = 0, given this
        // node
        // the max SUm = root.val, so we have to use root value at this point.
        int left = Math.max(0, helper(root.left));
        int right = Math.max(0, helper(root.right));
        res = Math.max(res, left + right + root.val);
        //for a root node return value, we we only consider one branch + root, because another possible
        //result always filered in above line code
        return Math.max(left, right) + root.val;
    }

    // we use an array to store the returned results
    public int maxPathSum2(TreeNode root) {
        if (root == null)
            return 0;
        int[] result = new int[1];
        result[0] = Integer.MIN_VALUE;
        helper2(root, result);
        return result[0];
    }

    // 1 Node only, 2 L child, 3 R child 4 L + root Node,
    // 5 R + root Node, 6 L + R + RootNode
    public int helper2(TreeNode root, int[] result) {
        if (root == null)
            return 0;

        int left = Math.max(0, helper2(root.left, result)); // only left branch
        int right = Math.max(0, helper2(root.right, result)); // only right branch
        int subtreeMax = left + right + root.val; // include the root node

        result[0] = Math.max(result[0], subtreeMax);
        return Math.max(left, right) + root.val;
    }

}
