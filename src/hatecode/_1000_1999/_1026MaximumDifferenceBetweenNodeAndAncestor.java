package hatecode._1000_1999;

import hatecode._0001_0999.TreeNode;

public class _1026MaximumDifferenceBetweenNodeAndAncestor {
/*

1026. Maximum Difference Between Node and Ancestor
Given the root of a binary tree, find the maximum value V for which there 
//exists different nodes A and B where V = |A.val - B.val| 
//and A is an ancestor of B.

(A node A is an ancestor of B if either: any child of A is equal to B, 
or any child of A is an ancestor of B.)
Input: [8,3,10,1,6,null,14,null,null,4,7,13]
Output: 7

*/
    //thinking process:
    //given a binary tree, find max diff between two nodes A and B, 
    //A is ancestor of B
    
    //so assume we have have the function, like helper() and we are processing one node
    //how can we figure out the max diff for this sub tree?
    //we need a context which has the max and min in from up tree, and downtree, 
    //we could have the info for above tree and how can get the info for down tree, dfs,
    //so we use min and max to record the max and min info, every time we compare to current node, 
    //if we meet leaf node, which means we go to an end, so we just return max - min
    public int maxAncestorDiff(TreeNode root) {
        if(root == null) return 0;
        return helper(root, root.val, root.val);
    }
    
    private int helper(TreeNode root, int min, int max) {
        if(root == null) return max - min;
        
        max = Math.max(max, root.val);
        min = Math.min(min, root.val);
        return Math.max(helper(root.left, min, max), helper(root.right, min, max));
    }
    
    int res = 0;
    public int maxAncestorDiff_Common(TreeNode root) {
        helper_Common(root, root.val, root.val);
        return res;
    }
    
    private void helper_Common(TreeNode root, int max, int min) {
        if (root == null) return;
        
        if (root.val > max) { max = root.val; }
        if (root.val < min) { min = root.val; }
		
        res = Math.max(max - min, res);
		
        helper(root.left, max, min);
        helper(root.right, max, min);
    }
    
}