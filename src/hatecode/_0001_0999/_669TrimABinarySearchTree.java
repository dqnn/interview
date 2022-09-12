package hatecode._0001_0999;

public class _669TrimABinarySearchTree {
/*
669. Trim a Binary Search Tree
Given the root of a binary search tree and the lowest and highest boundaries as low and high, trim the tree so that all its elements lies in [low, high]. Trimming the tree should not change the relative structure of the elements that will remain in the tree (i.e., any node's descendant should remain a descendant). It can be proven that there is a unique answer.

Return the root of the trimmed binary search tree. Note that the root may change depending on the given bounds.

 

Example 1:


Input: root = [3,0,4,null,2,null,null,1], low = 1, high = 3
Output: [3,2,null,1]
*/
    /*
     * thinking process: O(n)/O(h)
     * 
     * the problem is to say: given one BST, low and high, remove the nodes which are 
     * not in [low, high], but for some children should still in the tree, see
     * fillowing example 
     *            3
     *          /   \ 
     *         0     4
     *          \    
     *           2
     *          /
     *         1
     *         
     *   -----> 
     *           3
     *         /   \
     *        2     4
     *       /
     *      1   
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        return helper(root, low, high);
    }


    private TreeNode helper(TreeNode root, int l, int r) {
        if (root == null) return null;
        
        if (root.val < l) return helper(root.right, l, r);
        else if (root.val > r) return helper(root.left, l, r);
        
        
        root.left = helper(root.left, l, r);
        root.right = helper(root.right, l ,r);
        
        return root;
    }
}