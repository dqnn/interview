package hatecode;

public class _1026MaximumDifferenceBetweenNodeAndAncestor {
/*

1026. Maximum Difference Between Node and Ancestor
Given the root of a binary tree, find the maximum value V for which there exists different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.

(A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.)
Input: [8,3,10,1,6,null,14,null,null,4,7,13]
Output: 7

*/
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