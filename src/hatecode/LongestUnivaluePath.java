package hatecode;
public class LongestUnivaluePath {
/*
687. Longest Univalue Path
Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.

Note: The length of path between two nodes is represented by the number of edges between them.

Example 1:

Input:

              5
             / \
            4   5
           / \   \
          1   1   5
Output:

2
*/
    //the same longest path, biggest sum, templates code
    int max  = 0;
    public int longestUnivaluePath(TreeNode root) {
        helper(root);
        return max;
    }
    
    private int helper(TreeNode node) {
        if (node == null) return 0;
        
        int left = helper(node.left);
        int right = helper(node.right);
        
        if (node.left == null || node.left.val != node.val) {
            left = 0;
        }
        
        if (node.right == null || node.right.val != node.val) {
            right = 0;
        }
        
        max = Math.max(max, left + right);
        return Math.max(left, right) + 1;
    }
}