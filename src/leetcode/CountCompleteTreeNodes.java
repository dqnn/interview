package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : CountCompleteTreeNodes
 * Creator : duqiang
 * Date : Dec, 2017
 * Description : 222. Count Complete Tree Nodes
 */
public class CountCompleteTreeNodes {

    /**
     * Given a complete binary tree, count the number of nodes.

     Definition of a complete binary tree from Wikipedia:
     In a complete binary tree every level, except possibly the last, is completely filled, and all nodes
     in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

           3
         /   \
        9     20
      /  \   /  \
     15   7 1

     2^h - 1

     time : O(logn * logn)
     space : O(n) / O(logn) 不确定

     * @param root
     * @return
     */

    // this problem key is understand that:
    // complete binary tree is only one most right node miss
    // and recursive on left branch and right branch, so we can get to the
    // most left node and most right node
    // so here the logic in countNodes are also used in sub tree so it can re-used
    public int countNodes(TreeNode root) {
        // int left = helper(root, true);
        // int right = helper(root, false);
        int left = leftDepth(root);
        int right = rightDepth(root);

        if (left == right) {
            // here means 1 will be left shift left times
            return (1 << left) - 1;
        } else {
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }

    private int leftDepth(TreeNode root) {
        int res = 0;
        while (root != null) {
            root = root.left;
            res++;
        }
        return res;
    }

    private int rightDepth(TreeNode root) {
        int res = 0;
        while (root != null) {
            root = root.right;
            res++;
        }
        return res;
    }

    private int helper(TreeNode root, boolean isLeft) {
        if (root == null) return 0;
        return isLeft ? helper(root.left, isLeft) + 1: helper(root.right, isLeft) + 1;
    }

    public int countNodes2(TreeNode root) {
        if (root == null)
            return 0;
        TreeNode left = root, right = root;
        int height = 0;
        while (right != null) {
            left = left.left;
            right = right.right;
            height++;
        }
        if (left == null)
            return (1 << height) - 1;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    /*
     * The height of a tree can be found by just going left. Let a single node tree
     * have height 0. Find the height h of the whole tree. If the whole tree is
     * empty, i.e., has height -1, there are 0 nodes.
     * 
     * Otherwise check whether the height of the right subtree is just one less than
     * that of the whole tree, meaning left and right subtree have the same height.
     * 
     * If yes, then the last node on the last tree row is in the right subtree and
     * the left subtree is a full tree of height h-1. So we take the 2^h-1 nodes of
     * the left subtree plus the 1 root node plus recursively the number of nodes in
     * the right subtree. If no, then the last node on the last tree row is in the
     * left subtree and the right subtree is a full tree of height h-2. So we take
     * the 2^(h-1)-1 nodes of the right subtree plus the 1 root node plus
     * recursively the number of nodes in the left subtree. Since I halve the tree
     * in every recursive step, I have O(log(n)) steps. Finding a height costs
     * O(log(n)). So overall O(log(n)^2).
     */
    int height(TreeNode root) {
        return root == null ? -1 : 1 + height(root.left);
    }

    public int countNodes3(TreeNode root) {
        // height is special so it only count from child, not include the root since
        // it returns -1, we should change this
        int h = height(root);
        // so here if right == h - 1 which means
        //
        return h < 0 ? 0
                // if right from right child equals h - 1 which
                // means r child has same height as left,
                : height(root.right) == h - 1 ? (1 << h) + countNodes3(root.right)
                        // h - 1 is max, so if use h -1 which means left
                        : (1 << (h - 1)) + countNodes3(root.left);
    }
}
