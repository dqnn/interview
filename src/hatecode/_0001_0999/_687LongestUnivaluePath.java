package hatecode._0001_0999;
public class _687LongestUnivaluePath {
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
          4   4   5
Output:

2
*/
    /*
     * interview friendly: O(n)/O(n)
     * 
     * this problem is tricky because it only count edges, one naive way is to build the maps and 
     * start from every nodes, that's doable but result in high complexity 
     * 
     * if we only thinking about traverse trees with post order, we know that root node have chance to know its sub nodes status, 
     * so here we if we know its left, right child edges there, 
     * 
     * then in root node, if they are not equals, then we have mark left count as 0, res will be stored as it won't matter here 
     * also same to right child 
     */
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
        
        //here we only count edges, not nodes,
        max = Math.max(max, left + right);
        return Math.max(left, right) + 1;
    }
}