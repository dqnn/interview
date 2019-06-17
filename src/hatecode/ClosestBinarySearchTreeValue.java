package hatecode;
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class ClosestBinarySearchTreeValue {
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
    int res = -1;
    public int closestValue(TreeNode root, double target) {
        helper(root, target);
        return res;
    }
    
    private void helper(TreeNode node, double target) {
        if (node == null) return;
        if (min > Math.abs(node.val - target)) {
            min = Math.abs(node.val - target);
            res = node.val;
        }
        if (node.val > target)
            helper(node.left, target);
        else if (node.val < target)
            helper(node.right, target);
        else {}
    }
}