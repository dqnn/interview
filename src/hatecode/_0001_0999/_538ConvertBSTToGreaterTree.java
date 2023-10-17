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
public class _538ConvertBSTToGreaterTree {
    
/*
538. Convert BST to Greater Tree

Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus the sum of all keys greater than the original key in BST.

As a reminder, a binary search tree is a tree that satisfies these constraints:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
 

Example 1:


Input: root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
*/


/*
 * thinking process: O(n)/O(n)
 * 
 * the problem is to say: given one binary search tree, you need to sum all its right side nodes to its left node, for example
 * 
 *            4
 *          /  \
 *         2     6
 *       /  \      \
 *      1    3      8
 * 
 * it will change to 
 * 
 *            18 = 14 + 4
 *          /  \
 *         23     14 = 6 + 8
 *       /  \      \
 *      24    21    8
 * 
 * 
 * so we can use visit right, root, left order to solve the problem, but the tricky part is you need to bring its right result into left branch 
 * 
 * so we use helper(root, int v)
 * 
 * first we go to most right branch, then return its right value, 
 * 
 */
    public TreeNode convertBST(TreeNode root) {
        if(root == null) return null;
        helper(root, 0);
        
        return root;
    }
    
    
    
    private int  helper(TreeNode root, int v) {
        if (root == null) return v;
        
        int r = helper(root.right, v);
        root.val += r;
        int l = helper(root.left, root.val);
        
        return l;
    }
}