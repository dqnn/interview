package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : CountUnivalueSubtrees
 * Creator : duqiang
 * Date : Nov, 2017
 * Description : 250. Count Univalue Subtrees
 */
public class CountUnivalueSubtrees {
    /**
     * Given a binary tree, count the number of uni-value subtrees.

     A Uni-value subtree means all nodes of the subtree have the same value.

     For example:
     Given binary tree,
         5
        / \
       1   5
      / \   \
     5   5   5
     return 4.

     root = 5 res = 2
     root = 1
     root = 5 res = 3
     root = 5 res = 4

     time : O(n)
     space : O(n)


     */

    //simpler version, dfs version
    int count = 0;
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) return count;
        //we will overwrite root value as root value, no use for first time use
        helper(root, root.val);
        return count;
    }
    
    public boolean helper(TreeNode node, int val) {
        if (node == null) return true;
        boolean l = helper(node.left, node.val), r = helper(node.right, node.val);
        if (l && r) {
            count ++;
            return node.val == val;
        }
        return false;
    }

    int res;

    public int countUnivalSubtrees2(TreeNode root) {
        res = 0;
        helper(root);
        return res;
    }
    public boolean helper(TreeNode root) {
        if (root == null) return true;

        // post order visit the tree
        boolean left = helper(root.left);
        boolean right = helper(root.right);

        if (left && right) {
            // if left or right is null then we only need to consider other child
            // or if only one node is good enough here
            if (root.left != null && root.val != root.left.val) {
                return false;
            }
            if (root.right != null && root.val != root.right.val) {
                return false;
            }
            res++;
            return true;
        }
        return false;
    }

}
