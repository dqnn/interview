package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : BinaryTreeLongestConsecutiveSequence
 * Creator : duqiang
 * Date : Aug, 2017
 * Description : 298. Binary Tree Longest Consecutive Sequence
 */
public class BinaryTreeLongestConsecutiveSequence {

    /**
     * Given a binary tree, find the length of the longest consecutive sequence path.

     The path refers to any sequence of nodes from some starting node to any node
     in the tree along the parent-child connections. The longest consecutive path need to
     be from parent to child (cannot be the reverse).

     For example,
      1
      \
       3
      / \
     2   4
          \
           5
     Longest consecutive sequence path is 3-4-5, so return 3.
        2
         \
         3
        /
       2
      /
     1
     Longest consecutive sequence path is 2-3,not3-2-1, so return 2.

     time : O(n);
     space : O(n);
     * @param root
     * @return
     */

    private int res = 0;

    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        helper(root, 0, root.val);
        return res;
    }

    public void helper(TreeNode root, int max, int target) {
        if (root == null) return;
        if (root.val == target) {
            max++;
        } else max = 1;
        res = Math.max(res, max);
        helper(root.left, max, root.val + 1);
        helper(root.right, max, root.val + 1);
    }

    // this is wrong way to write recursive, because
    // the value we want max will be lost when executing leaf node which is not
    // successive number of its parent
    public int longestConsecutive2(TreeNode root) {
        // edge case :
        if (null == root) {
            return 0;
        }
        return helper2(root, root.val, 0);
    }

    public int helper2(TreeNode node, int value, int max) {

        if (null == node)
            return max; // it will be lost, suppose
        /*
         * 1 2 3 4 5, when we pass from 2-->4, 4 will return 1, but actually we have 2
         * already from 1--> 2--> 4
         */
        // System.out.println(String.format("node.val:%s---value:%s", node != null ?
        // node.val //: "", max));
        int temp = node.val == value ? ++max : 1;
        return Math.max(helper2(node.left, node.val + 1, temp), helper2(node.right, node.val + 1, temp));
    }

    // this is correct way how to preserve the max value when in recursive
    public int longestConsecutive3(TreeNode root) {
        // edge case :
        if (null == root) {
            return 0;
        }
        return helper3(root, root.val, 0, 0);
    }

    public int helper3(TreeNode node, int value, int max, int curMax) {

        if (null == node)
            return max;
        // System.out.println(String.format("node.val:%s---value:%s", node != null ?
        // node.val //: "", max));
        int temp = node.val == value ? ++curMax : 1;
        max = Math.max(max, curMax);
        return Math.max(helper3(node.left, node.val + 1, max, temp), helper3(node.right, node.val + 1, max, temp));
    }

}
