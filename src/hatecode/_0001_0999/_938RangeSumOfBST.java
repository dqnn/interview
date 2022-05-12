package hatecode._0001_0999;
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class _938RangeSumOfBST {
/*
938. Range Sum of BST
Given the root node of a binary search tree and two integers low and high, return the sum of values of all nodes with a value in the inclusive range [low, high].


*/
    //thinking process:  avg : O(h)/O(h), worst O(n)/O(n)
    
    //tree traverse but need to track each branch twice in code because
    //first are to locate the correct branches, 
    public int rangeSumBST(TreeNode root, int low, int high) {
        return helper(root, low, high);
    }
    
    private int helper(TreeNode root, int low, int high) {
        if (root == null) return 0;
        if(root.val < low) {
            return helper(root.right,low, high);
        } 
        if (root.val > high) {
            return helper(root.left, low, high);
        }
        return root.val + helper(root.left, low, high) + helper(root.right,low, high);
    }
}