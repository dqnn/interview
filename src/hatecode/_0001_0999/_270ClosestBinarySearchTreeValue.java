package hatecode._0001_0999;
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class _270ClosestBinarySearchTreeValue {
/*
270. Closest Binary Search Tree Value
Given a non-empty binary search tree and a target value, 
find the value in the BST that is closest to the target.

Note:

Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
Example:

Input: root = [4,2,5,1,3], target = 3.714286

    4
   / \
  2   5
 / \
1   3

Output: 4
 */
    //standard solutions
    double min = Double.MAX_VALUE;
    TreeNode res = null;
    public int closestValue(TreeNode root, double t) {
        helper(root, t);
        return res.val;
    }
    
    private void helper(TreeNode node, double t) {
        if(node == null) return;
        
        // if there is a tiem we choose the smaller one 
        double diff = Math.abs(node.val - t);
        if(diff < min || diff == min && res.val > node.val) {
            min = diff;
            res = node;
        }
        
        if (node.val < t) {
            helper(node.right, t);
        } else {
            helper(node.left, t);
        }
    }
}