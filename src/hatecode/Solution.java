package hatecode;

class Solution {
/*
250. Count Univalue Subtrees
Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

Example :

Input:  root = [5,1,5,5,5,null,5]

              5
             / \
            1   5
           / \   \
          5   5   5

Output: 4
*/
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
}